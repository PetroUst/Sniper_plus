package com.example.sniper;


public class BulletModel {

    private int id;
    private String bulletName;
    private String bulletCal;
    private float bulletWeight;
    private float bulletG1;
    private float bulletG7;
    private float bulletStart_speed;



    BulletModel(int id, String name, String caliber, float weight, float G1, float G7, float start_speed){
        this.id = id;
        this.bulletName = name;
        this.bulletCal = caliber;
        this.bulletWeight = weight;
        this.bulletG1 = G1;
        this.bulletG7 = G7;
        this.bulletStart_speed = start_speed;
    }



    BulletModel( String name, String caliber, float weight, float G1, float G7, float start_speed){
        this.bulletName = name;
        this.bulletCal = caliber;
        this.bulletWeight = weight;
        this.bulletG1 = G1;
        this.bulletG7 = G7;
        this.bulletStart_speed = start_speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return bulletName;
    }

    public void setName(String bulletName) {
        this.bulletName = bulletName;
    }

    public String getCaliber() {
        return bulletCal;
    }

    public void setCaliber(String bulletCal) {
        this.bulletCal = bulletCal;
    }

    public float getWeight() {
        return bulletWeight;
    }

    public void setWeight(float bulletWeight) {
        this.bulletWeight = bulletWeight;
    }

    public float getG1() {
        return bulletG1;
    }

    public void setG1(float bulletG1) {
        this.bulletG1 = bulletG1;
    }

    public float getG7() {
        return bulletG7;
    }

    public void setG7(float bulletG7) {
        this.bulletG7 = bulletG7;
    }

    public float getStart_speed() {
        return bulletStart_speed;
    }

    public void setStart_speed(float bulletStart_speed) {
        this.bulletStart_speed = bulletStart_speed;
    }

}