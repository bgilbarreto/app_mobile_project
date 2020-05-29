package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Pago extends AppCompatActivity {

    private ImageView ver_ub, ver_pse, ver_trans;
    private Button boton1, boton2;
    private ImageView boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        nextWindow();
        pse();
        transferencia();
        navegation();
        cart();
        profile();

    }

    public void nextWindow () {
        ver_ub = (ImageView) findViewById(R.id.imageView52);
        ver_ub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, pago_pse.class));
            }
        });
    }

    public void pse () {
        ver_pse = (ImageView) findViewById(R.id.imageView49);
        ver_pse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, Pago_Debito.class));
            }
        });
    }

    public void transferencia () {
        ver_trans = (ImageView) findViewById(R.id.imageView51);
        ver_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, pago_credito.class));
            }
        });
    }

    public void navegation () {
        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, navegacion.class));
            }
        });
    }

    public void cart () {
        boton2 = (Button) findViewById(R.id.carrito);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, CarroDeCompras.class));
            }
        });
    }

    public void profile () {
        boton3 = (ImageView) findViewById(R.id.imageView5);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pago.this, perfil.class));
            }
        });
    }

}
