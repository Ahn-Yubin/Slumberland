package physicalObject;

import java.awt.Toolkit;

import vector.Vector;

public class EnemyBullet extends Bullet{

	public EnemyBullet(Vector r, Vector v) {
		super(r, v);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/enemyBullet.png"));
		// TODO Auto-generated constructor stub
	}

}
