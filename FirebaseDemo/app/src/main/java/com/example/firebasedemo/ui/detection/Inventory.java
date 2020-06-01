package com.example.firebasedemo.ui.detection;

public class Inventory {

    String name;
    String dateOfExpiration;
    int totalQuantity;


    public Inventory(String name, String dateOfExpiration, int totalQuantity){
        this.name = name;
        this.dateOfExpiration = dateOfExpiration;
        this.totalQuantity = totalQuantity;
    }

    public Inventory(){

    }

    public String getName(){
        return name;
    }

    public String getDateOfExpiration(){
        return dateOfExpiration;
    }

    public int getTotalQuantity(){
        return totalQuantity;
    }

}
