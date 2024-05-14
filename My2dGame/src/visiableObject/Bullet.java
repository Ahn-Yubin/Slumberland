package visiableObject;

import java.awt.Toolkit;

import vector.Vector;

public class Bullet extends VisiableObject {
	public Bullet(Vector r, Vector v) {
		this.setPosition(r);
		this.setSpeed(v);
		this.setAcceleration(new Vector());
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet.png"));
		this.setWidth(15);
		this.setHeight(7);
	}
}
