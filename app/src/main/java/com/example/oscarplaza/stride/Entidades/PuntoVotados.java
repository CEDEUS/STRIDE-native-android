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
    private String score;
    private int age;
    private String ability;
    private String sex;
    private String secuence;
    private double lat=0;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    private String create_by;

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


    public PuntoVotados(String votacion, int age, String ability, String sexo, String secuence, double lat, double lng) {
        this.score = votacion;
        this.age = age;
        this.ability = ability;
        this.sex = sexo;
        this.secuence = secuence;
        this.lat = lat;
        this.lon = lng;
    }

    public PuntoVotados(String votacion, int age, String ability, String sexo) {
        this.score = votacion;
        this.age = age;
        this.ability = ability;
        this.sex = sexo;
    }
    public PuntoVotados(){}

    public PuntoVotados(String votacion, int age, String ability, String sexo, String secuence) {
        this.score = votacion;
        this.age = age;
        this.ability = ability;
        this.sex = sexo;
        secuence = secuence;
    }

    public String getVotacion() {
        return score;
    }

    public void setVotacion(String votacion) {
        this.score = votacion;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getSexo() {
        return sex;
    }

    public void setSexo(String sexo) {
        this.sex = sexo;
    }

    public String getButtonpress() {
        return secuence;
    }

    public void setButtonpress(String buttonpress) {
        secuence = buttonpress;
    }
    public String getCreat(String id)
    {

        return getCreate_by();
    }
}
