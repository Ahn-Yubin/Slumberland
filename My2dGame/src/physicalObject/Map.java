package physicalObject;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class Map extends PhysicalObject {
	public Map() {
		super();
		this.setWidth(1600);
		this.setHeight(900);
		this.setPosition(new Vector(this.getWidth()/2, this.getHeight()/2));
		this.setSpeed(new Vector(0, 0));
		this.setAcceleration(new Vector(0, 0));
		Toolkit imageTool = Toolkit.getDefaultToolkit();
		this.setSprite(imageTool.getImage("res/img/map.png"));
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}
