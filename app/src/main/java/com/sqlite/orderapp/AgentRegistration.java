package com.sqlite.orderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sqlite.orderapp.models.Agent;
import com.sqlite.orderapp.models.County;
import com.sqlite.orderapp.models.StringWithTag;
import com.sqlite.orderapp.models.SubCounty;

import java.util.ArrayList;
import java.util.List;

public class AgentRegistration extends AppCompatActivity {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    Spinner spin_county,spin_subcounty;
    EditText txt_agent_idno;
    Button btn_save_agent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_registration);

        spin_county=findViewById(R.id.spin_county);
        spin_subcounty=findViewById(R.id.spin_subcounty);
        txt_agent_idno=findViewById(R.id.txt_agent_idno);
        btn_save_agent=findViewById(R.id.btn_save_agent);

        RegisterLocation rl=new RegisterLocation();
        MutableLiveData<DataSnapshot> counties= rl.findCounty();
        List<StringWithTag> wards=new ArrayList<>();
        counties.observe(this,dataSnapshot -> {
            if(dataSnapshot.exists()){
                for (DataSnapshot snap:dataSnapshot.getChildren()  ) {
                    County c=snap.getValue(County.class);
                    wards.add(new StringWithTag(c.getCountyName(),snap.getKey()));
                }
                ArrayAdapter<StringWithTag> adap = new ArrayAdapter<StringWithTag>(
            getApplicationContext(), android.R.layout.simple_spinner_item, wards);
            spin_county.setAdapter(adap);
            }
        });


        btn_save_agent.setOnClickListener(v->{
            RegisterAgent();
        });


        spin_county.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                findSubCounties();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

public  void findSubCounties(){
    String county=spin_county.getSelectedItem().toString();
    StringWithTag s = (StringWithTag) spin_county.getItemAtPosition(spin_county.getSelectedItemPosition());
    Object tag = s.tag;
    RegisterLocation rl=new RegisterLocation();
    MutableLiveData<DataSnapshot> subcounties= rl.findSubCounties(new County(tag.toString(),county));
    List<StringWithTag> subc=new ArrayList<>();
    subcounties.observe(this,dataSnapshot -> {
//        if(dataSnapshot.exists()){
            for (DataSnapshot snap:dataSnapshot.getChildren()  ) {
                SubCounty c=snap.getValue(SubCounty.class);
                subc.add(new StringWithTag(c.getSubCountyName(),snap.getKey()));
            }
            ArrayAdapter<StringWithTag> adap = new ArrayAdapter<StringWithTag>(
                    getApplicationContext(), android.R.layout.simple_spinner_item, subc);
            spin_subcounty.setAdapter(adap);
//        }
    });
}

public void RegisterAgent(){
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    Agent agent=new Agent();
    agent.setAgentId(user.getUid());
    agent.setAgentIdNo(txt_agent_idno.getText().toString());
    DatabaseReference agentRef=database.getReference("Agent");
//    String id=agentRef.push().getKey();
    agentRef.child(user.getUid()).setValue(agent).addOnSuccessListener(unused ->
            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show());

}



}