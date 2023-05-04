package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Report extends AppCompatActivity {

    TextView reportdate, averageCal, maxCal, minCal, recommendations;
    TextView editMinCal, editAverageCal, editMaxCal, editRecommendations;
    Button reportHistory, nextMonth;


    //objects of date and calendar classes to obtain the date
    Calendar calendar;
    SimpleDateFormat dateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportdate = (TextView)findViewById(R.id.reportdate);
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.FULL ).format(calendar.getTime());

        averageCal = (TextView) findViewById(R.id.textAverageCal);
        maxCal = (TextView) findViewById(R.id.textMaxCal);
        minCal= (TextView) findViewById(R.id.textMinCal);
        recommendations = (TextView) findViewById(R.id.textRecommendations);

        editMinCal = (TextView) findViewById(R.id.editTextMinCal);
        editAverageCal =  (TextView) findViewById(R.id.editTextAverageCal);
        editMaxCal =  (TextView) findViewById(R.id.editTextMaxCal);
        editRecommendations =  (TextView) findViewById(R.id.editTextRecommendations);


        reportHistory = (Button) findViewById(R.id.reportHistorybtn);
        nextMonth = (Button) findViewById(R.id.nextmonthbtn);


        reportdate.setText(date);


        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Report.this, NextMonthObjectives.class);
                startActivity(intent);
            }
        });


    }
}