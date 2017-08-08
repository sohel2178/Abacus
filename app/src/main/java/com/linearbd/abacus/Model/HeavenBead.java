package com.linearbd.abacus.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.linearbd.abacus.Listener.BeadStateListener;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public class HeavenBead extends Bead {
    private static final String HB_COLOR="#795548";
    private float maxCy;
    private float minCy;
    private float displacement;
    private boolean state;
    private Paint heavenBeadPaint;
    private BeadStateListener listener;



    public HeavenBead(float cX, float cY, float radius) {
        super(cX, cY, radius);
        this.minCy = cY;
        state = false;
        initColor();
    }

    public void setBeadStateListener(BeadStateListener listener){
        this.listener =listener;
    }

    private void initColor() {
        heavenBeadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        heavenBeadPaint.setColor(Color.parseColor(HB_COLOR));
        heavenBeadPaint.setStyle(Paint.Style.FILL);

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getcX(),getcY(),getRadius(),heavenBeadPaint);

    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
        this.maxCy = minCy+displacement;

    }

    public float getMaxCy() {
        return maxCy;
    }

    public void setMaxCy(float maxCy) {
        this.maxCy = maxCy;
    }

    public float getMinCy() {
        return minCy;
    }

    public void setMinCy(float minCy) {
        this.minCy = minCy;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState(){
        return state;
    }

    public int getValue(){
        int value =0;

        if(isState()){
           value=5;
        }

        return value;
    }


    public boolean isTouched(float x,float y){
        boolean retBool = false;

        float distanceFromCenter = (float) Math.sqrt(Math.pow((getcX()-x),2)+Math.pow((getcY()-y),2));

        if(distanceFromCenter<getRadius()){
            retBool= true;
        }

        return retBool;
    }

    public void moveDown(){
        final float currentY = getcY();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(float i=0;i<displacement;i=i+4){
                    if(currentY+i<=maxCy){
                        setcY(currentY+i);
                        state=true;
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



                Log.d("TTTTTTTTTT",state+"");
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
                        state = false;
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
}
