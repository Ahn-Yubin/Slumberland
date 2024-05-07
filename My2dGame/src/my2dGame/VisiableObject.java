package my2dGame;

import java.awt.Image;

public abstract class VisiableObject {
	private Vector position;
	private Image sprite;
	
	private double width;
	private double height;
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
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
}
