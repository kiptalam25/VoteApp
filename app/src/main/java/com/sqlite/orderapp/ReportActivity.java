package com.sqlite.orderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        barChart=findViewById(R.id.barChart);

        loadBarChart();

    }

    private void loadBarChart(){//ArrayList<BarEntry> entries

        ArrayList<BarEntry> entries=new ArrayList<>();
        entries.add(new BarEntry(2016,620000));
        entries.add(new BarEntry(2017,420000));
        entries.add(new BarEntry(2018,720000));
        entries.add(new BarEntry(2019,620000));
        entries.add(new BarEntry(2020,870000));
        entries.add(new BarEntry(2021,950000));

        BarDataSet barDataSet=new BarDataSet(entries,"Collections");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Example");
        barChart.animateX(2000);
        barChart.animateY(2000);
    }

}