package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class envio extends AppCompatActivity {

    private Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio);

        nextWindow();

    }

    public void nextWindow () {
        pagar = (Button) findViewById(R.id.pagoVista);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(envio.this, ubicacion_texto.class));
            }
        });
    }

}
