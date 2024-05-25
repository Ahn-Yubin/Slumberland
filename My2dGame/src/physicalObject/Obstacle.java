package physicalObject;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class Obstacle extends PhysicalObject {

	public Obstacle() {
		super();
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/obstacle.png"));
		this.setWidth(160);
		this.setHeight(160);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public Obstacle(Vector position, double width, double height) {
		super();
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/obstacle.png"));
		this.setPosition(position);
		this.setWidth(width);
		this.setHeight(height);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}