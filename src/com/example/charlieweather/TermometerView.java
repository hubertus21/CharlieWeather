package com.example.charlieweather;

import android.view.View;


import android.content.Context;
import android.graphics.*;
import android.graphics.Path.Direction;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TermometerView extends View {

	private Paint innerTermometerPaint = new Paint();
	private Path innerTermometerPath = new Path();
	private Paint outerTermometerPaint = new Paint();
	private Path outerTermometerPath = new Path();
	
	private double maxTemp = -5;
	private double minTemp = 0;
	


    public TermometerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    
    

    @Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if(w * h != 0){
			this.calculateInner();
			}
	}
    
    protected void calculateInner(){
    	innerTermometerPath = new Path();
        Point termLowerCenter = new Point(this.getWidth()/2,(int) (this.getHeight() * 0.8));
        int termRadius = (int) (this.getWidth()* 0.1);
		//innerTermometerPaint.setColor(this.calculateInnerColor());
		innerTermometerPaint.setShader(new LinearGradient(0, 0, termRadius, 0, this.calculateInnerColor(), this.calculateInnerDarkerColor() , Shader.TileMode.MIRROR));
        innerTermometerPaint.setAlpha(128);

        innerTermometerPath.moveTo(0, 0);
        innerTermometerPath.addCircle(termLowerCenter.x,termLowerCenter.y, termRadius, Direction.CCW);

        
        //innerTermometerPath.addRect(, Direction.CCW);
        innerTermometerPath.addRoundRect(new RectF(termLowerCenter.x - (termRadius/2), (float) (termLowerCenter.y - this.calculateInnerLength()), termLowerCenter.x + (termRadius/2), termLowerCenter.y),20,20,Direction.CCW);
    }
    
    public void setTemperature(int maxTemp, int minTemp){
    	this.maxTemp = maxTemp;
    	this.minTemp = minTemp;
    	this.calculateInner();
    	
    }

    public double calculateInnerLength(){
    	double maxLength = this.getHeight() * 0.7;
    	double avgTemp = maxTemp + minTemp;
    	avgTemp /=2;
    	
    	if(avgTemp > 30)
    		return maxLength;
    	else if(avgTemp < -10)
    		return 0;
    	
    	avgTemp += 10;
    	
    	return (avgTemp / 40) * maxLength;
    }
    
    public int calculateInnerColor(){
    	double avgTemp = maxTemp + minTemp;
    	avgTemp /=2;

    	if(avgTemp > 30)
    		return Color.rgb(225,0,0);
    	else if(avgTemp < -10)
    		return Color.rgb(0,0,225);

    	avgTemp += 10;
    	int redColor = (int) (((225/40) * avgTemp));
    	avgTemp -= 20;
    	int greenColor =0;//  (int) (128 - ((225/80) * avgTemp));
    	avgTemp += 20;
    	int blueColor = (int) (225 - (225/40) * avgTemp);
    	
    	
    	return Color.rgb( redColor, greenColor , blueColor);
    }

    public int calculateInnerDarkerColor(){

    	double avgTemp = maxTemp + minTemp;
    	avgTemp /=2;

    	if(avgTemp > 30)
    		return Color.rgb(255,30,30);
    	else if(avgTemp < -10)
    		return Color.rgb(30,30,255);
    	
    	avgTemp += 10;
    	int redColor = (int) (((225/40) * avgTemp));
    	avgTemp -= 20;
    	int greenColor = 0;// (int) (113 - ((225/40) * avgTemp));
    	avgTemp += 20;
    	int blueColor = (int) (225 - (225/40) * avgTemp);
    	Log.i("COLOR",String.valueOf(redColor) + "," + String.valueOf(greenColor) + "," + String.valueOf(blueColor));
    	return Color.rgb( redColor + 30, greenColor + 30, blueColor + 30);
    }

	@Override
    protected void onDraw(Canvas canvas) {
    	canvas.drawPath(innerTermometerPath,innerTermometerPaint);
    	canvas.drawPath(outerTermometerPath,outerTermometerPaint);
        super.onDraw(canvas);

    }
}
