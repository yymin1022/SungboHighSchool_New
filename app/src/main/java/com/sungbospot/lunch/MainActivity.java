package com.sungbospot.lunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
    }
}
