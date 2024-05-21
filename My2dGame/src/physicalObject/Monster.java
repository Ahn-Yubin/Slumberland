package physicalObject;

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
}
