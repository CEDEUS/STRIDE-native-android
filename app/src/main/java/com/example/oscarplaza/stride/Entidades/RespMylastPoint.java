package com.example.oscarplaza.stride.Entidades;

import com.example.oscarplaza.stride.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RespMylastPoint {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private Integer next;
    @SerializedName("previous")
    @Expose
    private String previous;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public RespResult getResults() {
        return results;
    }

    public void setResults(RespResult results) {
        this.results = results;
    }

    @SerializedName("results")
    @Expose
    private RespResult results;


}
