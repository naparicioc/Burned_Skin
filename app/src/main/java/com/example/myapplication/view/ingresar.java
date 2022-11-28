package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class ingresar extends AppCompatActivity {

    Button inicio;
    EditText mail, pass;
    Boolean cond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresar);

        mail = findViewById(R.id.repsuestaemail);
        pass = findViewById(R.id.respuestacontraseña);
        inicio = findViewById(R.id.inicio);
        cond = mail.getText().length() > 0 && pass.getText().length() > 0;


        findViewById(R.id.inicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true) {//mail.getText().length() > 0 && pass.getText().length() > 0) {
                    Intent testIntent = new Intent(getApplicationContext(), iniciodoctor.class);
                    startActivity(testIntent);
                }
            }
        });

        findViewById(R.id.buttonregistro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registroIntent = new Intent(getApplicationContext(), registro.class);
                startActivity(registroIntent);
            }
        });


    }}