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
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);

                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                String dni = binding.editTextDniBorrar.getText().toString();

                if (!dni.isEmpty()){
                    int cantidad = BaseDeDatos.delete("alumnos", "dni="+dni, null);

                    BaseDeDatos.close();

                    if (cantidad ==1){
                        Toast.makeText(thisContext, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(thisContext, "No existe el producto", Toast.LENGTH_SHORT).show();
                    }
                }  else{
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