package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class envio extends AppCompatActivity {

    private Button pagar, ubicar;
    private ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio);

        nextWindow();
        cambiarUbicacion();
        cambiarUbicacion2();
        verTienda();

    }

    public void nextWindow () {
        pagar = (Button) findViewById(R.id.pagoVista);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(envio.this, Pago.class));
            }
        });
    }

    public void cambiarUbicacion () {
        ubicar = (Button) findViewById(R.id.ubicacion);
        ubicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(envio.this, ubicacion_texto.class));
            }
        });
    }

    private void cambiarUbicacion2 () {
        img1 = (ImageView) findViewById(R.id.imageView43);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(envio.this, ubicacion_texto.class));
            }
        });
    }

    private void verTienda () {
        img2 = (ImageView) findViewById(R.id.imageView37);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(envio.this, ubicacion_1.class));
            }
        });
    }

}
