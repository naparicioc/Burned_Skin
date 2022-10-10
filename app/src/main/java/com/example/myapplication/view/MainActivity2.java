package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

import com.example.myapplication.R;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    Integer contadorpregunta = 1;
    Integer sum = 0;
    Integer sum_ant = 0;
    RadioGroup r1;
    RadioButton b1, b2, b3, b4, b5;
    Button sig, ant;
    TextView sumaFinal, textofactor, numfactor;
    JSONObject jsonfactores;

    String jsonstring = "{\"1\": \"Tiempo de quirófano (min)\n 1. Menor a 30\n 2. Entre 31 y 60\n 3. Entre 61 y 120\n 4. Entre 121 y 180\n 5. Más de 180\"," +
            "\"2\": \"Duración estimada de la estancia\n 1. Ambulatorio\n 2. Menor a 23 horas\n 3. Entre 24 y 48 horas\n 4. Entre 2 y 3 días\n 5. Más de 3 días\","+
            "\"3\": \"Necesidad de UCI postoperatoria (%)\n 1. Muy improbable\n 2. Menor a 5\n 3. Entre 5 y 10\n 4. Entre 11 y 25\n 5. Mayor a 25\","+
            "\"4\": \"Pérdida de sangre prevista (cc)\n 1. Menor a 100/\n 2. Entre 100 y 250\n 3. Entre 250 y 500\n 4. Entre 500 y 750\n 5. Mayor a 750\", " +
            "\"5\": \"Tamaño del equipo quirúrgico (n)\n 1. 1\n 2. 2\n 3. 3\n 4. 4\n 5. Más de 5\","+
            "\"6\": \"Probabilidad de intubación (%)\n 1. Menor a 1\n 2. Entre 1 y 5\n 3. Entre 6 y 10\n 4. Entre 11 y 25\n 5. Mayor a 25\","+
            "\"7\": \"Sitio quirúrgico\n 1. Ninguna de las siguientes opciones\n 2. Cirugía mínimamente invasiva abdominopélvica\n 3. Cirugía abierta abdominopélvica, infraumbilical\n 4. Cirugía abierta abdominopélvica, supraumbilical\n 5. Cirugía de otorrinolaringología, cabeza y cuello/superior, gastrointestinal/torácica\","+
            "\"8\": \"Eficacia de la opción de tratamiento no quirúrgico\n 1. Ninguno disponible\n 2. Disponible, <40% tan efectivo como la cirugía\n 3. Disponible, 40% a 60% tan efectivo como la cirugía\n 4. Disponible, 61% a 95% tan efectivo como la cirugía\n 5. Disponible, igualmente eficaz\","+
            "\"9\": \"Opción de tratamiento no quirúrgico recurso/riesgo de exposición\n 1. Significativamente peor/no aplicable\n 2. Algo peor\n 3. Equivalente\n 4. Algo mejor\n 5. Significativamente mejor\","+
            "\"10\": \"Impacto del retraso de 2 semanas en el resultado de la enfermedad\n 1. Significativamente peor\n 2. Peor\n 3. Moderadamente peor\n 4. Ligeramente peor\n 5. Nada peor\","+
            "\"11\": \"Impacto del retraso de 2 semanas en la dificultad/riesgo quirúrgico\n 1. Significativamente peor\n 2. Peor\n 3. Moderadamente peor\n 4. Ligeramente peor\n 5. Nada peor\","+
            "\"12\": \"Impacto del retraso de 6 semanas en el resultado de la enfermedad\n 1. Significativamente peor\n 2. Peor\n 3. Moderadamente peor\n 4. Ligeramente peor\n 5. Nada peor\","+
            "\"13\": \"Impacto del retraso de 6 semanas en la dificultad/riesgo quirúrgico\n 1. Significativamente peor\n 2. Peor\n 3. Moderadamente peor\n 4. Ligeramente peor\n 5. Nada peor\","+
            "\"14\": \"Años\n 1. Menos de 20\n 2. Entre 20 y 40\n 3. Entre 41 y 50\n 4. Entre 51 y 65\n 5. Mayor a 65\","+
            "\"15\": \"Enfermedad pulmonar\n 1. Ninguna\n 2. – 3\n – 4. Mínima (inhalador esporádico)\n 5. Grave\","+
            "\"16\": \"Apnea obstructiva del sueño\n 1. No\n 2. –\n 3. –\n 4. Leve/moderada\n 5. Grave (con presión positiva continua en las vías respiratorias)\","+
            "\"17\": \"Enfermedad cardiovascular\n 1. Ninguna\n 2. Mínima (sin medicamentos)\n 3. Leve (1 medicamento)\n 4. Moderada (2 medicamentos)\n 5. Severa (Más de 3 medicamentos)\","+
            "\"18\": \"Diabetes\n 1. No\n 2. –\n 3. Leve (sin medicamentos)\n 4. Moderada (solo medicamentos orales)\n 5. Severa (insulina)\","+
            "\"19\": \"Inmunocomprometido\n 1. No\n 2. –\n 3. –\n 4. Moderado\n 5. Severo\","+
            "\"20\": \"Síntomas de enfermedades similares a la influenza\n 1. No\n 2. –\n 3. –\n 4. –\n 5. Si\","+
            "\"21\": \"Exposición a una persona positiva conocida de COVID 19 en los últimos 14 días\n 1. No\n 2. Probablemente no\n 3. Posiblemente\n 4. Probablemente si\n 5. Si\"}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {
            jsonfactores = new JSONObject(jsonstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        try {
            textofactor.setText((CharSequence) jsonfactores.getString(String.valueOf(contadorpregunta)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                sig.setEnabled(false);
                sum_ant = Integer.parseInt((String) sumaFinal.getText());
                sumaFinal.setText(String.valueOf(sum));
                r1.clearCheck();
                contadorpregunta ++;
                if (contadorpregunta == 22) {
                    Intent testIntent = new Intent(getApplicationContext(), MainActivity3.class);
                    testIntent.putExtra("sum", sum);
                    startActivity(testIntent);
                }
                numfactor.setText(String.valueOf(contadorpregunta));
                try {
                    textofactor.setText((CharSequence) jsonfactores.getString(String.valueOf(contadorpregunta)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ant.setEnabled(true);
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorpregunta --;
                sumaFinal.setText(String.valueOf(sum_ant));
                numfactor.setText(String.valueOf(contadorpregunta));
                try {
                    textofactor.setText((CharSequence) jsonfactores.getString(String.valueOf(contadorpregunta)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

