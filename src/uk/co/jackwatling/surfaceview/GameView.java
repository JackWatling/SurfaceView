package uk.co.jackwatling.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback{

	private SurfaceHolder holder;
	private Thread thread;
	private boolean running;
	
	private Sprite sprite;
	
	public GameView(Context context, AttributeSet attrs){
		super(context, attrs);		
		
		//Get surface holder
		holder = getHolder();
		holder.addCallback(this);
		
		//Threading
		thread = new Thread(this);
		running = false;
		
		//Add sprite
		sprite = new Sprite(getResources());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//Start thread
		thread.start();
		running = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		//Loop while running is true
		while (running){
			update();
			
			//Lock canvas, draw, then unlock canvas
			Canvas canvas = holder.lockCanvas();
			onDraw(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.GRAY);
		sprite.draw(canvas);
	}

	private void update() {
		sprite.update(getWidth(), getHeight());
	}
	
}
