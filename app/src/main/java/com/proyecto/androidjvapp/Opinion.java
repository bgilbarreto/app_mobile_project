package com.proyecto.androidjvapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Opinion extends AppCompatActivity {
    private Button alerbtn;
    WebView myBrowser;
    Button calificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        alerbtn = (Button) findViewById(R.id.Enviar);
        calificar = (Button) findViewById(R.id.Calificar);
        alerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Opinion.this);
                alerta.setMessage("¿Deseas enviar el mensaje?")
                        .setCancelable(true)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent miIntent=new Intent(Opinion.this,EncuestaP.class);
                                startActivity(miIntent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Confirmar");
                titulo.show();

            }
        });

        calificar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder alerta2 = new AlertDialog.Builder(Opinion.this);
                alerta2.setTitle("CALIFICAR");
                alerta2.setMessage("Tu comentario es importante para nosotros, por favor valoralo o deja un comentario para mejorar la aplicacion"+"Gracias por tu ayuda")
                        .setCancelable(true)
                        .setPositiveButton("Ir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.lite&hl=es_CO") ) );
                            }
                        })
                        .setNegativeButton("En otro momento", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog titulo = alerta2.create();
                titulo.setTitle("CALIFICAR");
                titulo.show();
            }
        });

    }
}
