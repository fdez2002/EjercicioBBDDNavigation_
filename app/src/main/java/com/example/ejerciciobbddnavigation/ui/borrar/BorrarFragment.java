package com.example.ejerciciobbddnavigation.ui.borrar;

import android.content.Context;
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
import android.widget.Toast;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.baseDeDatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentBorrarBinding;
import com.example.ejerciciobbddnavigation.ui.inicio.InicioViewModel;

public class BorrarFragment extends Fragment {

    private FragmentBorrarBinding binding;
    private Context thisContext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        thisContext = container.getContext();


        BorrarViewModel borrarViewModel =
                new ViewModelProvider(this).get(BorrarViewModel.class);

        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textViewBorrar;
        //borrarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonAceptarBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lo primero que hacemos en este método es crear un objeto de la clase que planteamos anteriormente y
                // le pasamos al constructor this (referencia del Activity actual), "administracion"
                // (es el nombre de la base de datos que crearemos en el caso que no exista) luego pasamos null y
                // un uno indicando que es la primer versión de la base de datos (en caso que cambiemos la estructura o agreguemos tablas
                // por ejemplo podemos pasar un dos en lugar de un uno para que se ejecute el método onUpgrade donde indicamos la nuestra estructura
                // de la base de datos)
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);
                // procedemos a crear un objeto de la clase SQLiteDataBase llamando al método getWritableDatabase
                // (la base de datos se abre en modo lectura y escritura).
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                //obtenemos el dni que el usuario ha introducido y los guardamos en una variable
                String dni = binding.editTextDniBorrar.getText().toString();
                //comprobamos que se hayan introducido datos
                if (!dni.isEmpty()){
                    int cantidad = baseDeDatos.delete("alumnos", "dni='"+dni+"'", null);
                    //cerramos la base de datos
                    baseDeDatos.close();
                    //Limpiamos el editText
                    binding.editTextDniBorrar.setText("");
                    //informamos al usuario de lo que se ha realizado dependiendo del resultado de la cantidad
                    if (cantidad ==1){
                        Toast.makeText(thisContext, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(thisContext, "No existe el alumno", Toast.LENGTH_SHORT).show();
                    }
                }  else{
                        Toast.makeText(thisContext, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
                }



            }
        });

        binding.buttonCancelarBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editTextDniBorrar.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}