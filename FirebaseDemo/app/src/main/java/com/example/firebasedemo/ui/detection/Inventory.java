package com.example.firebasedemo.ui.detection;

public class Inventory {

    String name;
    int daysUntilExpiration;
    int daysUntilNotification;

    public Inventory(String name, int daysUntilExpiration, int daysUntilNotification){
        this.name = name;
        this.daysUntilExpiration = daysUntilExpiration;
        this.daysUntilNotification = daysUntilNotification;
    }

    public String getName(){
        return name;
    }

    public int getDaysUntilExpiration(){
        return daysUntilExpiration;
    }

    public int getDaysUntilNotification(){
        return daysUntilNotification;
    }
}
