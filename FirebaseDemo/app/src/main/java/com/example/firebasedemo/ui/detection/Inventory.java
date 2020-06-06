package com.example.firebasedemo.ui.detection;

import android.graphics.Bitmap;

public class Inventory {

    String name;
    String dateOfExpiration;
    int totalQuantity;

    int month;
    int dayOfMonth;
    int year;



    public Inventory(String name, String dateOfExpiration, int totalQuantity, int month, int dayOfMonth, int year){
        this.name = name;
        this.dateOfExpiration = dateOfExpiration;
        this.totalQuantity = totalQuantity;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.year = year;
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

    public int setQuantity(int x){
        totalQuantity = x;
        return totalQuantity;
    }

    public int getDayOfMonth(){
        return dayOfMonth;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public String toString(){
        return "There are " + getTotalQuantity() + " " + getName() + " that expire on " + getDateOfExpiration();
    }



}
