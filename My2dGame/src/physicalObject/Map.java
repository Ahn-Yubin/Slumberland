package physicalObject;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class Map extends PhysicalObject {
	public Map() {
		super();
		this.setWidth(5000);
		this.setHeight(3000);
		this.setPosition(new Vector(1000, 1000));
		this.setSpeed(new Vector(0, 0));
		this.setAcceleration(new Vector(0, 0));
		Toolkit imageTool = Toolkit.getDefaultToolkit();
		this.setSprite(imageTool.getImage("res/img/map2.png"));
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
}
