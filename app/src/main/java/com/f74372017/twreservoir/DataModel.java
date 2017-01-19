package com.f74372017.twreservoir;

/**
 * Created by andy6804tw on 2017/1/19.
 */

public class DataModel {
    private   String Water;
    private  String Day;
    private  String Update;
    private  String Down;
    private  String Name;
    private  String Percentage;
    private  String Position;

    public DataModel(String water, String day, String update, String down, String name, String percentage,String position) {
        Water = water;
        Day = day;
        Update = update;
        Down = down;
        Name = name;
        Percentage = percentage;
        Position=position;
    }


    public String getWater() {
        return Water;
    }

    public String getDay() {
        return Day;
    }

    public String getUpdate() {
        return Update;
    }

    public String getDown() {
        return Down;
    }

    public String getName() {
        return Name;
    }

    public String getPercentage() {
        return Percentage;
    }

    public String getPosition() {
        return Position;
    }
}
