package com.example.oscarplaza.stride.Entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RespResult {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("observed")
    @Expose
    private Integer observed;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("hdop")
    @Expose
    private float hdop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObserved() {
        return observed;
    }

    public void setObserved(Integer observed) {
        this.observed = observed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public float getHdop() {
        return hdop;
    }

    public void setHdop(float hdop) {
        this.hdop = hdop;
    }
}
