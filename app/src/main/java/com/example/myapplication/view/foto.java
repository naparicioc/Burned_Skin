package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.io.IOException;

public class foto extends AppCompatActivity {

    Button btnCamara, btnSelect;
    ImageView visor;
    Bitmap bitmap;
    Module module = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto);

        btnCamara=findViewById(R.id.btn_camara);
        btnSelect=findViewById(R.id.btn_select);
        visor=findViewById(R.id.iv_visor);

        btnCamara.setOnClickListener(view -> camara());

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });

        findViewById(R.id.anterior).setOnClickListener(view -> {
            Intent atrasIntent = new Intent(getApplicationContext(), ingresarpaciente.class);
            startActivity(atrasIntent);
        });

        findViewById(R.id.sig_prediccion).setOnClickListener(view -> {

            Intent sigIntent = new Intent(getApplicationContext(), prediccion.class);
            startActivity(sigIntent);
        });
    }
    // Activar cámara
    @SuppressLint("QueryPermissionsNeeded")
    private void camara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    // Capturar la imagen
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1 && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            visor. setImageBitmap(imgBitmap);
        }
        else if (requestCode==10){
            if (data!=null) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                visor.setImageBitmap(bitmap);
            }
        }
    }
}
