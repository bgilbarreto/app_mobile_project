package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CatalogoActivity extends AppCompatActivity {

    private Button btnFb;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        btnFb = findViewById(R.id.buttonFacebook);
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progresdialog();
                showPDialog();

                //Proceso completado
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, 4000);
            }
        });


    }


    private void progresdialog(){
        this.progressDialog =  new ProgressDialog(this);
    }


    private void  showPDialog(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("");
        progressDialog.setMessage("Conectando...");
        progressDialog.show();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


    }
}
