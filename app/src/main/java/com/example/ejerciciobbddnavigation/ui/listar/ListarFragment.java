package com.example.ejerciciobbddnavigation.ui.listar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ejerciciobbddnavigation.PersonaAdapter;
import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.baseDeDatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentListarBinding;
import com.example.ejerciciobbddnavigation.objeto.Persona;
import com.example.ejerciciobbddnavigation.ui.matricular.MatricularViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    List<Persona> listPersonas;
    private Context thisContext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        thisContext = container.getContext();

        ListarViewModel listarViewModel =
                new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.tvListar;
        //listarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Lo primero que hacemos en este método es crear un objeto de la clase que planteamos anteriormente y
        // le pasamos al constructor this (referencia del Activity actual), "administracion"
        // (es el nombre de la base de datos que crearemos en el caso que no exista) luego pasamos null y
        // un uno indicando que es la primer versión de la base de datos (en caso que cambiemos la estructura o agreguemos tablas
        // por ejemplo podemos pasar un dos en lugar de un uno para que se ejecute el método onUpgrade donde indicamos la nuestra estructura
        // de la base de datos)
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);
        // procedemos a crear un objeto de la clase SQLiteDataBase llamando al método getReadableDatabase
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        //Seguidamente definimos una variable de la clase Cursor y la inicializamos con el valor devuelto por el método llamado rawQuery.
        Cursor cursor = baseDeDatos.rawQuery("select * from alumnos", null);

        String dni, nombre, apellidos, sexo;

        listPersonas = new ArrayList<>();
        //La clase Cursos almacena en este caso una fila o cero filas (una en caso que hayamos ingresado un dni existente en la tabla votantes),
        // llamamos al método moveToFirst() de la clase Cursos y retorna true en caso de existir una persona con el dni ingresado,
        // en caso contrario retorna cero.
        if (cursor.moveToFirst()){
            do{
                //Recuperamos los datos con getString pasandole la posicion del campo a recuperar
                dni = cursor.getString(0);
                nombre = cursor.getString(1);
                apellidos = cursor.getString(2);
                sexo = cursor.getString(3);
                listPersonas.add(new Persona(dni, nombre, apellidos, sexo));
            }while(cursor.moveToNext());

        }
        //Creamos el adapter pasandole el context, el formato de lita  el arrau con el contenido
        PersonaAdapter personadaptor = new PersonaAdapter(thisContext, R.layout.list_row, (ArrayList<Persona>) listPersonas);

        binding.listView.setAdapter(personadaptor);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}