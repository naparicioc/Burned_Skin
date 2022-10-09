package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity2 extends AppCompatActivity {
    Integer contadorpregunta = 1;
    Integer sum = 0;
    Integer sum_ant = 0;
    RadioGroup r1;
    RadioButton b1, b2, b3, b4, b5;
    Button sig, ant;
    TextView sumaFinal, textofactor, numfactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sumaFinal = findViewById(R.id.suma);
        textofactor = findViewById(R.id.textfactor);
        numfactor = findViewById(R.id.factor);
        r1 = findViewById(R.id.radiogroup1);
        b1 = findViewById(R.id.radioButton1);
        b2 = findViewById(R.id.radioButton2);
        b3 = findViewById(R.id.radioButton3);
        b4 = findViewById(R.id.radioButton4);
        b5 = findViewById(R.id.radioButton5);
        sig = findViewById(R.id.siguiente);
        ant = findViewById(R.id.anterior);
        sig.setEnabled(false);
        ant.setEnabled(false);

        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                sum = Integer.parseInt((String) sumaFinal.getText());
                switch(i){
                    case R.id.radioButton1:
                        sum+=1;
                        break;
                    case R.id.radioButton2:
                        sum+=2;
                        break;
                    case R.id.radioButton3:
                        sum+=3;
                        break;
                    case R.id.radioButton4:
                        sum+=4;
                        break;
                    case R.id.radioButton5:
                        sum+=5;
                        break;
                }
            }

        });

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum_ant = Integer.parseInt((String) sumaFinal.getText());
                sumaFinal.setText(String.valueOf(sum));
                r1.clearCheck();
                contadorpregunta ++;
                numfactor.setText(String.valueOf(contadorpregunta));
                ant.setEnabled(true);
                /**if (contadorpregunta == 22) {
                    Intent testIntent = new Intent(getApplicationContext(), MainActivity3.class);
                    testIntent.putExtra("sum", sumaFinal);
                    startActivity(testIntent);
                }*/
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorpregunta --;
                sumaFinal.setText(String.valueOf(sum_ant));
                numfactor.setText(String.valueOf(contadorpregunta));
                ant.setEnabled(false);
            }
        });
    }

    public void onRadioButtonClicked(View view){
        if (b1.isChecked()||b2.isChecked()||b3.isChecked()||b4.isChecked()||b5.isChecked()){
            sig.setEnabled(true);
        }
    }

}