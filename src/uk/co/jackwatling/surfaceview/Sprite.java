package uk.co.jackwatling.surfaceview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Sprite {
	
	private Bitmap texture;
	private Point2D position;
	private Point2D velocity;
	private boolean colliding;
	private boolean alive;
	private int health;
	
	public Sprite(Resources resources){
		this(resources, 0, 0);
	}
	
	public Sprite(Resources resources, int x, int y) {
		texture = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher);
		position = new Point2D(x, y);
		velocity = new Point2D(3);
		colliding = false;
		alive = true;
		health = 5;
	}
	
	public boolean isColliding(){
		return colliding;		
	}
	
	public boolean isDead(){
		return !alive;
	}

	/**
	 * Update the Sprite object, in this case the current velocity
	 * is added to the current position of the Sprite, causing
	 * movement on the screen.
	 * <p>
	 * If the sprite touches the edge of the screen the velocity is
	 * reversed in the appropriate direction.
	 * 
	 * @param width
	 * @param height
	 */
	public void update(int width, int height){
		position.add(velocity);
		colliding = false;
		
		//Left or right
		if (atLeftRight(width)){
			velocity.setX(velocity.getX() * -1);
			colliding = true;
			health--;
		}
		
		//Top or bottom
		if (atTopBottom(height)){
			velocity.setY(velocity.getY() * -1);
			colliding = true;
			health--;
		}
		
		if (health <= 0)
			alive = false;
	}

	private boolean atTopBottom(int height) {
		return position.getY() > height - texture.getHeight() || position.getY() < 0;
	}

	private boolean atLeftRight(int width) {
		return position.getX() > width - texture.getWidth() || position.getX() < 0;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(texture, position.getX(), position.getY(), null);
	}

}
