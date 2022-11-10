package com.example.ejerciciobbddnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejerciciobbddnavigation.objeto.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {//Clase que extiende de arrayAdapter
    private Context mContext;
    private int mresource;
    //private List<Persona> personalist;

    public PersonaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Persona> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mresource = resource;
        //this.personalist = objects;
    }

    //Con este metodo controlamos la parte logica del dibujado de ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mresource, parent, false);

        TextView textDni = convertView.findViewById(R.id.textViewDniLista);
        TextView textNombre = convertView.findViewById(R.id.textViewNombreLista);
        TextView textApellidos = convertView.findViewById(R.id.textViewApellidosList);
        TextView textSexo = convertView.findViewById(R.id.textViewSexoList);


        textDni.setText(getItem(position).getDni());
        textNombre.setText(getItem(position).getNombre());
        textApellidos.setText(getItem(position).getApellidos());
        textSexo.setText(getItem(position).getSexo());


        return convertView;
    }
}
