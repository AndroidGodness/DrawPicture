package com.example.toluene.drawpicture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by toluene on 2016/12/10.
 */

public class DrawPictureView extends View {
    private List<Point> allPoints = new ArrayList<>();
    public DrawPictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setBackgroundColor(Color.WHITE);
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Point point = new Point((int)event.getX(),(int)event.getY());  //记录坐标
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    allPoints = new ArrayList<>();
                    allPoints.add(point);
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    allPoints.add(point);
                }else if (event.getAction() == MotionEvent.ACTION_MOVE){
                    allPoints.add(point);
                    DrawPictureView.this.postInvalidate();
                }
                return true;
            }
        });


    }

    private Random random = new Random();
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setARGB(255,random.nextInt(255),random.nextInt(255),random.nextInt(255));
        paint.setStrokeWidth(10.0f);
        if (allPoints.size() > 1){
            Iterator<Point> iterator = allPoints.iterator();
            Point firstPoint = null;
            Point lastPoint = null;
            while(iterator.hasNext()){
                if (firstPoint == null){
                    firstPoint = iterator.next();
                }else {
                    if (lastPoint != null){
                        firstPoint = lastPoint;
                    }
                    lastPoint = iterator.next();
                    canvas.drawLine(firstPoint.x,firstPoint.y,lastPoint.x,lastPoint.y,paint);
                }
            }
        }
        super.onDraw(canvas);
    }
}
