package physicalObject;

import collision.Collider;
import vector.Vector;

public class Obstacle extends PhysicalObject {
	
	public Obstacle() {
		super();
		this.setWidth(160);
		this.setHeight(160);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public Obstacle(Vector position, double width, double height) {
		super();
		int renderingErrorCorrection = 10;
		this.setPosition(position);
		this.setWidth(width + renderingErrorCorrection);
		this.setHeight(height + renderingErrorCorrection);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public void spin() {
		double dt = 1.0/144.0;
		double angular = 0.001 * 2* Math.PI;
		new Thread() {
			public void run() {
				try {
					while(true) {
						setAngle(getAngle().add(new Vector(getAngle().getY(), -getAngle().getX()).unit().mul(angular)));
						sleep((int)(1000 * dt));
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}