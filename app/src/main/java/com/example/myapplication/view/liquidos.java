package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class liquidos extends AppCompatActivity {

    EditText area_v, masa_v;
    Button calcular;
    float vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liquidos);

        TextView instrucciones = findViewById(R.id.instrucciones);
        TextView volumen = findViewById(R.id.volumen);
        calcular = findViewById(R.id.calcularvol);
        area_v = (EditText) findViewById(R.id.area_value);
        masa_v = (EditText) findViewById(R.id.masa_value);
        instrucciones.setText("La primera mitad del fluido se administra dentro de las primeras 8 horas posteriores a la quemadura, y la mitad restante en las siguientes 16 horas.");
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                vol = 4*Float.valueOf(String.valueOf(masa_v.getText()))*Float.valueOf(String.valueOf(area_v.getText()));
                volumen.setText(String.valueOf(vol)+" mL");
            }
        });
    }
}