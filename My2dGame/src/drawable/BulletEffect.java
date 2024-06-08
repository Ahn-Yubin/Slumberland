package drawable;

import java.awt.Toolkit;

import vector.Vector;

public class BulletEffect extends Drawable{

	public BulletEffect(Vector position, double width, double height, Vector angle, int direction) {
		super();
		this.setPosition(position);
		this.setWidth(width);
		this.setHeight(height);
		this.setAngle(angle);
		this.setDirection(direction);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/drawable/fireEffect.png"));
		// TODO Auto-generated constructor stub
	}

}
