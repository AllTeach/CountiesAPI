package com.example.countiesapi;

import java.util.ArrayList;

public class Country {
    private String name;
    private String officialName;
    private String currency;
    private String language;

    public ArrayList<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<String> neighbours) {
        this.neighbours = neighbours;
    }

    private ArrayList<String> neighbours;


    public Country(String name, String officialName, String currency, String language, ArrayList<String> neighbours) {
        this.name = name;
        this.officialName = officialName;
        this.currency = currency;
        this.language = language;
        this.neighbours = neighbours;
    }

    public Country(String name, String officialName, String currency, String language) {
        this.name = name;
        this.officialName = officialName;
        this.currency = currency;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}