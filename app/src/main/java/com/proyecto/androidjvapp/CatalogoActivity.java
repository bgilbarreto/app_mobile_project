package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.content.Context;

public class CatalogoActivity extends AppCompatActivity {

    private Button btnFb,btnWa, btnIg, ver_ub, btn8;
    private ImageView imProducto, imglogo;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        nextWindow();
        nextWQuestions();

        btnFb = findViewById(R.id.buttonFacebook);
        btnIg = findViewById(R.id.buttonInstagram);
        btnWa = findViewById(R.id.buttonWhatsapp);
        btn8 = findViewById(R.id.button8);

        imProducto = findViewById(R.id.imageViewProducto);
        imProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplication(), ProductoActivity.class);
                startActivity(intencion);
            }
        });
        imglogo = findViewById(R.id.imageView5);
        imglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateperfil = new Intent(getApplication(), DatosBasicos.class);
                startActivity(updateperfil);
            }
        });


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
                        //intent facebook
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(getBaseContext());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        });

        btnIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progresdialog();
                showPDialogIG();
                Uri uri = Uri.parse("http://instagram.com/_u/johan_s_85");
                //Proceso completado
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
                        Intent intentIg = new Intent(Intent.ACTION_VIEW, uri);
                        intentIg.setPackage("com.instagram.android");
                        startActivity(intentIg);
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        });

        btnWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progresdialog();
                showPDialogWa();

                //Proceso completado
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
                        openWhatsApp();
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        });


    }

    //abrir Facebook
    public static String FACEBOOK_URL = "https://www.facebook.com/Johans-Prote%C3%ADnas-y-vitaminas-1738612086383388/";
    public static String FACEBOOK_PAGE_ID = "1738612086383388";

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    //abrir whatsapp
    private void openWhatsApp() {
        String smsNumber = "573133279919";
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hola, estoy interesad@ en tus productos.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }

    private void progresdialog(){
        this.progressDialog =  new ProgressDialog(this);
    }


    private void  showPDialog(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Conectando con Facebook");
        progressDialog.setMessage("Conectando...");
        progressDialog.show();
        //progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void  showPDialogWa(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Conectando con Whatsapp");
        progressDialog.setMessage("Conectando...");
        progressDialog.show();
        //progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void  showPDialogIG(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Conectando con Instagram");
        progressDialog.setMessage("Conectando...");
        progressDialog.show();
        //progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void nextWindow () {

        ver_ub = (Button) findViewById(R.id.menu_lat);
        ver_ub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CatalogoActivity.this, ubicacion_1.class));
            }
        });
    }


    public void nextWQuestions () {

        btn8 = (Button) findViewById(R.id.button8);
       btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CatalogoActivity.this, Preguntas.class));
            }
        });
    }

}
