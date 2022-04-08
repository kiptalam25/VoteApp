package com.sqlite.orderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private BarChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        loadBarChart();

    }

    private void loadBarChart(){//ArrayList<BarEntry> entries

        ArrayList<BarEntry> entries=new ArrayList<>();
//        entries.add(new BarEntry(2016,620000));
//        entries.add(new BarEntry(2017,420000));
//        entries.add(new BarEntry(2018,720000));
//        entries.add(new BarEntry(2019,620000));
//        entries.add(new BarEntry(2020,870000));
//        entries.add(new BarEntry(2021,950000));
//
//        BarDataSet barDataSet=new BarDataSet(entries,"Collections");
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//
//        BarData barData=new BarData(barDataSet);
//        barChart.setFitBars(true);
//        barChart.setData(barData);
//        barChart.getDescription().setText("Example");
//        barChart.animateX(2000);
//        barChart.animateY(2000);
    }

}