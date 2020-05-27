package com.proyecto.androidjvapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Miscompras extends AppCompatActivity {
    private EditText codigo, fecha, costos, codigo2;
    private Button btn8;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_compras);

        codigo =(EditText)findViewById(R.id.codigo);
        codigo2 =(EditText)findViewById(R.id.cod);
        fecha =(EditText)findViewById(R.id.fecha);
        costos =(EditText)findViewById(R.id.costos);

        nextWQuestions();
    }


    public void buscar(View view){
        adminSqliteOpenHelper admin=new adminSqliteOpenHelper(this,"administracion", null, 1);

        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String ncodigo =codigo.getText().toString();

        if(!ncodigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select codigo, fech_pedido, num_referencia from Compras_Envios where codigo"+ ncodigo, null);

            if(fila.moveToFirst()){
                codigo2.setText(fila.getString(0));
                fecha.setText(fila.getString(1));
                costos.setText(fila.getString(2));


                BaseDeDatos.close();
            }else {
                Toast.makeText(this, "no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "introducir codigo", Toast.LENGTH_SHORT).show();
        }
    }




    public void nextWQuestions () {

        btn8 = (Button) findViewById(R.id.button);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Miscompras.this, Opinion.class));
            }
        });
    }
}