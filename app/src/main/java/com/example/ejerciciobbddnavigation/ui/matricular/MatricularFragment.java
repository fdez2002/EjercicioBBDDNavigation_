package com.example.ejerciciobbddnavigation.ui.matricular;

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
import com.example.ejerciciobbddnavigation.databinding.FragmentMatricularBinding;

public class MatricularFragment extends Fragment {

    private FragmentMatricularBinding binding;
    private Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        thisContext = container.getContext();//guardamos en una variable el context para pasarlo al objeto
        MatricularViewModel matricularViewModel =
                new ViewModelProvider(this).get(MatricularViewModel.class);

        binding = FragmentMatricularBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        //matricularViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAceptMatricula.setOnClickListener(new View.OnClickListener() {
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
                //Guardamos en variables el contenido que el usuario haya introducido
                String dni = binding.editTextDniMatricula.getText().toString();
                String nombre = binding.editTextNombreMatricula.getText().toString();
                String apellidos = binding.editTextApellidosMatricula.getText().toString();
                String sexo = binding.spinnerMatriculaSexo.getAdapter().toString();
                //Creamos un objeto de la clase ContentValues y mediante el método put inicializamos todos los campos a cargar.
                ContentValues registro = new ContentValues();
                //Comprobamos antes  que los dtos no esten vacios
                if(!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()){
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);
                    //llamamos al método insert de la clase SQLiteDatabase pasando en el primer parámetro el nombre de la tabla,
                    // como segundo parámetro un null y por último el objeto de la clase ContentValues ya inicializado
                    BaseDeDatos.insert("alumnos", null, registro);
                    //Cerramos el metodo
                    BaseDeDatos.close();
                    //Limpiamos los editText
                    binding.editTextDniMatricula.setText("");
                    binding.editTextNombreMatricula.setText("");
                    binding.editTextApellidosMatricula.setText("");
                    //Informamos al usuario de que los datos se hayn introducido correctamente
                    Toast.makeText(thisContext, "Alumno guardado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    //En el caso de que algun campo este vacio, lo informamos al usuario
                    Toast.makeText(thisContext, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}