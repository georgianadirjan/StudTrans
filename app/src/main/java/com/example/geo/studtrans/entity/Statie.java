package com.example.geo.studtrans.entity;

/**
 * Created by Geo on 1/10/2015.
 */
public class Statie {

    private String name;
    private Double cx;
    private Double cy;
    private int id;

    public Double getCx(){
        return cx;
    }
    public Double getCy(){
        return cy;
    }
    public String getName(){
        return name;
    }


    public int getId(){
        return id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCx(Double cx) {
        this.cx = cx;
    }

    public void setCy(Double cy) {
        this.cy = cy;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Statie{" +
                "name='" + name + '\'' +
                ", cx=" + cx +
                ", cy=" + cy +
                ", id=" + id +
                '}';
    }
}
