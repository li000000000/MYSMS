package com.example.sms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sms.R;
import com.google.gson.Gson;

public class TeacherActivity extends AppCompatActivity {

    private int userId;
    private TextView wait;
    private Intent intent ;
    Gson gson =new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        init();
        setMethod();
    }

    private void setMethod() {

    }

    private void init() {
        intent = getIntent();
        userId = intent.getIntExtra("id", -1);
        wait = findViewById(R.id.textView3);

    }
}