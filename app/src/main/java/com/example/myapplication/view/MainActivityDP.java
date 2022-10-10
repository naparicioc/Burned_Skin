package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class MainActivityDP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dp);

        findViewById(R.id.Button_usuario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(getApplicationContext(),MainActivityU.class);
                startActivity(testIntent);
            }
        });

        findViewById(R.id.Button_historial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(getApplicationContext(),MainActivityH.class);
                startActivity(testIntent);
            }
        });
    }
}