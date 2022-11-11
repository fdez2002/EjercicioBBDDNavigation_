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
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(thisContext, "administracion", null, 1);

        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor cursor = baseDeDatos.rawQuery("select * from alumnos", null);

        String dni, nombre, apellidos, sexo;

        listPersonas = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{
                dni = cursor.getString(0);
                nombre = cursor.getString(1);
                apellidos = cursor.getString(2);
                sexo = cursor.getString(3);
                listPersonas.add(new Persona(dni, nombre, apellidos, sexo));
            }while(cursor.moveToNext());

        }

        PersonaAdapter personaadaptor = new PersonaAdapter(thisContext, R.layout.list_row, (ArrayList<Persona>) listPersonas);

        binding.listView.setAdapter(personaadaptor);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}