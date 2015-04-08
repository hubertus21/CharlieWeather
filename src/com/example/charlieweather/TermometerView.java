package com.example.charlieweather;

import java.util.ArrayList;
import java.util.List;

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
	private Paint scalePaint = new Paint();
	private Path scalePath = new Path();
	private List<String> scaleLabels = new ArrayList<String>();
	private List<Float> scaleYs = new ArrayList<Float>();
	private float scaleLabelsX = 0;
	
	private double maxTemp = 10;
	private double minTemp = 10;
	


    public TermometerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    
    

    @Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if(w * h != 0){
			this.calculateInner();
	    	this.calculateOuter();
	    	this.calculateScale();
			}
	}
    
    protected void calculateScale(){
    	scalePath = new Path();
        Point termLowerCenter = new Point(this.getWidth()/2,(int) (this.getHeight() * 0.8));
        int termRadius = (int) (this.getWidth()* 0.1);
		scalePaint.setColor(Color.BLACK);
		scalePaint.setAlpha(128);
        scalePath.moveTo(0, 0);
        scalePaint.setTextSize(16);
        float unitHeight = (float) ((this.getHeight() * 0.6)/40);
        scaleLabels.clear();
        scaleYs.clear();
        for(int i=0;i < 40;i+=5){
            scalePath.addRoundRect(new RectF(termLowerCenter.x - (termRadius/2), (float) (termLowerCenter.y - (termRadius + unitHeight * i)), termLowerCenter.x + (termRadius/2), termLowerCenter.y - (termRadius + unitHeight * i) + 2),0,0,Direction.CCW);
            scaleLabels.add(String.valueOf(i - 10) + " 'C");
            scaleYs.add((termLowerCenter.y - (termRadius + unitHeight * i)));
        }
        scaleLabelsX = termLowerCenter.x + (termRadius/2);
        
       //scalePath.addRoundRect(new RectF(termLowerCenter.x - (termRadius/2), (float) (termLowerCenter.y - this.calculateInnerLength()), termLowerCenter.x + (termRadius/2), termLowerCenter.y),20,20,Direction.CCW);
    
    }
    
    protected void calculateInner(){
    	innerTermometerPath = new Path();
        Point termLowerCenter = new Point(this.getWidth()/2,(int) (this.getHeight() * 0.8));
        int termRadius = (int) (this.getWidth()* 0.1);
		innerTermometerPaint.setShader(new LinearGradient(0, 0, termRadius, 0, this.calculateInnerColor(), this.calculateInnerDarkerColor() , Shader.TileMode.MIRROR));
        innerTermometerPaint.setAlpha(128);

        innerTermometerPath.moveTo(0, 0);
        innerTermometerPath.addCircle(termLowerCenter.x,termLowerCenter.y, termRadius, Direction.CCW);

        
       innerTermometerPath.addRoundRect(new RectF(termLowerCenter.x - (termRadius/2), (float) (termLowerCenter.y - this.calculateInnerLength()), termLowerCenter.x + (termRadius/2), termLowerCenter.y),20,20,Direction.CCW);
    
    }
    protected void calculateOuter(){
    	outerTermometerPath = new Path();
        Point termLowerCenter = new Point(this.getWidth()/2,(int) (this.getHeight() * 0.8));
        int termRadius = (int) (this.getWidth()* 0.11);

        outerTermometerPaint.setShader(new LinearGradient(0, 0, termRadius * 0.6f, 1, Color.argb(255,200,200,200), Color.argb(128,200,200,200) , Shader.TileMode.MIRROR));
        outerTermometerPaint.setAlpha(64);

        outerTermometerPath.moveTo(0, 0);
        outerTermometerPath.addCircle(termLowerCenter.x,termLowerCenter.y, termRadius, Direction.CCW);

        outerTermometerPath.addRoundRect(new RectF(termLowerCenter.x - (termRadius/2), (float) (termLowerCenter.y - (this.getHeight() * 0.7)), termLowerCenter.x + (termRadius/2), termLowerCenter.y),20,20,Direction.CCW);
    }
    
    public void setTemperature(double maxTemp,double minTemp){
    	this.maxTemp = maxTemp;
    	this.minTemp = minTemp;
    	this.calculateInner();
    	this.calculateOuter();
    	
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

    	avgTemp -= 10;
    	int redColor = (int) (((225/20) * avgTemp) );
    	redColor = trimColor(redColor,225);

    	int greenColor= (int) (225 - Math.abs((225/20) * avgTemp));
    	
    	avgTemp += 20;
    	int blueColor = (int) (225 - (225/20) * avgTemp);
    	blueColor = trimColor(blueColor,225);

    	return Color.rgb( redColor, greenColor , blueColor);
    }
    
    protected int trimColor(int color, int maxColor){
    	if(color < 0)
    		return 0;
    	else if (color > maxColor)
    		return maxColor;
    	
    	return color;
    		
    }

    public int calculateInnerDarkerColor(){
    	double avgTemp = maxTemp + minTemp;
    	avgTemp /=2;

    	if(avgTemp > 30)
    		return Color.rgb(225,0,0);
    	else if(avgTemp < -10)
    		return Color.rgb(0,0,225);

    	avgTemp -= 10;
    	int redColor = (int) (((225/20) * avgTemp));
    	redColor = trimColor(redColor,225);
    	
    	int greenColor= (int) (225 - Math.abs((225/20) * avgTemp));
    	
    	avgTemp += 20;
    	int blueColor = (int) (225 - (225/20) * avgTemp);
    	blueColor = trimColor(blueColor,225);

    	return Color.rgb( redColor + 30, greenColor +30, blueColor+30);
    }

	@Override
    protected void onDraw(Canvas canvas) {
    	canvas.drawPath(innerTermometerPath,innerTermometerPaint);
    	canvas.drawPath(outerTermometerPath,outerTermometerPaint);
    	canvas.drawPath(scalePath, scalePaint);
    	for(int i=0;i < scaleLabels.size();i++){
    		canvas.drawText(scaleLabels.get(i), scaleLabelsX, scaleYs.get(i), scalePaint);
    	}
        super.onDraw(canvas);

    }
}
