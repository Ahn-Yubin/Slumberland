package physicalObject.bullet;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class PlayerBullet extends Bullet {

	public PlayerBullet(Vector r, Vector v) {
		super(r, v);
		this.setWidth(100);
		this.setHeight(30);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet/playerBullet.png"));
		// TODO Auto-generated constructor stub
	}

}
