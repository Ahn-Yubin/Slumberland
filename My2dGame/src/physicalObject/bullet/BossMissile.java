package physicalObject.bullet;

import java.awt.Toolkit;

import collision.Collider;
import physicalObject.entity.Player;
import vector.Vector;

public class BossMissile extends Bullet {
	private Player target;
	
	public BossMissile(Vector r, Vector v, Player target) {
		// TODO Auto-generated constructor stub
		super(r, v);
		this.target = target;
		this.setWidth(64);
		this.setHeight(14);
		//this.setDamage(30);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet/bossMissile.png"));
		trace();
	}
	
	public void trace() {
		new Thread() {
			public void run() {
				Vector dp = target.getPosition().sub(getPosition());
				setSpeed(dp.unit().mul(200));
			}
		}.start();
	}
}
