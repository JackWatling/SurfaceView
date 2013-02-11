package uk.co.jackwatling.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder holder;
	private Bitmap icon;
	
	public GameView(Context context, AttributeSet attrs){
		super(context, attrs);
		icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas();
		onDraw(canvas);
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(icon, (getWidth() - icon.getWidth()) / 2, (getHeight() - icon.getHeight()) / 2, null);
	}
	
}
