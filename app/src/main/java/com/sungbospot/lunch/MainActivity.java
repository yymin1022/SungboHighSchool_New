package com.sungbospot.lunch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    LinearLayout btnDevinfo;
    LinearLayout btnMeal;
    LinearLayout btnNotice;
    LinearLayout btnSchoolinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnDevinfo = findViewById(R.id.main_btn_devInfo);
        btnMeal = findViewById(R.id.main_btn_meal);
        btnNotice = findViewById(R.id.main_btn_notice);
        btnSchoolinfo = findViewById(R.id.main_btn_schoolInfo);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.main_btn_devInfo:
                        startActivity(new Intent(getApplicationContext(), DevinfoActivity.class));
                        break;
                    case R.id.main_btn_meal:
                        startActivity(new Intent(getApplicationContext(), MealActivity.class));
                        break;
                    case R.id.main_btn_notice:
                        startActivity(new Intent(getApplicationContext(), NoticeActivity.class));
                        break;
                    case R.id.main_btn_schoolInfo:
                        startActivity(new Intent(getApplicationContext(), SchoolinfoActivity.class));
                        break;
                }
            }
        };

        btnDevinfo.setOnClickListener(btnListener);
        btnMeal.setOnClickListener(btnListener);
        btnNotice.setOnClickListener(btnListener);
        btnSchoolinfo.setOnClickListener(btnListener);

        getTodayMeal();
    }

    public void getTodayMeal(){
        Calendar mCalendar = Calendar.getInstance();

        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        MealTool.restoreMealDateClass mData = MealTool.restoreMealData(getApplicationContext(), year, month, day);

        if(mData.isBlankDay){

        }else{

        }
    }
}