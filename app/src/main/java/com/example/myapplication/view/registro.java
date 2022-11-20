package com.example.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class registro extends AppCompatActivity {

    EditText nombre, cedula, correo, eps, especialidad, contraseña;
    Button listo;
    Boolean condicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        nombre = (EditText) findViewById(R.id.nombre_doctor);
        cedula = (EditText) findViewById(R.id.cedula_doctor);
        correo = (EditText) findViewById(R.id.correo_doctor);
        eps = (EditText) findViewById(R.id.eps_doctor);
        especialidad = (EditText) findViewById(R.id.especialidad_doctor);
        contraseña = (EditText) findViewById(R.id.contraseña_doctor);
        listo = (Button) findViewById(R.id.Button_done);

        findViewById(R.id.Button_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((nombre.getText().length() > 0) && (especialidad.getText().length() > 0) && (correo.getText().length() > 0) && (eps.getText().length() > 0) && (contraseña.getText().length() > 0) && (cedula.getText().length() > 0))){
                guardarPreferencias();
                Intent testIntent = new Intent(getApplicationContext(), iniciodoctor.class);
                startActivity(testIntent);
                }
            }
        });
    }
    private void guardarPreferencias() {

        String nid = cedula.getText().toString();
        SharedPreferences doctor=getSharedPreferences(nid, Context.MODE_PRIVATE);

        String name = nombre.getText().toString();
        String mail = correo.getText().toString();
        String n_eps = eps.getText().toString();
        String n_especialidad = especialidad.getText().toString();
        String n_contraseña = contraseña.getText().toString();

        SharedPreferences.Editor editor=doctor.edit();

        editor.putString("nombre", name);
        editor.putString("cedula", nid);
        editor.putString("correo", mail);
        editor.putString("eps", n_eps);
        editor.putString("especialidad", n_especialidad);
        editor.putString("contraseña", n_contraseña);
        editor.commit();

        SharedPreferences doctores=getSharedPreferences("Doctores", Context.MODE_PRIVATE);
        SharedPreferences.Editor us_editor=doctor.edit();
        us_editor.putString("cedula", nid);
        us_editor.commit();
    }}