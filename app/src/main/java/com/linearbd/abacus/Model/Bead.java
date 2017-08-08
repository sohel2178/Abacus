package com.linearbd.abacus.Model;

import android.graphics.Canvas;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public abstract class Bead {
    private float cX;
    private float cY;
    private float radius;

    public Bead(float cX,float cY,float radius){
        this.cX = cX;
        this.cY = cY;
        this.radius =radius;
    }

    public float getcX() {
        return cX;
    }

    public void setcX(float cX) {
        this.cX = cX;
    }

    public float getcY() {
        return cY;
    }

    public void setcY(float cY) {
        this.cY = cY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public abstract void draw(Canvas canvas);

}
