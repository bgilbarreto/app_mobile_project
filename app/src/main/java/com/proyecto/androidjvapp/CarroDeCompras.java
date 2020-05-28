package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CarroDeCompras extends AppCompatActivity {

    ListView lista;

    String [][] datos = {
            {"categoria","nombre","codigo","precio","cantidad"},
            {"categoria","nombre","codigo","precio","cantidad"},
            {"categoria","nombre","codigo","precio","cantidad"}
    };

    int[] datosImg = {R.drawable.pro,R.drawable.proteinas,R.drawable.vit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_de_compras);

        lista = (ListView) findViewById(R.id.lvCompras);

        lista.setAdapter(new Adaptador(this,datos,datosImg));

    }
    public void  siguinte(View View) {
        Intent siguinte = new Intent(this, envio.class);
        startActivity(siguinte);
    }
}
