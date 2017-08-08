package com.linearbd.abacus.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.linearbd.abacus.Listener.BeadStateListener;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public class EarthBead extends Bead {

    private static final String EB_COLOR="#424242";
    private float displacement;
    private boolean state;
    private Paint earthBeadPaint;
    private BeadStateListener listener;

    private float minCy,maxCy;

    public EarthBead(float cX, float cY, float radius) {
        super(cX, cY, radius);
        state = false;

        this.maxCy = cY;
        initColor();
    }

    private void initColor() {
        earthBeadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        earthBeadPaint.setColor(Color.parseColor(EB_COLOR));
        earthBeadPaint.setStyle(Paint.Style.FILL);

    }

    public void setBeadStateListener(BeadStateListener listener){
        this.listener =listener;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getcX(),getcY(),getRadius(),earthBeadPaint);

    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
        this.minCy = maxCy-displacement;

    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isTouched(float x, float y){
        boolean retBool = false;

        float distanceFromCenter = (float) Math.sqrt(Math.pow((getcX()-x),2)+Math.pow((getcY()-y),2));

        if(distanceFromCenter<getRadius()){
            retBool= true;
        }

        return retBool;
    }

    public void moveDown(){

        //setcY(getMaxCy());

        final float currentY = getcY();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(float i=0;i<displacement;i=i+4){
                    if(currentY+i<=maxCy){
                        setcY(currentY+i);
                        //Set State False
                        state=false;
                        if (listener!=null){
                            listener.updateUI();
                        }
                    }


                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();




    }
    public void moveUp(){
        final float currentY = getcY();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(float i=0;i<displacement;i=i+4){

                    if(currentY-i>=minCy){
                        setcY(currentY-i);
                        //set State = true
                        Log.d("JJJJJ","KKK");
                        state=true;
                        if (listener!=null){
                            Log.d("JJJJJ","Listener Not Null");
                            listener.updateUI();
                        }
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }
        }).start();


    }

    public float getMinCy() {
        return minCy;
    }

    public float getMaxCy() {
        return maxCy;
    }

    public int getValue(){
        int value =0;
        if(isState()){
            value =1;
        }

        return value;
    }



}
