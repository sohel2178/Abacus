package com.linearbd.abacus.Layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.linearbd.abacus.Listener.BeadStateListener;
import com.linearbd.abacus.Listener.GroupBeadListener;
import com.linearbd.abacus.Model.Bead;
import com.linearbd.abacus.Model.EarthBead;
import com.linearbd.abacus.Model.EarthBeadGroup;
import com.linearbd.abacus.Model.HeavenBead;
import com.linearbd.abacus.Model.SingleRod;
import com.linearbd.abacus.R;

/**
 * Created by Genius 03 on 8/7/2017.
 */

public class AbacusView extends View implements BeadStateListener {
    private static final int THERESHOLD=20;
    private Context context;
    private SingleRod singleRod;
    private SingleRod singleRod2;

    private int canvasWidth,canvasHeight;
    private Paint selectPaint,borderPaint,rodPaint;

    private int margin;
    private int effectiveHeight,effectiveWidth;

    private int dividerY,rodCenterToCenter;

    private int firstRodTotal,secondRodTotal;


    public AbacusView(Context context) {
        super(context);
        this.context = context;

        singleRod = new SingleRod(getScreenHeight()-135-20,100,135);
        singleRod2 = new SingleRod(getScreenHeight()-135-20,250,135);
        singleRod.setBeadStateListener(this);
        singleRod2.setBeadStateListener(this);

        firstRodTotal=0;
        secondRodTotal=0;


    }

    private void initPaint(){
        selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectPaint.setColor(Color.parseColor("#eedd82"));
        selectPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(ContextCompat.getColor(context, R.color.borderColor));
        borderPaint.setStrokeWidth(8);

        rodPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rodPaint.setStyle(Paint.Style.STROKE);
        rodPaint.setColor(ContextCompat.getColor(context, R.color.rodColor));
        rodPaint.setStrokeWidth(4);
        rodPaint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //heavenBead.draw(canvas);
        //earthBead.draw(canvas);




        initPaint();

        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        margin = calculateMargin();
        effectiveHeight = calculateEffectiveHeight();
        effectiveWidth = calculateEffectiveWidth();

        // Draw Border on Canvas
        drawBorder(canvas);

        canvas.drawLine(margin,singleRod.getDividerPosition(),getWidth()-margin,singleRod.getDividerPosition(),borderPaint);

        canvas.drawText(String.valueOf(firstRodTotal),singleRod.getStartX()-10,singleRod.getStartY()-70,rodPaint);
        canvas.drawText(String.valueOf(secondRodTotal),singleRod2.getStartX()-10,singleRod2.getStartY()-70,rodPaint);



        singleRod.draw(canvas);
        singleRod2.draw(canvas);

        //Log.d("HHHH",canvasWidth+"");
        //Log.d("HHHH",canvasHeight+"");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value= super.onTouchEvent(event);


        Bead bead = null;
        Bead bead2 = null;



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{


                return true;
            }


            case MotionEvent.ACTION_MOVE:{

                float x = event.getX();
                float y = event.getY();
                bead = singleRod.isTouch(x,y);
                bead2 = singleRod2.isTouch(x,y);

                if(bead!=null){
                    if(bead instanceof HeavenBead){
                        if(y-bead.getcY()>THERESHOLD){

                            singleRod.moveDownHeavenlyBead(bead);
                            Log.d("hhhh","Down");
                            // earthBead1.moveDown();
                        }else if(y-bead.getcY()<-THERESHOLD){
                            Log.d("hhhh","UP");
                            singleRod.moveUpHeavenlyBead(bead);
                        }

                    }else{
                        if(y-bead.getcY()>THERESHOLD){

                            singleRod.moveDownGroup(bead);
                            Log.d("hhhh","Down");
                            // earthBead1.moveDown();
                        }else if(y-bead.getcY()<-THERESHOLD){
                            Log.d("hhhh","UP");
                            singleRod.moveUpGroup(bead);
                        }
                    }
                }

                if(bead2!=null){
                    if(bead2 instanceof HeavenBead){
                        if(y-bead2.getcY()>THERESHOLD){

                            singleRod2.moveDownHeavenlyBead(bead2);
                            Log.d("hhhh","Down");
                            // earthBead1.moveDown();
                        }else if(y-bead2.getcY()<-THERESHOLD){
                            Log.d("hhhh","UP");
                            singleRod2.moveUpHeavenlyBead(bead2);
                        }

                    }else{
                        if(y-bead2.getcY()>THERESHOLD){

                            singleRod2.moveDownGroup(bead2);
                            Log.d("hhhh","Down");
                            // earthBead1.moveDown();
                        }else if(y-bead2.getcY()<-THERESHOLD){
                            Log.d("hhhh","UP");
                            singleRod2.moveUpGroup(bead2);
                        }
                    }
                }



            }

        }

        return value;
    }


    private int getScreenHeight() {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;
        return height;
    }

    private void drawBorder(Canvas canvas) {
        canvas.drawRect(margin,margin,canvas.getWidth()-margin,canvas.getHeight()-margin,borderPaint);
        //dividerY = effectiveHeight/4;
        //canvas.drawLine(margin,margin+dividerY,canvas.getWidth()-margin,margin+dividerY,borderPaint);
        //rodCenterToCenter = effectiveWidth/9;

        //canvas.drawLine(margin+rodCenterToCenter,margin,margin+rodCenterToCenter,canvas.getHeight()-margin,rodPaint);


    }

    private int calculateEffectiveHeight() {
        return (canvasHeight-2*margin);
    }

    private int calculateEffectiveWidth() {
        return (canvasWidth-2*margin);
    }

    private int calculateMargin() {
        int tempMargin = (canvasHeight%9)/2;

        if(tempMargin<9){
            int minSide = canvasHeight-9*2;
            int remainder = minSide%9;
            tempMargin = 9+remainder/2;
        }
        return tempMargin;
    }


    @Override
    public void updateUI() {
        Log.d("Run","updateUICAlled");
        firstRodTotal = singleRod.getTotal();
        secondRodTotal = singleRod2.getTotal();
        postInvalidate();
    }
}
