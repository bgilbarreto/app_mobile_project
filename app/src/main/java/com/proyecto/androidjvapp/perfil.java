package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class perfil extends AppCompatActivity {

    private Button next, photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nextWindow();
        takePhto();
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

}
