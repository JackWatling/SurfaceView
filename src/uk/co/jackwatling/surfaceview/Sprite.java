package uk.co.jackwatling.surfaceview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Sprite {
	
	Bitmap texture;
	private Point2D position;
	private Point2D velocity;
	
	public Sprite(Resources resources){
		texture = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher);
		position = new Point2D();
		velocity = new Point2D(3);
	}

	public void update(int width, int height){
		position.add(velocity);
		if (position.getX() > width - texture.getWidth() || position.getX() < 0)
			velocity.setX(velocity.getX() * -1);
		if (position.getY() > height - texture.getHeight() || position.getY() < 0)
			velocity.setY(velocity.getY() * -1);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(texture, position.getX(), position.getY(), null);
	}

}
