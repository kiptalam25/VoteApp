
package com.sqlite.orderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import  com.sqlite.orderapp.models.County;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sqlite.orderapp.models.Location;
import com.sqlite.orderapp.models.StringWithTag;
import com.sqlite.orderapp.models.SubCounty;
import com.sqlite.orderapp.models.Ward;

import java.util.ArrayList;
import java.util.List;

public class RegisterLocation extends AppCompatActivity {
FirebaseDatabase database= FirebaseDatabase.getInstance();
    Button btn_save_county,btn_add_subcounty,btn_save_ward,btn_add_location;
EditText county_name,sub_county_name,ward_name,txt_location;
Spinner spin_subcounties,spin_ward;

    List<County> countyList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_location);
        btn_save_county=findViewById(R.id.btn_save_county);
        btn_add_subcounty=findViewById(R.id.btn_add_subcounty);
        btn_save_ward=findViewById(R.id.btn_save_ward);
        county_name=findViewById(R.id.county_name);
        ward_name=findViewById(R.id.ward_name);
        sub_county_name=findViewById(R.id.sub_county_name);
        spin_subcounties=findViewById(R.id.spin_subcounties);
        btn_add_location=findViewById(R.id.btn_add_location);
        txt_location=findViewById(R.id.txt_location);
        spin_ward=findViewById(R.id.spin_ward);

findCounty();
        btn_save_county.setOnClickListener(v->{
            registerCounty();
        });
        btn_add_subcounty.setOnClickListener(v->{
            registerSubCounty();
        });

        btn_add_location.setOnClickListener(v->{
            String ward=spin_ward.getSelectedItem().toString();
            StringWithTag s = (StringWithTag) spin_subcounties.getItemAtPosition(spin_subcounties.getSelectedItemPosition());
            Object tag = s.tag;
            registerLocation(new Ward(tag.toString(),ward,""));
        });

        btn_save_ward.setOnClickListener(v->{
            String subcounty=spin_subcounties.getSelectedItem().toString();
            StringWithTag s = (StringWithTag) spin_subcounties.getItemAtPosition(spin_subcounties.getSelectedItemPosition());
            Object tag = s.tag;
                    registerWard(new SubCounty(tag.toString(),subcounty));
        });
    }

    public MutableLiveData<DataSnapshot> findSubCounties(County county) {
        final MutableLiveData<DataSnapshot> fetchAllSubCounties= new MutableLiveData<>();
        String countyId=county.getCountyId();
//        if(countyList.size()>=1) {
//            County county = countyList.get(0);
//            String countyId = county.getCountyId();
//        }

        List<StringWithTag> list = new ArrayList<>();
       database.getReference(SubCounty.class.getSimpleName())
               .orderByChild("countyId").equalTo(countyId)
               .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fetchAllSubCounties.setValue(snapshot);
//                if(snapshot.exists()){
//                    list.clear();
//                    for (DataSnapshot snap:snapshot.getChildren()) {
//                        SubCounty subCounty=snap.getValue(SubCounty.class);
//                        subCounty.setSubCountyId(snap.getKey());
//                        list.add(new StringWithTag(subCounty.getSubCountyName(), snap.getKey()));
//                    }
//                    ArrayAdapter<StringWithTag> adap = new ArrayAdapter<> (
//                            getApplicationContext(), android.R.layout.simple_spinner_item, list);
//                    spin_subcounties.setAdapter(adap);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "No subcounties Found", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
//
//SubCounty subCounty=new SubCounty();
//        List<StringWithTag> wards=new ArrayList<>();
//        database.getReference("Ward")
//                .orderByChild("subCountyId")
//                .equalTo(subCounty.getCountyId())
//                .addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for (DataSnapshot snap:snapshot.getChildren()) {
//                        Ward ward=snap.getValue(Ward.class);
//                        ward.setWardId(snap.getKey());
//                        wards.add(new StringWithTag(ward.getWardName(), snap.getKey()));
//                    }
//                    ArrayAdapter<StringWithTag> adap = new ArrayAdapter<> (
//                            getApplicationContext(), android.R.layout.simple_spinner_item, wards);
//                    spin_ward.setAdapter(adap);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "No subcounties Found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
return  fetchAllSubCounties;
    }

    public void registerCounty(){
        String countyName=county_name.getText().toString().trim();
        County county1 = new County(countyName);
DatabaseReference countyRef=database.getReference("County");
        countyRef.orderByChild("countyName")
                .equalTo(countyName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Record Exists", Toast.LENGTH_SHORT).show();
                } else {
                    //Save the county
                    String id=countyRef.push().getKey();
                    county1.setCountyId(id);
                    countyRef.child(id).setValue(county1).addOnSuccessListener(unused ->
                            Toast.makeText(RegisterLocation.this, "Saved!", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(RegisterLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void registerSubCounty(){
        String CountyId=countyList.get(0).getCountyId();

//        for (County c:countyList ) {
//            CountyId=c.getCountyId();
//        }

        DatabaseReference sub_county= database.getReference(SubCounty.class.getSimpleName());
        SubCounty sub_county1 = new SubCounty(CountyId,sub_county_name.getText().toString().trim());
        sub_county.orderByChild("subCountyName").equalTo(sub_county_name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Record Exists", Toast.LENGTH_SHORT).show();
                } else {
                    //Save the sub_county
                    String id = sub_county.push().getKey();
                    sub_county.child(id).setValue(sub_county1).addOnSuccessListener(unused ->
                            Toast.makeText(RegisterLocation.this, "Saved",
                                    Toast.LENGTH_SHORT).show()).addOnFailureListener(e ->
                            Toast.makeText(RegisterLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void registerLocation(Ward ward){
        DatabaseReference location= database.getReference(Location.class.getSimpleName());
        String LocationName=txt_location.getText().toString();
        Location location1 = new Location(LocationName,ward.getWardId());
        location.orderByChild("lacationName").equalTo(LocationName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Record Exists", Toast.LENGTH_SHORT).show();
                } else {
                    //Save the county
                    String id =location.push().getKey();
                    location.child(id).setValue(location1).addOnSuccessListener(unused ->
                            Toast.makeText(RegisterLocation.this, "Location Saved!", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(RegisterLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }




    public void registerWard(SubCounty subCounty){
        DatabaseReference wardRef= database.getReference(Ward.class.getSimpleName());
        String id =wardRef.push().getKey();
        String wardName=ward_name.getText().toString().trim();
        Ward ward = new Ward(wardName,subCounty.getSubCountyId());

        wardRef.orderByChild("wardName").equalTo(wardName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Record Exists", Toast.LENGTH_SHORT).show();
                } else {
                    //Save the county
                    wardRef.child(id).setValue(ward).addOnSuccessListener(unused ->
                            Toast.makeText(RegisterLocation.this, "Saved!", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(RegisterLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public  MutableLiveData<DataSnapshot>   findCounty(){
        final MutableLiveData<DataSnapshot> fetchAllCounties= new MutableLiveData<>();
    DatabaseReference databaseReference=database.getReference(County.class.getSimpleName());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    fetchAllCounties.setValue(snapshot);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        County county = dataSnapshot.getValue(County.class);
                        county.setCountyId(dataSnapshot.getKey());
                        countyList.add(county);
                    }

                } catch (Exception e) {
                    Toast.makeText(RegisterLocation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return fetchAllCounties;
    }





}