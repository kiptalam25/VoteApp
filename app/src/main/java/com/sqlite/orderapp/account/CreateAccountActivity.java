package com.sqlite.orderapp.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sqlite.orderapp.MainActivity;
import com.sqlite.orderapp.R;
import com.sqlite.orderapp.models.Role;
import com.sqlite.orderapp.services.model.Users;
import com.sqlite.orderapp.vewmodel.DatabaseViewModel;
import com.sqlite.orderapp.vewmodel.SignInViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    Button btn_register;
    EditText txt_username,txt_email,txt_password;
    DatabaseViewModel databaseViewModel;
    Context context;
    String userId;
    String imageUrl;
    String timeStamp;
    FirebaseUser currentUser;
    SignInViewModel signInViewModel;
    TextView have_an_account;
    Spinner spinner_roles;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
        init();
        listeners();

    }


    public void init(){


        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        txt_username=findViewById(R.id.txt_username);
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);
        btn_register=findViewById(R.id.btn_register);
        have_an_account=findViewById(R.id.have_an_account);
        spinner_roles=findViewById(R.id.spinner_roles);
        progressBar2=findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        context=CreateAccountActivity.this;

        fillRoles();
        signInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(SignInViewModel.class);
        databaseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(DatabaseViewModel.class);

        if(currentUser!=null) {
            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            finish();
        }


    }

public void fillRoles(){
        try {

            final List<String> list = new ArrayList<>();
            JSONObject myObject = new JSONObject();
            list.add("Select Designation");
            progressBar2.setVisibility(View.VISIBLE);
            DatabaseReference rolesRef = FirebaseDatabase.getInstance().getReference().child(Role.class.getSimpleName());
            rolesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    progressBar2.setVisibility(View.GONE);
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        Role role = snap.getValue(Role.class);
                        String name = role.getRolename().toLowerCase();
                        String cap = name.substring(0, 1).toUpperCase() + name.substring(1);

                        list.add(cap);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressBar2.setVisibility(View.GONE);
                }
            });
            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_roles.setAdapter(adp1);
//    return getRoles;
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
}

    public void listeners(){


        btn_register.setOnClickListener(v->{
            SigninUser();
        });
        have_an_account.setOnClickListener(v->{
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            finish();
        });


    }
    public void SigninUser() {
        signInViewModel.userSignIn(txt_username.getText().toString(), txt_email.getText().toString(), txt_password.getText().toString());
        signInViewModel.signInUser.observe(this, task -> {
            if(!task.isSuccessful()){

                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
            else{
                currentUser=FirebaseAuth.getInstance().getCurrentUser();
                Users user=new Users();
                user.setEmailId(txt_email.getText().toString());
                user.setBio("Malle");
                user.setId(currentUser.getUid());
                user.setUsername(txt_username.getText().toString());
                user.setLatitude(0.0000);
                user.setLongitude(0.0000);
                user.setStatus("1");
                user.setImageUrl("url");
                user.setTimestamp(String.valueOf(new Date()));
                user.setDesignation(spinner_roles.getSelectedItem().toString());
                addUserInDatabase(user);
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        });

    }
    private void addUserInDatabase(Users user) {
        long tsLong = System.currentTimeMillis();
        timeStamp = Long.toString(tsLong);
        imageUrl = "default";
        userId = currentUser.getUid();
        user.setTimestamp(timeStamp);
        databaseViewModel.addUserDatabase(user);
        databaseViewModel.successAddUserDb.observe(this, aBoolean -> {
            if (aBoolean)
                Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(context, "ERROR WHILE ADDING DATA IN DATABASE.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}