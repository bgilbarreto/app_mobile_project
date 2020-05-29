package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class navegacion extends AppCompatActivity {

    private Button ver_ub, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        nextWindow();
        perfilM();

    }

    public void nextWindow () {

        ver_ub = (Button) findViewById(R.id.mis_compr);
        ver_ub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navegacion.this, mis_compras.class));
            }
        });
    }

    public void perfilM () {

        profile = (Button) findViewById(R.id.perfil);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navegacion.this, perfil.class));
            }
        });
    }

}
