package physicalObject;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class Bullet extends PhysicalObject {
	public Bullet(Vector r, Vector v) {
		this.setPosition(r);
		this.setSpeed(v);
		this.setAcceleration(new Vector());
		this.setDirection(this.getSpeed().unit());
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet.png"));
		this.setWidth(20);
		this.setHeight(10);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}
