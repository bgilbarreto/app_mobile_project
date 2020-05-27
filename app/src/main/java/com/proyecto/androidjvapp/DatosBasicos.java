package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.content.ContentValues;

import android.os.Bundle;

public class DatosBasicos extends AppCompatActivity {

    private EditText et_firstname, et_lastname, et_email, et_age, et_weight, et_height;
    private Button btnSvProfile;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_basicos);

        et_firstname = (EditText)findViewById(R.id.editText6);
        et_lastname = (EditText)findViewById(R.id.editText7);
        et_email = (EditText)findViewById(R.id.editText9);
        et_age = (EditText)findViewById(R.id.editText13);
        et_weight = (EditText)findViewById(R.id.editText14);
        et_height = (EditText)findViewById(R.id.editText15);

        btnSvProfile = findViewById(R.id.btn_saveprofile);

        btnSvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progresdialog();
                showPDialogDP();

                //Proceso completado
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Registrar(null);
                        Toast.makeText(getBaseContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, 3000);
            }
        });
    }
    private void  showPDialogDP(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Guardando en DB");
        progressDialog.setMessage("Guardando...");
        progressDialog.show();
        //progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void progresdialog(){
        this.progressDialog =  new ProgressDialog(this);
    }

    //Método para guardar los datos del usuario
    public void Registrar(View view){
        adminSqliteOpenHelper admin = new adminSqliteOpenHelper(getBaseContext(),"administracion", null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String firstname = et_firstname.getText().toString();
        String lastname = et_lastname.getText().toString();
        String emailuser = et_email.getText().toString();
        String ageuser = et_age.getText().toString();
        String weightuser = et_weight.getText().toString();
        String heightuser = et_height.getText().toString();

        if(!firstname.isEmpty() && !lastname.isEmpty() && !emailuser.isEmpty() && !ageuser.isEmpty() && !weightuser.isEmpty() && !heightuser.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre",firstname);
            registro.put("apellido",lastname);
            registro.put("correo",emailuser);
            registro.put("edad",ageuser);
            registro.put("peso",weightuser);
            registro.put("estatura",heightuser);

            BaseDeDatos.insert("Clientes", null, registro);
            BaseDeDatos.close();
            et_firstname.setText("");
            et_lastname.setText("");
            et_email.setText("");
            et_age.setText("");
            et_weight.setText("");
            et_height.setText("");
        } else {
            Toast.makeText(getBaseContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
