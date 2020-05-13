package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProductoActivity extends AppCompatActivity {

    private Button btnAgregar,btnRegresoCatalogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        btnAgregar = findViewById(R.id.button);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Proceso completado
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                    }
                }, 4000);
            }
        });

        btnRegresoCatalogo = findViewById(R.id.buttonRegCatalogo);
        btnRegresoCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplication(), CatalogoActivity.class);
                startActivity(intencion);
            }
        });
    }
}
