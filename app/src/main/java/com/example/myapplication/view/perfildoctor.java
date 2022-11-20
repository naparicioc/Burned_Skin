package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class perfildoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfildoctor);

        findViewById(R.id.regresar_perfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inicioIntent = new Intent(getApplicationContext(), iniciodoctor.class);
                startActivity(inicioIntent);
            }
        });
    }
}