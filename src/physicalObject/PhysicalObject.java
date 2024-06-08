package physicalObject;

import java.awt.Image;

import collision.Collider;
import vector.Vector;

public abstract class PhysicalObject{

	private Vector position;
	private Vector speed;
	private Vector acceleration;
	
	private Vector angle; 
	
	private int direction = 1;
	
	private Image sprite;
	
	private double width;
	private double height;
	
	private double mass;
	
	private Collider collider;
	
	public PhysicalObject() {
		this.setPosition(new Vector(0, 0));     // m
		this.setSpeed(new Vector(0, 0));        // m/s
		this.setAcceleration(new Vector(0, 0)); // m/s^2
		this.setAngle(new Vector(1, 0));
		this.setMass(1.0);
	}
	
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

	public Vector getAngle() {
		return angle;
	}

	public void setAngle(Vector direction) {
		this.angle = direction;
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

	public Collider getCollider() {
		return collider;
	}

	public void setCollider(Collider collider) {
		this.collider = collider;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
