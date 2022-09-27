package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity2 extends AppCompatActivity {
    Integer sumFinal = 0;
    Integer multiplo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView text1 = findViewById(R.id.textView2);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer sum = Integer.parseInt((String) text1.getText());
                sum ++;
                text1.setText(String.valueOf(sum));
                sumFinal = sum;
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(getApplicationContext(), MainActivity3.class);
                testIntent.putExtra("sum",sumFinal);
                startActivity(testIntent);
            }
        });

        Spinner spinner1 = findViewById(R.id.spinner2);
        String[] opciones = {"opción 1","Opción 2","Opción 3"};
        Integer[] opcionesNum = {1,2,3};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                multiplo = opcionesNum[i];
                sumFinal = multiplo*sumFinal;
                text1.setText(String.valueOf(sumFinal));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RadioGroup r1 = findViewById(R.id.radiogroup1);
        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.radioButton:
                        sumFinal+=5;
                        break;
                    case R.id.radioButton2:
                        sumFinal+=8;
                        break;
                    case R.id.radioButton3:
                        sumFinal+=25;
                        break;
                    case R.id.radioButton4:
                        sumFinal+=40;
                        break;
                }
                text1.setText(String.valueOf(sumFinal));
            }
        });

    }
}