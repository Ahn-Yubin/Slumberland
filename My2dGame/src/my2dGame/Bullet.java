package my2dGame;

import java.awt.Toolkit;

public class Bullet extends VisiableObject {
	public Bullet(Vector r, Vector v) {
		this.setPosition(r);
		this.setSpeed(v);
		this.setAcceleration(new Vector());
		Toolkit imageTool = Toolkit.getDefaultToolkit();
		this.setSprite(imageTool.getImage("res/img/bullet.png"));
		this.setWidth(150);
		this.setHeight(70);
	}
}
