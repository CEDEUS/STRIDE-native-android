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
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.PuntoVotados;

import java.util.ArrayList;

public class adapterPoint extends ArrayAdapter<PuntoVotados> {
    private final Object mContext;

    public adapterPoint(Context listFragmentandDelete, ArrayList<PuntoVotados> data) {
        super(listFragmentandDelete,0,data);

        this.mContext= listFragmentandDelete;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Log.d("TAG", "getView: ");
        final Semaforo activity = (Semaforo) mContext;


        PuntoVotados user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        TextView tvnombre = (TextView) convertView.findViewById(R.id.txtnombre);
        TextView tvPresio = (TextView) convertView.findViewById(R.id.txtpresio);
        TextView tvCategory = (TextView) convertView.findViewById(R.id.txtcategory);
        convertView.setClickable(true);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Deletedatan(position);
                activity.changeTabs();


            }
        });

        tvCategory.setText(user.getCategory());
        tvnombre.setText(user.getVotacion());
        tvPresio.setText(getnameStrett(user.getLat(),user.getLng(),activity));
        return  convertView;

    }

    private String getnameStrett(double lat, double lng, Semaforo activity) {
        return activity.nameofstreet(lat,lng);
    }


}
