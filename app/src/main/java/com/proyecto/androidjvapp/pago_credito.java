package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pago_credito extends AppCompatActivity {

    private Button ver_ub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_credito);

        nextWindow();

    }

    public void nextWindow () {

        ver_ub = (Button) findViewById(R.id.confirm);
        ver_ub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pago_credito.this, confirm_delivery.class));
            }
        });
    }

}
