package com.sqlite.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sqlite.orderapp.models.Contestant;
import com.sqlite.orderapp.vewmodel.DatabaseViewModel;

public class ContestantActivity extends AppCompatActivity {
    DatabaseViewModel databaseViewModel = new DatabaseViewModel();
    Button btn_save_contestant;
    EditText txt_contestant_name, txt_contestant_idno, txt_contestant_phone, txt_contestant_email, txt_contestant_region, txt_contestant_description;
    Spinner spin_political_seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant);
        init();
        btn_save_contestant.setOnClickListener(v -> {
            saveContestant();
        });
    }


    void saveContestant() {
        Contestant contestant = new Contestant();
        String idno = txt_contestant_idno.getText().toString().trim();
        String name = txt_contestant_name.getText().toString().trim();
        String email = txt_contestant_email.getText().toString().trim();
        String phone = txt_contestant_phone.getText().toString().trim();
        String politicalseatId = "Governor UasinGishu";//spin_political_seat.getSelectedItem()
        // .toString();
        String region = txt_contestant_region.getText().toString().trim();
        String description = txt_contestant_description.getText().toString().trim();

        contestant.setContestantIdNo(idno);
        contestant.setContestantEmail(name);
        contestant.setContestantPhone(phone);
        contestant.setPoliticalSeatId(politicalseatId);
        contestant.setRegion(region);
        contestant.setContestantDescription(description);

        databaseViewModel.addContestant(contestant);
    }


    void init() {
        btn_save_contestant = findViewById(R.id.btn_save_contestant);
        txt_contestant_name = findViewById(R.id.txt_contestant_name);
        txt_contestant_idno = findViewById(R.id.txt_contestant_idno);
        txt_contestant_phone = findViewById(R.id.txt_contestant_phone);
        txt_contestant_email = findViewById(R.id.txt_contestant_email);
        txt_contestant_region = findViewById(R.id.txt_contestant_region);
        txt_contestant_description = findViewById(R.id.txt_contestant_description);
        spin_political_seat = findViewById(R.id.spin_political_seat);
    }
}