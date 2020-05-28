package com.proyecto.androidjvapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater= null;

    Context contexto;
    String[][] datos;
    int[] datosImg;

    public Adaptador(Context context, String[][] datos, int[] imagenes){

        this.contexto = context;
        this.datos = datos;
        this.datosImg =  imagenes;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista,null);

        TextView categoria = (TextView) vista.findViewById(R.id.textView_categoria);
        TextView nombre = (TextView) vista.findViewById(R.id.textView_nombre);
        TextView codigo = (TextView) vista.findViewById(R.id.textView_codigo);
        TextView precio = (TextView) vista.findViewById(R.id.textView_precio);
        TextView cantidad = (TextView) vista.findViewById(R.id.textView_cantidad);

        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView_compra);

        categoria.setText(datos [i][0]);
        nombre.setText(datos[i][1]);
        codigo.setText(datos[i][2]);
        precio.setText((datos[i][3]));
        cantidad.setText(datos[i][4]);

        imagen.setImageResource(datosImg[i]);

        // genera una etiqueta para la imagen
        /*
        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });


         */


        return vista;
    }
}
