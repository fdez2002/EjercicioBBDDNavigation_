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
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);

                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                String dni = binding.editTextDniModificar.getText().toString();
                String nombre = binding.editTextNombreModificar.getText().toString();
                String apellidos = binding.editTextApellidosModificar.getText().toString();
                String sexo = binding.spinnerModificar.getAdapter().toString();

                if(!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()){
                    ContentValues registro = new ContentValues();

                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);

                    int cantidad = BaseDeDatos.update("alumnos", registro, "dni="+dni, null);

                    if (cantidad ==1){
                        Toast.makeText(thisContext, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(thisContext, "No existe el alumno", Toast.LENGTH_SHORT).show();
                    }
                } else{
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

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);

        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String dni = binding.editTextDniModificar.getText().toString();
        String nombre = binding.editTextNombreModificar.getText().toString();
        String apellidos = binding.editTextApellidosModificar.getText().toString();
        String sexo = binding.spinnerModificar.getAdapter().toString();

        if(!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("dni", dni);
            registro.put("nombre", nombre);
            registro.put("apellidos", apellidos);
            registro.put("sexo", sexo);

            int cantidad = BaseDeDatos.update("alumnos", registro, "dni="+dni, null);

            if (cantidad ==1){
                Toast.makeText(thisContext, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(thisContext, "No existe el producto", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(thisContext, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
 }

