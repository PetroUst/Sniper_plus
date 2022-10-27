package com.example.sniper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class Shot {

    private static double distance;
    private static double target_high= 0.00001;
    private static double bc;//балістичний коефіцієнт
    private static double wind_speed = 0.000001;
    private static double bullet_speed;
    private static double wind_degree;

    public static void setBullet_speed(double bullet_speed) {
        Shot.bullet_speed = bullet_speed;
    }

    public static void setTarget_high(double target_high) {
        Shot.target_high = target_high;
        if(target_high==0)
        {
            Shot.target_high=0.01;
        }
    }

    public static void setDistance(double distance) {
        Shot.distance = distance;
    }

    public static void setBc(double bc) {
        Shot.bc = bc;
    }

    public static void setWind_degree(double wind_degree) {
        Shot.wind_degree = wind_degree;
    }

    public static void setWind_speed(double wind_speed) {
        Shot.wind_speed = wind_speed;
    }

    public static double calculate_vertical_correction(){
        //EditText _distance = (EditText)this.findViewById(R.id.edit_distance);
        //distance=Double.parseDouble(.getText().toString());
        //EditText distance = (EditText)findViewById(R.id.edit_distance);
        double alpha1 = 0, alpha2 = 80 * Math.PI / 180, mid=0, hCur = 0;
        double vX, vY,T,l,t=0.00001,F,aX,aY;
        double cur_distance=0, bullet_area =4.8e-5,
        air_density = 1.2,weight=0.010886;
        while(Math.abs(target_high-hCur)>0.0001){
            mid = (alpha1 + alpha2) / 2;
            vX = bullet_speed * Math.cos(mid);
            vY = bullet_speed * Math.sin(mid);
            hCur=0;
            T=0;
            l=0;
            while(l<distance){
                T+=t;
                F=bc*air_density*bullet_area*vX*vX/2;
                aX=F/weight;
                vX-=aX*t;

                aY = 9.80665;
                vY-= aY*t;
                hCur += vY * t;
                l += vX * t;
            }
            if (hCur > target_high) alpha2 = mid;
            else alpha1 = mid;
        }
        mid= mid* 180 / Math.PI;
        String.format("%.3f",mid);
        return mid;
    }




}
