package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.widget.EditText;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    Button inicio;
    EditText mail, pass;
    Boolean cond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.repsuestaemail);
        pass = findViewById(R.id.respuestacontraseña);
        inicio = findViewById(R.id.inicio);
        cond = mail.getText().length() > 0 && pass.getText().length() > 0;


        findViewById(R.id.inicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().length() > 0 && pass.getText().length() > 0) {
                    Intent testIntent = new Intent(getApplicationContext(), MainActivityDP.class);
                    startActivity(testIntent);
                }
            }
        });
}
    private void fun(){
        inicio.setEnabled((mail.getText().length() > 0) && (pass.getText().length() > 0));
    }

}