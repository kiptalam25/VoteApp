package com.sqlite.orderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.sqlite.orderapp.account.LoginActivity;
import com.sqlite.orderapp.services.model.Orders;
import com.sqlite.orderapp.vewmodel.DatabaseViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseViewModel databaseViewModel;
    Button btn_logout,btnbanana,btnCheckOrders,uploadImage,btnorange,btn_reg_agent,btn_add_agent,btn_open_report;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String buyerId;
    ProgressBar progressBar;
    Context context;
    Uri imageUri;
    String timeStamp;
    private StorageReference fileReference;
    private StorageTask uploadImageTask;
    byte[] dataImageByte;
    private static final int IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getCurrentFirebaseUser();

        uploadImage=findViewById(R.id.uploadImage);
        progressBar=findViewById(R.id.progressBar);
        btn_reg_agent=findViewById(R.id.btn_reg_agent);
        btn_add_agent=findViewById(R.id.btn_add_agent);
        btn_open_report=findViewById(R.id.btn_open_report);
        progressBar.setVisibility(View.GONE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        btn_add_agent.setOnClickListener(v->{
            startActivity(new Intent(this,ReportActivity.class));
        });
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Orders");

        databaseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication()))
                .get(DatabaseViewModel.class);


        btn_logout=findViewById(R.id.btn_logout);
        btnbanana=findViewById(R.id.btnbanana);
        btnCheckOrders=findViewById(R.id.btnCheckOrders);
        btnorange=findViewById(R.id.btnorange);
        Button btn_add_contestant=findViewById(R.id.btn_add_contestant);


        btn_add_contestant.setOnClickListener(v->{
            startActivity(new Intent(this,ContestantActivity.class));
        });

        uploadImage.setOnClickListener(v->{
            openImage();
//            uploadImage();
        });
        btnCheckOrders.setOnClickListener(view -> {
//            checkMyOrders();
            openOrders();
        });


        btnbanana.setOnClickListener(v->{
            PlaceOrder();
        });

        btnorange.setOnClickListener(v->{
            startActivity(new Intent(this, RegisterLocation.class));
        });
        btn_reg_agent.setOnClickListener(v->{
            startActivity(new Intent(this,AgentRegistration.class));
        });
        btn_open_report.setOnClickListener(v->{
            startActivity(new Intent(this,ReportActivity.class));
        });

        btn_logout.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            finish();
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            Bitmap bmp = null;
            try {
                bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            assert bmp != null;
            bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos);   //compression
            dataImageByte = baos.toByteArray();

            if (uploadImageTask != null && uploadImageTask.isInProgress()) {
                Toast.makeText(context, "Upload in progress.", Toast.LENGTH_SHORT).show();
            } else {

                uploadImage();
            }
        }
    }
    private void uploadImage() {
//        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.setMessage("Uploading Image.");
//        progressDialog.show();
progressBar.setVisibility(View.VISIBLE);
        if (imageUri != null) {
            long tsLong = System.currentTimeMillis();
            timeStamp = Long.toString(tsLong);
            databaseViewModel.fetchImageFileReference(timeStamp, imageUri, context);
            databaseViewModel.imageFileReference.observe(this, storageReference -> {
                fileReference = storageReference;
                uploadImageTask = fileReference.putBytes(dataImageByte);  //image address
                uploadImageTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                    if (!task.isSuccessful()) {
                        throw Objects.requireNonNull(task.getException());
                    }
                    return fileReference.getDownloadUrl();
                }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                    if (task.isSuccessful()) {
                        Uri downLoadUri = task.getResult();
                        assert downLoadUri != null;
                        String mUri = downLoadUri.toString();
                        databaseViewModel.addImageUrlInDatabase("imageUrl", mUri);
                    } else {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
//                    progressDialog.dismiss();
                    progressBar.setVisibility(View.GONE);
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
//                    progressDialog.dismiss();
                });
            });

        } else {
            Toast.makeText(context, "No image selected.", Toast.LENGTH_SHORT).show();
        }
    }



    private void getCurrentFirebaseUser() {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = auth.getCurrentUser();
         buyerId = currentFirebaseUser.getUid();
    }

    public void init(){
        this.context=getApplicationContext();
        databaseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(DatabaseViewModel.class);
    }
   public void PlaceOrder(){
       final MutableLiveData<Boolean> placeOrder = new MutableLiveData<>();
       Orders order = new Orders(2, "banana",buyerId,"orEEK9j8Fxfy3IOfUwuDB0HbuAh2","456","url");

       databaseReference.push().setValue(order).addOnSuccessListener(unused ->
               Toast.makeText(MainActivity.this, "Sent!", Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
       DatabaseReference orderRef = firebaseDatabase.getReference("OrderList")
               .child(order.getOrderoto())
               .child(order.getOrderfrom());
       long tsLong = System.currentTimeMillis();
       String timestamp = Long.toString(tsLong);
       orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (!dataSnapshot.exists()) {
                   orderRef.child("id").setValue(order.getOrderfrom());
                   orderRef.child("timestamp").setValue(timestamp);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    List<Orders> orderList=new ArrayList<>();





    public void checkMyOrders(){
progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Orders order = dataSnapshot.getValue(Orders.class);
                        orderList.add(order);
//                        Toast.makeText(MainActivity.this, order.getName(), Toast.LENGTH_SHORT).show();
                    }
//                    orderList.add(new MyOrder(4,"banana","david","felix"));
                    progressBar.setVisibility(View.GONE);

                    openOrders();
//                    finish();

                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




    public void openOrders(){
        if(orderList.size()<=0){
            Orders order=new Orders(4,"nyanya","sam","dav","","");
            orderList.add(order);
        }

        Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
        Bundle b=new Bundle();
        b.putSerializable("orders", (Serializable) orderList);
        //i.putExtras(b);
        intent.putExtras(b);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void loadChats(){
        DatabaseReference chatsRef = FirebaseDatabase.getInstance().getReference("OrderList");


        chatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}