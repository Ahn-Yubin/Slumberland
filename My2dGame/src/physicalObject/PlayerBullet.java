package physicalObject;

import java.awt.Toolkit;

import vector.Vector;

public class PlayerBullet extends Bullet {

	public PlayerBullet(Vector r, Vector v) {
		super(r, v);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/playerBullet.png"));
		// TODO Auto-generated constructor stub
	}

}
