package com.example.sniper;

public class Bullet {

    private long bullet_id;
    private String caliber;
    private float weight;
    private float G1;
    private float G7;
    private float square;
    private float start_speed;



    Bullet(long bullet_id, String caliber, float weight, float G1, float G7, float square, float start_speed){
        this.bullet_id = bullet_id;
        this.caliber = caliber;
        this.weight = weight;
        this.G1 = G1;
        this.G7 = G7;
        this.square = square;
        this.start_speed = start_speed;
    }

    public long getId() {
        return bullet_id;
    }

    public String getCaliber() {
        return caliber;
    }

    public float getWeight() {
        return weight;
    }

    public float getG1() {
        return G1;
    }

    public float getG7() {
        return G7;
    }

    public float getSquare() {
        return square;
    }

    public float getStart_speed() {
        return start_speed;
    }

    public void setBullet_id(long bullet_id) {
        this.bullet_id = bullet_id;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setG1(float g1) {
        G1 = g1;
    }

    public void setG7(float g7) {
        G7 = g7;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public void setStart_speed(float start_speed) {
        this.start_speed = start_speed;
    }

}