package com.sqlite.orderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AgentsActivity extends AppCompatActivity {
    RecyclerView agentsRecyclerView;
AgentsAdapter agentsAdapter;
RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);
        agentsRecyclerView=findViewById(R.id.agents_recyclerView);
        layoutManager=new LinearLayoutManager(this);
        agentsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        agentsRecyclerView.setLayoutManager(layoutManager);

        agentsAdapter=new AgentsAdapter(this);
        agentsRecyclerView.setAdapter(agentsAdapter);

    }
}