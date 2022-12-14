package com.example.sniper;

import java.lang.Math;

public class Shot {
    private static double height;
    private static double distance = 0;
    private static double targetHeight;
    private static double windSpeed = 0;
    private static double windDegree;
    private static double T = 0;

    private static final double t = 0.001;  //time interval
    private static final double airDensity = 1.2;
    private static final double g = 9.80665;

    private static boolean error = false;
    public static double getDistance(){return distance;}

    public static class Bullet{
        private static double speed;
        private static double weight;
        private static double square;
        private static double bc;   //ballistic coefficient

        public static void setSpeed(double speed) {
            Bullet.speed = speed;
        }

        public static void setWeight(double weight) {
            Bullet.weight = weight;
        }

        public static void setSquare(double square) {
            Bullet.square = square;
        }

        public static void setBc(double bc) {
            Bullet.bc = bc;
        }

        public static double getSpeed() {
            return speed;
        }

        public static double getSquare() {
            return square;
        }

        public static double getBc() {
            return bc;
        }

        public static double getWeight() {
            return weight;
        }
    }

    public static void setTargetHeight(double targetHeight) {
        Shot.targetHeight = targetHeight;
        if(targetHeight == 0)
        {
            Shot.targetHeight = 0.01;
        }
    }

    public static void setHeight(double height){
        Shot.height = height;
    }


    public static void setDistance(double distance) {
        Shot.distance = distance;
    }


    public static void setWindDegree(double windDegree) {
        Shot.windDegree = windDegree;
    }


    public static void setWindSpeed(double windSpeed) {
        Shot.windSpeed = windSpeed;
    }


    private static double wind_horizontal()
    {
        double d,p,a, w_speed = Math.sin(Math.toRadians(windDegree*30))*windSpeed;
        d=Math.sqrt(Bullet.getSquare()*4/Math.PI);
        p=0.613*Math.pow(w_speed,2);
        a=(d*0.02*p*3)/Bullet.getWeight();
        if (w_speed<0){a*=-1;}
        return (a*T*1.5)/2;
//        return Math.sin(Math.toRadians(windDegree*30));
    }
    private static double wind_vertical()
    {
        return windSpeed*Math.cos(Math.toRadians(windDegree*30));
    }
    static double correctionV(){
        double correction = 0;
        double alpha1 = -(45 * Math.PI) / 180, alpha2 = (45 * Math.PI) / 180;
        double hCur = height, x, F;
        double vX = 0, vY;
        double aX, aY;
        double windV = 0; // = getWindV();

        error = false;

        for (int i = 0; i < 100; i++){     //binary search
            correction = (alpha1 + alpha2) / 2;
            vX = Bullet.speed * Math.cos(correction) + wind_vertical();
            vY = Bullet.speed * Math.sin(correction);

            hCur = height;
            T = 0;
            x = 0;

            aY = -g;

            while(x < distance && vX > 100){ //Bullet flight
                T += t;

                F = Bullet.bc * airDensity * Bullet.square * Math.pow(vX, 2) / 2;
                aX = - F / Bullet.weight;

                vX += aX * t;
                vY += aY * t;

                hCur += vY * t;
                x += vX * t;
            }

            if ( 45 * Math.PI / 180 - Math.abs(correction)  < 1 * Math.PI / 180 && hCur + 5 < targetHeight ){ //bullet can't fly far enough
                error = true;
                break;
            }

            if ( hCur < targetHeight && vX < 100 ){    //bullet can't fly high enough at specified distance
                error = true;
                break;
            }

            if(hCur > targetHeight) alpha2 = correction;
            else alpha1 = correction;
        }

        if (error || Math.abs(targetHeight - hCur) > 0.01 || vX < 100 ) return 0;  //shot is not possible

        double degrees = correction * 180 / Math.PI;
        return degrees * 60;
    }


    static double correctionH(){
        if (error) return 0;

        double spinDrift = 1.25 * (1.7 + 1.2) * Math.pow(T, 1.83) * 25.4;    //millimetres
        double windDrift = wind_horizontal()*T;   // = getWindH() * T

//        double degrees =  - Math.atan( ((spinDrift + windDrift) / 1000 / Shot.distance ) ) * 180 / Math.PI;
        double degrees =  - Math.atan( (((spinDrift/1000) + windDrift) / Shot.distance ) ) * 180 / Math.PI;
        return degrees * 60;
//        return wind_horizontal();
    }
}
