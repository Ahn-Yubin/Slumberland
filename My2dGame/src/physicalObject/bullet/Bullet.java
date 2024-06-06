package physicalObject.bullet;

import collision.Collider;
import physicalObject.PhysicalObject;
import vector.Vector;

public abstract class Bullet extends PhysicalObject {
	
	public Bullet(Vector r, Vector v) {
		this.setPosition(r);
		this.setSpeed(v);
		this.setAcceleration(new Vector());
		this.setAngle(this.getSpeed().unit());
		this.setWidth(64);
		this.setHeight(14);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}
