package com.example.calendarapp;

public class Person {
    String name;
    String info;
    int imageResourceId;


    public String getName(){
        return name;
    }
    public String getInfo(){
        return info;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    public Person(String name, String info, int imageResourceId){
        this.name = name;
        this.info = info;
        this.imageResourceId = imageResourceId;
    }
}
