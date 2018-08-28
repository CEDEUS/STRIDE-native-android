package com.example.oscarplaza.stride.Entidades;

import java.util.ArrayList;

public class ObservacionOld {



        public  ObservacionOld(ArrayList<PuntoVotadosOld> puntos){this.puntos = puntos;}

        private ArrayList<PuntoVotadosOld> puntos;



        public ArrayList<PuntoVotadosOld> getPuntos() {
            return puntos;
        }

        public void setPuntos(ArrayList<PuntoVotadosOld> puntos) {
            this.puntos = puntos;
        }

}
