package com.example.myapplication.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import android.content.Intent;
import android.view.View;

public class MainActivityH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_h);

        findViewById(R.id.regresar2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(getApplicationContext(),MainActivityDP.class);
                startActivity(backIntent);
            }
        });

    /**private void cargarPreferencias(){
        SharedPreferences usuarios=getSharedPreferences
                ("usuarios",MODE_PRIVATE);

        String cedula = usuarios.getString("cedula", "No existe este usuario");

        SharedPreferences usuario=getSharedPreferences
                (cedula, MODE_PRIVATE);

        String name = usuario.getString("nombre","xd");
        String age = usuario.getString("nombre","xd");
        String mail = usuario.getString("nombre","xd");
        String cirugia = usuario.getString("nombre","xd");
        String eps = usuario.getString("nombre","xd");
    }*/
    }
}