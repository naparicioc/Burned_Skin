package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class iniciodoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciodoctor);

        findViewById(R.id.Button_usuario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent usuarioIntent = new Intent(getApplicationContext(), ingresarpaciente.class);
                startActivity(usuarioIntent);
            }
        });

        findViewById(R.id.Button_historial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historialIntent = new Intent(getApplicationContext(), historial.class);
                startActivity(historialIntent);
            }
        });

        findViewById(R.id.Button_perfil_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfilIntent = new Intent(getApplicationContext(), perfildoctor.class);
                startActivity(perfilIntent);
            }
        });

        findViewById(R.id.Button_cerrar_sesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cerrarIntent = new Intent(getApplicationContext(), ingresar.class);
                startActivity(cerrarIntent);
            }
        });
    }
}