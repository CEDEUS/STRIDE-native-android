package com.example.oscarplaza.stride.Entidades;

import java.util.Date;

public class PuntoVotados {
    public String getSecuence_end() {
        return secuence_end;
    }

    public void setSecuence_end(String secuence_end) {
        this.secuence_end = secuence_end;
    }

    public String getSecuence() {
        return secuence;
    }

    public void setSecuence(String secuence) {
        this.secuence = secuence;
    }

    private String secuence_end;
    private int votacion;
    private int age;
    private int ability;
    private String sexo;
    private String secuence;
    private double lat=0;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    private String created_by;

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


    public PuntoVotados(int votacion, int age, int ability, String sexo, String secuence, double lat, double lng) {
        this.votacion = votacion;
        this.age = age;
        this.ability = ability;
        this.sexo = sexo;
        this.secuence = secuence;

        this.lat = lat;
        this.lon = lng;
    }

    public PuntoVotados(int votacion, int age, int ability, String sexo) {
        this.votacion = votacion;
        this.age = age;
        this.ability = ability;
        this.sexo = sexo;
    }
    public PuntoVotados(){}

    public PuntoVotados(int votacion, int age, int ability, String sexo, String secuence) {
        this.votacion = votacion;
        this.age = age;
        this.ability = ability;
        this.sexo = sexo;
        secuence = secuence;
    }

    public int getVotacion() {
        return votacion;
    }

    public void setVotacion(int votacion) {
        this.votacion = votacion;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getButtonpress() {
        return secuence;
    }

    public void setButtonpress(String buttonpress) {
        secuence = buttonpress;
    }
}
