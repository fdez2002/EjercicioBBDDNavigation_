package com.example.ejerciciobbddnavigation.ui.modificar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ejerciciobbddnavigation.baseDeDatos.AdminSQLiteOpenHelper;
import com.example.ejerciciobbddnavigation.databinding.FragmentModificarBinding;

public class ModificarFragment extends Fragment {

    private FragmentModificarBinding binding;
    private Context thisContext;
    //comprobamos el dni
    private  boolean validarDni(String dni) {

        return dni.matches("^[0-9]{7,8}[T|R|W|A|G|M|Y|F|P|D|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E]$");

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        thisContext = container.getContext();

        ModificarViewModel modificarViewModel =
                new ViewModelProvider(this).get(ModificarViewModel.class);

        binding = FragmentModificarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textSlideshow;
        //modificarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAccepModificar.setOnClickListener(new View.OnClickListener() {
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
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
                //Guardamos en variables los nuevos datos que haya introducido el usuario
                String dni = binding.editTextDniModificar.getText().toString();
                String nombre = binding.editTextNombreModificar.getText().toString();
                String apellidos = binding.editTextApellidosModificar.getText().toString();
                String sexo = binding.spinnerModificar.getSelectedItem().toString();

                //Comprobamos que esos datos no esten vacios
                if(!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()){
                    //Creamos un objeto de la clase ContentValues y mediante el método put inicializamos todos los campos a modificar.
                    ContentValues registro = new ContentValues();
                    //
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);
                    //Luego se llama al método update de la clase SQLiteDatabase pasando el nombre de la tabla,
                    // el objeto de la clase ContentValues y la condición del where (el cuanto parámetro en este ejemplo no se lo emplea)
                    int cantidad = BaseDeDatos.update("alumnos", registro, "dni='"+dni+"'", null);

                    //Dependiendo del resultado de cantidad informamos al usuario
                    if (cantidad ==1){
                        limpiar();
                        Toast.makeText(thisContext, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(thisContext, "No existe el alumno", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(thisContext, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonCacelModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void limpiar(){
        binding.editTextDniModificar.setText("");
        binding.editTextNombreModificar.setText("");
        binding.editTextApellidosModificar.setText("");
    }

 }

