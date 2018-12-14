package com.example.oscarplaza.stride.Entidades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;


import com.example.oscarplaza.stride.R;

import java.util.ArrayList;

public class ObservacionAdapter extends ArrayAdapter<RespResult> {
    private Context mContext;
    private ArrayList<RespResult> moviesList = new ArrayList<RespResult>();
    public ObservacionAdapter(FragmentActivity activity, ArrayList<RespResult> arr) {
        super(activity,0,arr);
        mContext = activity;
        moviesList = arr;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        RespResult currentMovie = getItem(position);


        TextView tcantidadPuntos =  (TextView)listItem.findViewById(R.id.cantidad_puntos);
        tcantidadPuntos.setText(currentMovie.getScore().toString());
        return  listItem;

    }
}
