package com.example.oscarplaza.stride;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.oscarplaza.stride.Entidades.PuntoVotados;

import java.util.ArrayList;

public class adapterPoint extends ArrayAdapter<PuntoVotados> {
    public adapterPoint(Context listFragmentandDelete, ArrayList<PuntoVotados> data) {
        super(listFragmentandDelete,0,data);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("TAG", "getView: ");

        PuntoVotados user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }
        TextView tvnombre = (TextView) convertView.findViewById(R.id.txtnombre);
        TextView tvPresio = (TextView) convertView.findViewById(R.id.txtpresio);

        tvnombre.setText(user.getVotacion());
        tvPresio.setText(String.valueOf(user.getHdop()));
        return  convertView;

    }
}
