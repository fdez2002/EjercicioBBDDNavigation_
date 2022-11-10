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
        thisContext = container.getContext();
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
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                String dni = binding.editTextDniMatricula.getText().toString();
                String nombre = binding.editTextNombreMatricula.getText().toString();
                String apellidos = binding.editTextApellidosMatricula.getText().toString();
                String sexo = binding.spinnerMatriculaSexo.getAdapter().toString();

                ContentValues registro = new ContentValues();

                if(!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()){
                    registro.put("dni", dni);
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("sexo", sexo);

                    BaseDeDatos.insert("alumnos", null, registro);

                    BaseDeDatos.close();

                    binding.editTextDniMatricula.setText("");
                    binding.editTextNombreMatricula.setText("");
                    binding.editTextApellidosMatricula.setText("");

                    Toast.makeText(thisContext, "Alumno guardado correctamente", Toast.LENGTH_SHORT).show();
                }else{
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