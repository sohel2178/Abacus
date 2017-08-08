package com.linearbd.abacus.Model;

import android.graphics.Canvas;
import android.util.Log;

import com.linearbd.abacus.Listener.BeadStateListener;
import com.linearbd.abacus.Listener.GroupBeadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public class EarthBeadGroup {
    private static final String TAG="EarthBeadGroup";
    private static final float BEAD_RADIUS=38;
    private List<EarthBead> earthBeadList;
    private int startX,startY;
    private int height;
    private float displacement;

    private BeadStateListener listener;


    public EarthBeadGroup(int startX,int startY,int height) {
        this.startX = startX;
        this.startY =startY;
        this.height =height;


        this.displacement = height-8*BEAD_RADIUS;

        initEarthBeadList();

        Log.d("displacement"," "+displacement);
    }

    public void setBeadStateListener(BeadStateListener listener){

        for(EarthBead x: earthBeadList){
            x.setBeadStateListener(listener);
        }
        //this.listener =listener;
    }

    private void initEarthBeadList() {
        this.earthBeadList = new ArrayList<>();

        for(int i=0;i<4;i++){
            EarthBead bead= new EarthBead(startX,startY+height-(2*i+1)*BEAD_RADIUS,BEAD_RADIUS);
            bead.setDisplacement(displacement);
            earthBeadList.add(bead);
        }

    }

    public List<EarthBead> getEarthBeadList() {
        return earthBeadList;
    }

    public void setEarthBeadList(List<EarthBead> earthBeadList) {
        this.earthBeadList = earthBeadList;
    }

    public void draw(Canvas canvas){
        for(EarthBead x:earthBeadList){
            x.draw(canvas);
        }
    }

    public EarthBead isTouched(float x, float y){
        Log.d(TAG,"Touch X= "+x);
        Log.d(TAG,"Touch Y= "+y);
        EarthBead earthBead = null;

        for(EarthBead eb:earthBeadList){
            //Log.d(TAG,"Entering For Loop");
            float distanceFromCenter = (float) Math.sqrt(Math.pow((eb.getcX()-x),2)+Math.pow((eb.getcY()-y),2));

            if(distanceFromCenter<BEAD_RADIUS){
                Log.d(TAG,"Condition Match");
                Log.d(TAG,"Earth Bead X= "+eb.getcX());
                Log.d(TAG,"Earth Bead Y= "+eb.getcY());
                earthBead =eb;
            }
        }
        return earthBead;
    }

    public int getValue(){
        int total = 0;
        for (EarthBead x:earthBeadList){
            total=total+x.getValue();
        }

        return total;
    }

    public void moveDown(EarthBead earthBead){
        final int index = earthBeadList.indexOf(earthBead);


        for (EarthBead x: earthBeadList){
            Log.d("Run","Enter Second For");
            Log.d("Run","MinCy "+x.getMinCy());
            Log.d("Run","MinCy "+x.getMaxCy());


            if(earthBeadList.indexOf(x)<=index){
                           /* if(x.getcY()+i<=x.getMaxCy()){
                                Log.d("Run","Enter into If");
                                x.setcY(x.getcY()+i);


                            }*/

                x.moveDown();
            }


        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Run","Enter Run Method");

                for(float i=0;i<displacement;i=i+10){


                    for (EarthBead x: earthBeadList){
                        Log.d("Run","Enter Second For");
                        Log.d("Run","MinCy "+x.getMinCy());
                        Log.d("Run","MinCy "+x.getMaxCy());


                        if(earthBeadList.indexOf(x)<=index){
                           *//* if(x.getcY()+i<=x.getMaxCy()){
                                Log.d("Run","Enter into If");
                                x.setcY(x.getcY()+i);


                            }*//*

                            x.moveDown();
                        }


                    }

                    if(listener!=null){
                        listener.updateUI();
                    }



                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Log.d("YVAL",tempList.get(0).getcY()+"");

            }
        }).start();*/
    }

    public void moveUp(EarthBead earthBead){
        final int index = earthBeadList.indexOf(earthBead);

        for (EarthBead x: earthBeadList){
            Log.d("Run","Enter Second For");
            Log.d("Run","MinCy "+x.getMinCy());
            Log.d("Run","MinCy "+x.getMaxCy());


            if(earthBeadList.indexOf(x)>=index){
                /*if(x.getcY()-i>=x.getMinCy()){
                    Log.d("Run","Enter into If");
                    x.setcY(x.getcY()-i);


                }*/

                x.moveUp();
            }


        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Run","Enter Run Method");

                for(float i=0;i<displacement;i=i+10){




                    if(listener!=null){
                        listener.updateUI();
                    }



                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Log.d("YVAL",tempList.get(0).getcY()+"");

            }
        }).start();*/





       /* List<EarthBead> tempList = new ArrayList<>();

        for(int i=index;i<earthBeadList.size();i++){
            tempList.add(earthBeadList.get(i));
        }

        moveGroup(tempList);*/
    }

    private void moveGroup(final List<EarthBead> tempList) {

       // final List<Float> currentStateList = new ArrayList<>();

       /* for(EarthBead x: tempList){
            currentStateList.add(x.getcY());


        }*/

       Log.d("YVAL",tempList.get(0).getcY()+"");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Run","Enter Run Method");

                for(float i=0;i<displacement;i=i+10){


                    for (EarthBead x: tempList){
                        Log.d("Run","Enter Second For");
                        if(x.getcY()-i>=x.getMinCy()){
                            Log.d("Run","Enter into If");
                            x.setcY(x.getcY()-i);


                        }
                    }

                    if(listener!=null){
                        listener.updateUI();
                    }



                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d("YVAL",tempList.get(0).getcY()+"");

            }
        }).start();
    }
}
