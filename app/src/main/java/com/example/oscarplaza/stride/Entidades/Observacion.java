package com.example.oscarplaza.stride.Entidades;

import java.util.ArrayList;

public class Observacion {
    private int age;
    private String ability;
    private String sex;
    private String create_by;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<PuntoVotados> getData() {
        return data;
    }

    public void setData(ArrayList<PuntoVotados> data) {
        this.data = data;
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

    public  Observacion(ArrayList<PuntoVotados> puntos){this.data = puntos;}

     private ArrayList<PuntoVotados> data;





}
