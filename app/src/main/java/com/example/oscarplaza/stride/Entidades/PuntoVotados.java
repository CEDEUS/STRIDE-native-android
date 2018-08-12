package com.example.oscarplaza.stride.Entidades;

import java.util.Date;

public class PuntoVotados {



    private String score;
    private double hdop;

    public PuntoVotados(String score, double hdop, double lon, double lat) {
        this.score = score;
        this.hdop = hdop;
        this.lon = lon;
        this.lat = lat;
    }

    public double getHdop() {
        return hdop;

    }

    public void setHdop(int hdop) {
        this.hdop = hdop;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }





    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lon;
    }

    public void setLng(double lng) {
        this.lon = lng;
    }

    private double lon=0;
    private double lat=0;


    public PuntoVotados(String votacion, double lat, double lng) {
        this.score = votacion;

        this.lat = lat;
        this.lon = lng;
    }

    public PuntoVotados(String votacion) {
        this.score = votacion;

    }
    public PuntoVotados(){}


    public String getVotacion() {
        return score;
    }

    public void setVotacion(String votacion) {
        this.score = votacion;
    }





}
