package com.sqlite.orderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sqlite.orderapp.models.Votes;

public class VotesActivity extends AppCompatActivity {
   DatabaseReference dbref=FirebaseDatabase.getInstance().getReference("Votes");
   DatabaseReference agent_ref=FirebaseDatabase.getInstance().getReference("Agent");

Button btn_submit_vote;
EditText contestantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);
        btn_submit_vote=findViewById(R.id.btn_submit_vote);
        btn_submit_vote.setOnClickListener(v->{
            addVote();
        });
    }

    void addVote(){

       agent_ref.orderByChild("").equalTo("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Votes vote=new Votes("","","");
        dbref.push().setValue(vote).addOnSuccessListener(task->{}).addOnFailureListener(task->{});
    }
}