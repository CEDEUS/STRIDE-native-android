package com.example.oscarplaza.stride.Entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespStatics {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("date_joined")
    @Expose
    private String dateJoined;
    @SerializedName("groups")
    @Expose
    private List<String> groups = null;
    @SerializedName("total_observed_person")
    @Expose
    private Integer totalObservedPerson;
    @SerializedName("total_points_voted")
    @Expose
    private Integer totalPointsVoted;
    @SerializedName("today_observed_person")
    @Expose
    private Integer todayObservedPerson;
    @SerializedName("days_surveyed")
    @Expose
    private Integer daysSurveyed;

    public RespStatics() {
    }

    public RespStatics(String email, Integer id, String username, String dateJoined, List<String> groups, Integer totalObservedPerson, Integer totalPointsVoted, Integer todayObservedPerson, Integer daysSurveyed) {
        this.email = email;
        this.id = id;
        this.username = username;
        this.dateJoined = dateJoined;
        this.groups = groups;
        this.totalObservedPerson = totalObservedPerson;
        this.totalPointsVoted = totalPointsVoted;
        this.todayObservedPerson = todayObservedPerson;
        this.daysSurveyed = daysSurveyed;
    }

    public RespStatics(String email, Integer id, String username, String dateJoined, Integer totalObservedPerson, Integer totalPointsVoted, Integer todayObservedPerson, Integer daysSurveyed) {
        this.email = email;
        this.id = id;
        this.username = username;
        this.dateJoined = dateJoined;
        this.totalObservedPerson = totalObservedPerson;
        this.totalPointsVoted = totalPointsVoted;
        this.todayObservedPerson = todayObservedPerson;
        this.daysSurveyed = daysSurveyed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Integer getTotalObservedPerson() {
        return totalObservedPerson;
    }

    public void setTotalObservedPerson(Integer totalObservedPerson) {
        this.totalObservedPerson = totalObservedPerson;
    }

    public Integer getTotalPointsVoted() {
        return totalPointsVoted;
    }

    public void setTotalPointsVoted(Integer totalPointsVoted) {
        this.totalPointsVoted = totalPointsVoted;
    }

    public Integer getTodayObservedPerson() {
        return todayObservedPerson;
    }

    public void setTodayObservedPerson(Integer todayObservedPerson) {
        this.todayObservedPerson = todayObservedPerson;
    }

    public Integer getDaysSurveyed() {
        return daysSurveyed;
    }

    public void setDaysSurveyed(Integer daysSurveyed) {
        this.daysSurveyed = daysSurveyed;
    }
}
