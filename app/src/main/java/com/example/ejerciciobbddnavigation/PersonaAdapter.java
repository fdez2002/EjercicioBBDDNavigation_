package com.example.ejerciciobbddnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejerciciobbddnavigation.objeto.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {//Clase que extiende de arrayAdapter
    private Context mContext;
    private int mresource;
    //private List<Persona> personalist;
    //Constructor donde le pasamos el contexto, de donde obtiene los datos y el resource que va a ser el layout de cada item
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
        //Instanciamos el archivo xml que le pasamos en el main
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mresource, parent, false);
        //Asignamos los elementos con el id del layaut pasado en el main
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewIcon);
        TextView textDni = convertView.findViewById(R.id.textViewDniLista);
        TextView textNombre = convertView.findViewById(R.id.textViewNombreLista);
        TextView textApellidos = convertView.findViewById(R.id.textViewApellidosList);
        TextView textSexo = convertView.findViewById(R.id.textViewSexoList);

        //Le asignamos los datos que va a tener la vista, con la posicion del elemento

        textDni.setText(getItem(position).getDni());
        textNombre.setText(getItem(position).getNombre());
        textApellidos.setText(getItem(position).getApellidos());
        textSexo.setText(getItem(position).getSexo());
        if(textSexo.getText().equals("Hombre")){
            imageView.setImageResource(R.drawable.chico);
        }else if(textSexo.getText().equals("Mujer")){
            imageView.setImageResource(R.drawable.mujer);
        }



        return convertView;//Retornamos la vista
    }
}
