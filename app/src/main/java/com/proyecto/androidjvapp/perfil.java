package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class perfil extends AppCompatActivity {

    private Button next, photo;
    private Button boton1, boton2;
    private ImageView boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nextWindow();
        takePhto();
        navegation();
        cart();
        profile();
    }

    public void nextWindow () {

        next = (Button) findViewById(R.id.editProfile);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(perfil.this, DatosBasicos.class));
            }
        });
    }

    public void takePhto () {

        photo = (Button) findViewById(R.id.cameraIcon);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(perfil.this, camera_view.class));
            }
        });
    }

    public void navegation () {
        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(perfil.this, navegacion.class));
            }
        });
    }

    public void cart () {
        boton2 = (Button) findViewById(R.id.carrito);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(perfil.this, CarroDeCompras.class));
            }
        });
    }

    public void profile () {
        boton3 = (ImageView) findViewById(R.id.imageView5);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(perfil.this, perfil.class));
            }
        });
    }

}
