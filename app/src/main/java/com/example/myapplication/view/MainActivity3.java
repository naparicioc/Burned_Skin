package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle datos = getIntent().getExtras();
        int num1 = datos.getInt("sum");

        TextView textview3 = findViewById(R.id.textView3);
        textview3.setText(String.valueOf(num1));
    }
}