package com.example.oscarplaza.stride.Entidades;

import java.util.ArrayList;

public class Observacion {

    public  Observacion(ArrayList<PuntoVotados> puntos){this.puntos = puntos;}

     private ArrayList<PuntoVotados> puntos;



    public ArrayList<PuntoVotados> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<PuntoVotados> puntos) {
        this.puntos = puntos;
    }
}
