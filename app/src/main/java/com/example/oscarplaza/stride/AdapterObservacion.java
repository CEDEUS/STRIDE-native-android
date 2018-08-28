package com.example.oscarplaza.stride;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.oscarplaza.stride.Entidades.PuntoVotados;

import java.util.ArrayList;

public class AdapterObservacion extends RecyclerView.Adapter<AdapterObservacion.PuntosVotadosViewHolder> {
    public AdapterObservacion(ArrayList<PuntoVotados> data) {
    }

    @NonNull
    @Override
    public PuntosVotadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PuntosVotadosViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PuntosVotadosViewHolder extends RecyclerView.ViewHolder {
        public PuntosVotadosViewHolder(View itemView) {
            super(itemView);
        }
    }
}
