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
    private static double high;
    private static double distance;
    private static double target_high;
    private static double bc;   //балістичний коефіцієнт
    private static double wind_speed = 0.000001;
    private static double wind_degree;

    private static final double t = 0.001; //time interval
    private static final double airDensity = 1.2;
    private static final double g = 9.80665;

    public static class Bullet{
        private static double speed;
        private static double weight;
        private static double square;

        public static void set_speed(double bullet_speed) {
            Bullet.speed = bullet_speed;
        }

        public static void set_weight(double bullet_weight) {
            Bullet.weight = bullet_weight;
        }

        public static void set_square(double bullet_square) {
            Bullet.square = bullet_square;
        }
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


    static double correctionV(){
        double correction = 0;
        double alpha1 = -(45 * Math.PI) / 180, alpha2 = (45 * Math.PI) / 180;
        double x, hCur = high, F, T;
        double aX, aY;
        double windV = 0; // = getWindV();


        while(Math.abs(target_high - hCur) > 0.001){
            correction = (alpha1 + alpha2) / 2;
            double vX = Bullet.speed * Math.cos(correction) - windV;
            double vY = Bullet.speed * Math.sin(correction);

            F = bc * airDensity * Bullet.square * Math.pow(vX, 2) / 2;

            T = 0;

            hCur = high;
            T = 0;
            x = 0;

            aX = -F/Bullet.weight;
            aY = -g;

            while(x < distance && x >= 0){ //Bullet flight
                T += t;

                hCur = vY * T + (aY * T * T) / 2;
                x = vX * T + (aX * T * T) / 2;
            }

            if(hCur > target_high) alpha2 = correction;
            else alpha1 = correction;
        }

        return correction * 180 / Math.PI;
    }
}
