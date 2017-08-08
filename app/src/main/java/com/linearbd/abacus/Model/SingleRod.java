package com.linearbd.abacus.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.linearbd.abacus.Listener.BeadStateListener;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public class SingleRod {
    private static final int BEAD_RADIUS =45;
    private EarthBeadGroup earthBeadGroup;
    private HeavenBead heavenBead;
    private Paint rodPaint;

    private int rodLength;
    private int startX;
    private int startY;

    public SingleRod(int rodLength,int startX, int startY) {
        this.rodLength = rodLength;
        this.startX = startX;
        this.startY = startY;

        int lengthForHeavenlyBead = rodLength/4;
        int lengthForEarthBeadGroup =rodLength- lengthForHeavenlyBead;


        earthBeadGroup = new EarthBeadGroup(startX, startY+lengthForHeavenlyBead ,lengthForEarthBeadGroup);
        heavenBead = new HeavenBead(startX,startY, BEAD_RADIUS);
        heavenBead.setDisplacement(lengthForHeavenlyBead- BEAD_RADIUS);
    }

    public void setBeadStateListener(BeadStateListener beadStateListener){
        earthBeadGroup.setBeadStateListener(beadStateListener);
        heavenBead.setBeadStateListener(beadStateListener);
    }

    public void draw(Canvas canvas){
        initPaint();
        canvas.drawLine(startX,startY-BEAD_RADIUS,startX,startY-BEAD_RADIUS+rodLength,rodPaint);
        earthBeadGroup.draw(canvas);
        heavenBead.draw(canvas);

    }

    public int getDividerPosition(){
        return (int) (startY+heavenBead.getDisplacement()+ BEAD_RADIUS);
    }

    public int getTotal(){

        return heavenBead.getValue()+earthBeadGroup.getValue();
    }

    private void initPaint(){
        rodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rodPaint.setStyle(Paint.Style.STROKE);
        rodPaint.setStrokeWidth(20);
        rodPaint.setColor(Color.parseColor("#BF360C"));
    }

    public Bead isTouch(float x, float y){
        Bead bead = null;

        for(EarthBead eb:earthBeadGroup.getEarthBeadList()){
            //Log.d(TAG,"Entering For Loop");
            float distanceFromCenter = (float) Math.sqrt(Math.pow((eb.getcX()-x),2)+Math.pow((eb.getcY()-y),2));

            if(distanceFromCenter<40){
                bead =eb;
            }
        }

        if(bead==null){
            float distanceFromCenter = (float) Math.sqrt(Math.pow((heavenBead.getcX()-x),2)+Math.pow((heavenBead.getcY()-y),2));

            if(distanceFromCenter<40){
                bead =heavenBead;
            }

        }
        return bead;


    }

    public void moveDownGroup(Bead bead){
        earthBeadGroup.moveDown((EarthBead) bead);
    }
    //moveDownHeavenlyBead

    public void moveUpGroup(Bead bead){
        earthBeadGroup.moveUp((EarthBead) bead);
    }

    public void moveDownHeavenlyBead(Bead bead){
        heavenBead.moveDown();
    }

    public void moveUpHeavenlyBead(Bead bead){
        heavenBead.moveUp();
    }

    public int getRodLength() {
        return rodLength;
    }

    public void setRodLength(int rodLength) {
        this.rodLength = rodLength;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
