package com.example.charlieweather;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class ProgressBar2 extends View {

	private Path sunPath = new Path();
	private Paint sunStreamsPaint = new Paint();
	private Paint sunPaint = new Paint();
	private List<Path> sunStreams = new ArrayList<Path>();
	private int currentStream = 0;
	private float sunRadius =0;
	private Point centerPoint;
	private Timer timer= new Timer();
	private Handler handlerUiThread = new Handler();
	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			currentStream++;
			handlerUiThread.post(new Runnable() {
				
				@Override
				public void run() {
					invalidate();
				}
			});
		}
	};


	
	public ProgressBar2(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer.scheduleAtFixedRate(task, 500, 300);
	}
	protected void calculateSunStreams(){
		sunStreams.clear();
		for(int i=0;i<15;i++){
			Path p = new Path();
			p.addRect(0f, sunRadius * 1.1f, 5f, sunRadius * 2f, Direction.CCW);
			
			sunStreams.add(p);
		}
	}
	
	protected void calculateSun(){
		sunPath = new Path();
		sunPaint = new Paint();
		sunStreamsPaint  = new Paint();
		centerPoint = new Point(this.getWidth()/2,this.getHeight()/2);

		if(this.getWidth() > this.getHeight())
			sunRadius = this.getHeight()/4;
		else
			sunRadius = this.getWidth()/4;
		sunStreamsPaint .setColor(Color.rgb(255,215,0));
		sunPaint.setColor(Color.rgb(255,215,0));
		sunPath.addCircle(centerPoint.x, centerPoint.y, sunRadius, Direction.CCW);
	}
	
	

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(w * h != 0){
				this.calculateSun();
				this.calculateSunStreams();
		}
	}
	
	@Override
    protected void onDraw(Canvas canvas) {
		canvas.drawPath(sunPath, sunPaint);
		int rot = 360 / sunStreams.size();
		for(int i=0;i < sunStreams.size();i++){
			canvas.rotate(rot, centerPoint.x, centerPoint.y);
			
			if(currentStream%sunStreams.size() != i)
				canvas.drawRect(centerPoint.x, centerPoint.y + sunRadius * 1.1f, centerPoint.x + 5, centerPoint.y + sunRadius *1.4f , sunStreamsPaint );
			else
				canvas.drawRect(centerPoint.x, centerPoint.y + sunRadius * 1.1f, centerPoint.x + 5, centerPoint.y + sunRadius *1.55f , sunStreamsPaint );
		}
        super.onDraw(canvas);

    }
}
