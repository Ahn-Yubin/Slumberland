package visiableObject;

import java.awt.Image;

import vector.Vector;

public abstract class VisiableObject {
	private Vector position;
	private Vector speed;
	private Vector acceleration;
	
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
	
	public void addPosition(Vector v) {
		this.position = this.position.add(v);
	}
	
	public Vector getSpeed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}
	
	public void addSpeed(Vector v) {
		this.speed = this.speed.add(v);
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	
	public void addAcceleration(Vector v) {
		this.acceleration = this.acceleration.add(v);
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
