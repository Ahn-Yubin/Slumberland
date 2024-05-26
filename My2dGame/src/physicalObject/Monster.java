package physicalObject;

import vector.Vector;
import java.awt.Toolkit;

import collision.Collider;

public class Monster extends PhysicalObject {
	public Monster() {
		super();
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/box.png"));
		this.setWidth(1600);
		this.setHeight(200);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public Monster(Vector p, double w, double h) {
		super();
		this.setPosition(p);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/box.png"));
		this.setWidth(w);
		this.setHeight(h);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}
