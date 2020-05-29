package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class confirm_delivery extends AppCompatActivity {

    private Button btn1;
    private Button boton1, boton2;
    private ImageView boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delivery);

        finalizar();
        nextWindow();
        cart();
        profile();

    }

    public void finalizar() {
        btn1 = (Button) findViewById(R.id.finalizar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirm_delivery.this, CatalogoActivity.class));
            }
        });
    }

    public void nextWindow () {
        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirm_delivery.this, navegacion.class));
            }
        });
    }

    public void cart () {
        boton2 = (Button) findViewById(R.id.carrito);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirm_delivery.this, CarroDeCompras.class));
            }
        });
    }

    public void profile () {
        boton3 = (ImageView) findViewById(R.id.imageView5);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirm_delivery.this, perfil.class));
            }
        });
    }

}
