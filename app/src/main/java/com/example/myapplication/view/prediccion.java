package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class prediccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediccion);

        //Bundle datos = getIntent().getExtras();
        //int num1 = datos.getInt("sum");

        TextView textview3 = findViewById(R.id.resultado);
        textview3.setText(String.valueOf(1));

        findViewById(R.id.regresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(getApplicationContext(), iniciodoctor.class);
                startActivity(testIntent);
            }
        });
    }
}