package com.sqlite.orderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sqlite.orderapp.services.model.OrderList;
import com.sqlite.orderapp.services.model.Orders;
import com.sqlite.orderapp.services.model.Users;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String reciever;
List<Orders> ordersList;
    List<OrderList> userList;
    private ArrayList<Users> mUsers=new ArrayList<>();
    RecyclerView recyclerView;;
    private OrdersAdapter ordersAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnLoad,btnOrderList;
    ProgressBar progressBar3;
    TextView textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        recyclerView =findViewById(R.id.recyclerView);
        progressBar3=findViewById(R.id.progressBar3);
        btnOrderList=findViewById(R.id.btnOrderList);
        userList=new ArrayList<>();
        progressBar3.setVisibility(View.GONE);
        btnLoad=findViewById(R.id.load);
        textView5=findViewById(R.id.textView5);

//        ordersAdapter=new OrdersAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ordersAdapter);
        ordersList = new ArrayList<>();
        btnOrderList.setOnClickListener(v->{
            fetchAllOrders();
        });

            ordersList = (ArrayList<Orders>)getIntent().getSerializableExtra("orders");
            // do something with the customer




        btnLoad.setOnClickListener(v->{
            FindOrders();
        });

    }
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
public void fetchAllOrders(){
//    Toast.makeText(this, firebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference(OrderList.class.getSimpleName()).child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    OrderList orderList = dataSnapshot1.getValue(OrderList.class);
                    userList.add(orderList);
                    System.out.println(orderList.getId()+"------------------------------");
                }
                ordersLists();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

}

    private void ordersLists() {
        DatabaseReference dbref=FirebaseDatabase.getInstance().getReference("Users");
                dbref.orderByChild("username").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mUsers.clear();
                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                Users users = dataSnapshot1.getValue(Users.class);
//                            Toast.makeText(getApplicationContext(), String.valueOf(userList.size()), Toast.LENGTH_SHORT).show();
                for (OrderList orderList : userList) {
                    assert users != null;
                    reciever=orderList.getId();
                    if (users.getId().equals(orderList.getId())) {
                        if(!mUsers.contains(users))
                            mUsers.add(users);
                    }
                }
            }
                        fetchOrders();
                        Toast.makeText(getApplicationContext(), String.valueOf(mUsers.size()), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
    public void fetchOrders(){
        DatabaseReference dbref=FirebaseDatabase.getInstance().getReference("Orders");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Orders order = dataSnapshot.getValue(Orders.class);
                assert order != null;

//                    Toast.makeText(getApplicationContext(), reciever, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), currentUser.getUid(), Toast.LENGTH_SHORT).show();
                    if (order.getOrderoto().equals(reciever) && order.getOrderfrom().equals(currentUser.getUid()) || order.getOrderoto().equals(currentUser.getUid()) && order.getOrderfrom().equals(reciever)) {
                    ordersList.add(order);
                        System.out.println(order.getName()+"----from-----"+currentUser.getUid()+"------to---"+reciever+"--------------------------------");
//                        if (order.getOrderoto().equals(currentUser.getUid())) {
//                             Toast.makeText(getApplicationContext(),"Sent Items",Toast.LENGTH_SHORT).show();
//                        }
                }
                    System.out.println(ordersList.size());
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
                    ref.child(reciever).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snap:snapshot.getChildren()) {
//                                Users user = snap.getValue(Users.class);
                                textView5.setText(snap.toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    ordersAdapter = new OrdersAdapter(ordersList, getApplicationContext());
                    recyclerView.setAdapter(ordersAdapter);
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void FindOrders(){
        ordersList.clear();
//        if(ordersList.size()<0){
//            MyOrder order=new MyOrder(4,"nyanya","sam","dav");
//            ordersList.add(order);
//        }
        progressBar3.setVisibility(View.VISIBLE);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference(Orders.class.getSimpleName());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar3.setVisibility(View.GONE);
                for ( DataSnapshot snapshot1:snapshot.getChildren()) {
                    Orders order=snapshot1.getValue(Orders.class);
                    ordersList.add(order);

                }
                ordersAdapter = new OrdersAdapter(ordersList, getApplicationContext());
                recyclerView.setAdapter(ordersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar3.setVisibility(View.GONE);
            }
        });

    }
}