package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivityU extends AppCompatActivity {

    EditText nombre, edad, eps, fecha, correo, cirugia, cedula;
    Button listo;
    Boolean condicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_u);

        nombre = (EditText) findViewById(R.id.et_name);
        fecha = (EditText) findViewById(R.id.et_date);
        correo = (EditText) findViewById(R.id.et_mail);
        eps = (EditText) findViewById(R.id.et_eps);
        edad = (EditText) findViewById(R.id.et_age);
        cirugia = (EditText) findViewById(R.id.et_cirugia);
        cedula = (EditText) findViewById(R.id.et_cedula);
        listo = (Button) findViewById(R.id.Button_done);


        findViewById(R.id.Button_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((nombre.getText().length() > 0) && (fecha.getText().length() > 0) && (correo.getText().length() > 0) && (eps.getText().length() > 0) && (edad.getText().length() > 0) && (cirugia.getText().length() > 0) && (cedula.getText().length() > 0))){
                guardarPreferencias();
                Intent testIntent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(testIntent);
                }
            }
        });
    }
    private void guardarPreferencias() {

        String nid = cedula.getText().toString();
        SharedPreferences usuario=getSharedPreferences(nid, Context.MODE_PRIVATE);

        String name = nombre.getText().toString();
        String age = edad.getText().toString();
        String mail = correo.getText().toString();
        String n_cirugia = cirugia.getText().toString();
        String date = fecha.getText().toString();
        String n_eps = eps.getText().toString();

        SharedPreferences.Editor editor=usuario.edit();

        editor.putString("nombre", name);
        editor.putString("cedula", nid);
        editor.putString("edad", age);
        editor.putString("correo", mail);
        editor.putString("cirugia", n_cirugia);
        editor.putString("fecha", date);
        editor.putString("eps", n_eps);
        editor.commit();

        SharedPreferences usuarios=getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor us_editor=usuario.edit();
        us_editor.putString("cedula", nid);
        us_editor.commit();
    }
    }