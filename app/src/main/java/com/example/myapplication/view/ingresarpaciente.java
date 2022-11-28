package com.example.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.myapplication.R;

public class ingresarpaciente extends AppCompatActivity {

    EditText nombre_p, id_p, edad_p, eps_p, fecha_p, hora_p;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresarpaciente);

        nombre_p = (EditText) findViewById(R.id.nombre_paciente);
        id_p = (EditText) findViewById(R.id.id_paciente);
        edad_p = (EditText) findViewById(R.id.edad_paciente);
        eps_p = (EditText) findViewById(R.id.eps_paciente);
        fecha_p = (EditText) findViewById(R.id.fecha_ingreso);
        hora_p = (EditText) findViewById(R.id.hora_ingreso);
        siguiente = (Button) findViewById(R.id.siguiente_ingdatos);

        findViewById(R.id.siguiente_ingdatos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true){//((nombre_p.getText().length() > 0) && (id_p.getText().length() > 0) && (edad_p.getText().length() > 0) && (eps_p.getText().length() > 0) && (fecha_p.getText().length() > 0) && (hora_p.getText().length() > 0))){
                    guardarPreferencias2();
                    Intent testIntent = new Intent(getApplicationContext(), foto.class);
                    startActivity(testIntent);
                }
            }
        });
    }

    private void guardarPreferencias2() {

        String nid = id_p.getText().toString();
        SharedPreferences paciente=getSharedPreferences(nid, Context.MODE_PRIVATE);

        String name = nombre_p.getText().toString();
        String n_edad = edad_p.getText().toString();
        String n_eps = eps_p.getText().toString();
        String n_fecha = fecha_p.getText().toString();
        String n_hora = hora_p.getText().toString();

        SharedPreferences.Editor editor=paciente.edit();

        editor.putString("nombre", name);
        editor.putString("documento", nid);
        editor.putString("edad", n_edad);
        editor.putString("eps", n_eps);
        editor.putString("fecha", n_fecha);
        editor.putString("hora", n_hora);
        editor.commit();

        SharedPreferences pacientes=getSharedPreferences("Pacientes", Context.MODE_PRIVATE);
        SharedPreferences.Editor us_editor=paciente.edit();
        us_editor.putString("documento", nid);
        us_editor.commit();
    }
}

