package com.example.sniper;

public class CaliberModel {

    private int id;
    private String Caliber;
    private float Diameter;

    CaliberModel( String caliber, float diameter){
        this.Caliber = caliber;
        this.Diameter = diameter;
    }

    public String getCaliber() {
        return Caliber;
    }

    public void setCaliber(String Caliber){
        this.Caliber = Caliber;
    }

    public float getDiameter() {
        return Diameter;
    }

    public void setDiameter(float Diameter){
        this.Diameter = Diameter;
    }
}
