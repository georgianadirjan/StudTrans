package com.example.geo.studtrans.entity;

import com.example.geo.studtrans.entity.Statie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geo on 1/10/2015.
 */
public class Traseu {

    private Map<Statie,Integer> statii = new HashMap<Statie,Integer>();
    private String name;
    private int id;

    public Map<Statie,Integer> getStatii() {
        return statii;
    }

    public void setStatii(Map<Statie,Integer> statii) {
        this.statii = statii;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Traseu{" +
                "statii=" + statii +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
