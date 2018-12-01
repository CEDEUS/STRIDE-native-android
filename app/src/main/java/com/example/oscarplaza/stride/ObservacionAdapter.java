package com.example.oscarplaza.stride;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.Observacion;

import java.util.ArrayList;
import java.util.List;

class ObservacionAdapter extends ArrayAdapter<Observacion> {
    private Context mContext;
    private List<Observacion> moviesList = new ArrayList<Observacion>();
    public ObservacionAdapter(Context listener, ArrayList<Observacion> results) {
        super(listener,0,results);
        mContext = listener;
        moviesList = results;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Observacion currentMovie = getItem(position);


        TextView tcantidadPuntos =  (TextView)listItem.findViewById(R.id.cantidad_puntos);
        tcantidadPuntos.setText(currentMovie.getCreate_by());


        return listItem;
    }
}
