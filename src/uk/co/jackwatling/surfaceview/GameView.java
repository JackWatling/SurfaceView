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
		
		holder = getHolder();
		holder.addCallback(this);
		thread = new Thread(this);
		running = false;
		
		sprite = new Sprite(getResources());
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
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
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.GRAY);
		sprite.draw(canvas);
	}

	@Override
	public void run() {
		while (running){
			onUpdate();
			Canvas canvas = holder.lockCanvas();
			onDraw(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	private void onUpdate() {
		sprite.update(getWidth(), getHeight());
	}
	
}
