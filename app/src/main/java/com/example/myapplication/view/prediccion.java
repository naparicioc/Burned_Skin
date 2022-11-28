package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.R;

public class prediccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediccion);

        SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
        String pred = sharedPref.getString("pred", "-1");

        TextView grado = findViewById(R.id.resultado);
        grado.setText(String.valueOf(pred));

        TextView recomendaciones = findViewById(R.id.reco);

        if (grado.getText().toString().equals(String.valueOf(1))){
            recomendaciones.setText("-Asegúrese de que las vías respiratorias estén permeables; oxígeno de alto flujo, incluso cuando la SpO2 es normal.\n" +
                  "-Establezca un acceso intravenoso, a través de la piel no quemada si es posible (acceso intraóseo si no es posible el acceso intravenoso).\n" +
                  "-Ringer lactato  (RL): 20 ml/kg durante la primera hora, incluso si el paciente se encuentra estable.\n" +
                  "-Morfina  SC: 0,2 mg/kg (los analgésicos de los pasos 1 y 2 no son efectivos).\n" +
                  "-Si es una quemadura química enjuague con abundante agua durante 15 a 30 minutos, evitando la contaminación de la piel sana; no intente neutralizar el agente químico.");
        }
        else if (grado.getText().toString().equals(String.valueOf(2))){
            recomendaciones.setText("-Asegúrese de que las vías respiratorias estén permeables; oxígeno de alto flujo, incluso cuando la SpO2 es normal.\n" +
                    "-Establezca un acceso intravenoso, a través de la piel no quemada si es posible (acceso intraóseo si no es posible el acceso intravenoso).\n" +
                    "-Ringer lactato  (RL): 20 ml/kg durante la primera hora, incluso si el paciente se encuentra estable.\n" +
                    "-Morfina  SC: 0,2 mg/kg (los analgésicos de los pasos 1 y 2 no son efectivos).\n" +
                    "-Si es una quemadura química enjuague con abundante agua durante 15 a 30 minutos, evitando la contaminación de la piel sana; no intente neutralizar el agente químico.");
        }
        else if (grado.getText().toString().equals(String.valueOf(3))){
            recomendaciones.setText("Cubre la quemadura. Cubre la zona, sin apretar, con una gasa o un paño limpio.\n"+
                    "Eleva la zona quemada por encima de la altura del corazón, en la medida de lo posible.\n\n"+
                    "Asegúrate de que la persona que sufrió la quemadura esté respirando. En caso de ser necesario, comienza con la respiración de rescate.\n"+
                    "Quítale las joyas, los cinturones y otros elementos ajustados, especialmente de la zona afectada por la quemadura y del cuello.\n"+
                    "Remitir inmediatamente al paciente a un centro hospitalario especializado en quemaduras.");
            recomendaciones.setText(pred);
        }
        else if (grado.getText().toString().equals(String.valueOf(-1))) {
            recomendaciones.setText("Error: Verificar conexión con el servidor");
        }

        findViewById(R.id.area).setOnClickListener(view -> {
            Intent testIntent = new Intent(getApplicationContext(), liquidos.class);
            startActivity(testIntent);
        });
    }
}