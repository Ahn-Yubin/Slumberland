package drawable;

import java.awt.Image;
import java.awt.Toolkit;

import physicalObject.entity.Enemy;
import vector.Vector;

public class Drawable {
	private Vector position;
	private Image sprite;
	private int direction;
	private double width;
	private double height;
	
	private float alpha = 1.0f;
	private float maxAlpha = 1.0f;
	private float minAlpha = 0.0f;
	
	public Drawable(Vector position, double width, double height, Image sprite, int direction) {
		this.setPosition(position);
		this.setWidth(width);
		this.setHeight(height);
		this.setSprite(sprite);
		this.setDirection(direction);
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		if(alpha < 0 )
			this.alpha = 0;
		else if (alpha > 1)
			this.alpha = 1;
		else
			this.alpha = alpha;
	}
}
