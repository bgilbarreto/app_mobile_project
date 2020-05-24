package com.proyecto.androidjvapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class adminSqliteOpenHelper extends SQLiteOpenHelper {

    public adminSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {

        dataBase.execSQL(
                "create table Departamentos" +
                        "(codigo int primary key, " +
                        "nombre text not null)");

        dataBase.execSQL(
                "create table Tipo_Producto" +
                        "(codigo int primary key, " +
                        "nombre text not null, " +
                        "descripcion text not null)");

        dataBase.execSQL("create table Entidades_Bancarias" +
                "(codigo int primary key, " +
                "nombre text not null)");

        dataBase.execSQL
                ("create table Tipos_Envio(" +
                        "codigo int primary key, " +
                        "nombre text not null, " +
                        "precio int not null)");

        dataBase.execSQL
                ("create table Ciudades(" +
                        "condigo int primary key, " +
                        "departamento int not null, " +
                        "nombre text not null, " +
                        "foreign key (departamento) references Departamentos(codigo))");

        dataBase.execSQL
                ("create table Tiendas(" +
                        "codigo int primary key, " +
                        "nombre text not null, " +
                        "caracteristicas text, " +
                        "ciudad int, direccion " +
                        "text not null, " +
                        "foreign key (ciudad) references Ciudad(codigo))");

        dataBase.execSQL
                ("create table Actividad(" +
                        "codigo int primary key, " +
                        "estado text not null)");

        dataBase.execSQL(
                "create table Clientes(" +
                        "cedula int primary key, " +
                        "nombre text not null, " +
                        "apellido text not null, " +
                        "correo text, " +
                        "edad int not null, " +
                        "peso int, " +
                        "estatura int, " +
                        "numero int not null , " +
                        "estado int, " +
                        "promo int, "+
                        "foreign key (estado) references Actividad(codigo))");

        dataBase.execSQL(
                "create table Ubicaciones(" +
                        "codigo int primary key, " +
                        "cedula int not null, " +
                        "direccion text not null, " +
                        "foreign key (cedula) references Clientes(cedula))");

        dataBase.execSQL(
                "create table Telefonos(" +
                        "codigo int primary key, " +
                        "cedula int not null, " +
                        "numero int not null, " +
                        "foreign key (cedula) references Clientes(cedula))");

        dataBase.execSQL(
                "create table Descuento(" +
                        "codigo int primary key, " +
                        "porcentaje int not null)");

        dataBase.execSQL(
                "create table Productos(" +
                        "codigo int primary key, " +
                        "tienda int not null, " +
                        "descuento int not null, " +
                        "tipo_producto int not null, " +
                        "nombre text not null, " +
                        "precio int not null, " +
                        "cantidad int not null, " +
                        "precio_venta int not null, " +
                        "caracteristicas text, " +
                        "imagen BLOB, " +
                        "foreign key (tienda) references Tiendas(codigo), " +
                        "foreign key (tipo_producto) references Tipo_Producto(codigo), " +
                        "foreign key (descuento) references Descuento(codigo))");

        dataBase.execSQL(
                "create table Compras_Envios(" +
                        "codigo int primary key, " +
                        "cliente int not null, " +
                        "entidad_bancaria int not null, " +
                        "tipo_envio int not null, " +
                        "num_referencia int, " +
                        "fech_pedido date not null, " +
                        "fech_envio date not null, " +
                        "foreign key (cliente) references Clientes(cedula), " +
                        "foreign key (entidad_bancaria) references Entidades_Bancarias(codigo), " +
                        "foreign key (tipo_envio) references Tipos_Envio(codigo))");

        dataBase.execSQL(
                "create table Producto_X_Compra(" +
                        "producto int not null, " +
                        "compra_envio int not null, " +
                        "foreign key (producto) references Productos(codigo), " +
                        "foreign key (compra_envio) references Compras_Envios(codigo))");

        dataBase.execSQL(
                "create table Comentarios(" +
                        "codigo int primary key, " +
                        "producto int not null, " +
                        "comentario text, " +
                        "calificacion int not null, " +
                        "foreign key (producto) references Productos(codigo))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
