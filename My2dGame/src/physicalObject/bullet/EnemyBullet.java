package physicalObject.bullet;

import java.awt.Toolkit;

import vector.Vector;

public class EnemyBullet extends Bullet{
	private double damage;
	
	public EnemyBullet(Vector r, Vector v) {
		super(r, v);
		this.damage = 1;
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet/enemyBullet.png"));
		// TODO Auto-generated constructor stub
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

}
