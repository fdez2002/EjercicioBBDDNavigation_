package com.example.ejerciciobbddnavigation.baseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ejerciciobbddnavigation.ui.matricular.MatricularFragment;

/**
 * Esta clase nos permite crear una base de datos y actualizar la estructura de tablas y datos iniciales
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {//hereda de SQLiteOpenHelper
    //En el constructor solo llamamos al constructor de la clase padre pasando los datos que llegan en los parámetros:
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Este metodo se le llama cuando la base de datos se crea por primera vez.
    // Aqui es donde se define la estructura de las tablas y se cargan eventualmente los datos iniciales
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(dni text primary key, nombre text, apellidos text, sexo text)");
    }
    //Este se llama cuando los datos deben de ser actualizados.
    //Tiene como objetivo eliminar tablas, añadir o hacer cualquier cosa que necesita para actualizarla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
