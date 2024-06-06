package drawable;

import java.awt.Image;
import java.awt.Toolkit;

import vector.Vector;

public class Drawable {
	private Vector position;
	private Image sprite;
	private int direction;
	private double width;
	private double height;
	
	public Drawable(Vector position, double width, double height, Image sprite) {
		this.setPosition(position);
		this.setDirection(1);
		this.setWidth(width);
		this.setHeight(height);
		this.setSprite(sprite);
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
}
