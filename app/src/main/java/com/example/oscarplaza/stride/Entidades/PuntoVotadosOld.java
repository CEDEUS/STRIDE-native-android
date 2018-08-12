package com.example.oscarplaza.stride.Entidades;

public class PuntoVotadosOld {
    public PuntoVotadosOld(String secuence_end, String votacion, int age, String ability, String sexo, String secuence, double lat, String created_by, double lon, double dop, String v) {
        this.secuence_end = secuence_end;
        this.score = votacion;
        this.age = age;
        this.ability = ability;
        this.sex = sexo;
        this.secuence = secuence;
        this.lat = lat;
        this.created_by = created_by;
        this.lon = lon;
        this.hdop = dop;
        this.version = v;
    }
    private String version;

    private double hdop;

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
}
