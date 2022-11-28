package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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
            SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
            String ecimg = sharedPref.getString("ecimg", null);
            getPred(ecimg);
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
            visor.setImageBitmap(imgBitmap);
            String imgString = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                imgString = Base64.getEncoder().encodeToString(getFileDataFromDrawable(imgBitmap));
            }
            SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor. putString("ecimg", imgString);
            editor.apply();

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
                String imgString = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    imgString = Base64.getEncoder().encodeToString(getFileDataFromDrawable(bitmap));
                }
                SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor. putString("ecimg", imgString);
                editor.apply();
            }
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void getPred(final String imgString) {
        String url = "http://192.168.207.228:5000/test";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formbody = new FormBody.Builder().add("encodeimg", imgString).build();
        Request request =new Request.Builder().url(url).post(formbody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(foto.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(foto.this,"Error in nertwork", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("pred", "-1");
                        editor.apply();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String pred = response.body().string();
                SharedPreferences sharedPref = getSharedPreferences("Datos", foto.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("pred", pred);
                editor.apply();
            }

        });
    }

    }


