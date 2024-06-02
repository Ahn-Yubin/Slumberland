package physicalObject.bullet;

import java.awt.Toolkit;

import collision.Collider;
import physicalObject.entity.Player;
import vector.Vector;

public class BossMissile extends Bullet {
	private Player target;
	private double damage = 30;
	
	public BossMissile(Vector r, Vector v, Player target) {
		// TODO Auto-generated constructor stub
		super(r, v);
		this.target = target;
		this.setWidth(640);
		this.setHeight(140);
		this.setDamage(30);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/bullet/bossMissile.png"));
		trace();
	}

	public void trace() {
		new Thread() {
			public void run() {
				try {
					while (true) {
						System.out.println(getPosition() + " " + getSpeed());
						Vector dp = target.getPosition().sub(getPosition());
						Vector spd = getSpeed();
						
						double cross = dp.getX()*spd.getY() - dp.getY()*spd.getX();
						
						double tracingPerformence = 1000 * ((cross > 0) ? 1 : -1);
						Vector dv = new Vector(spd.getY(), -spd.getX()).unit().mul(tracingPerformence);
						setAcceleration(dv);
						setDirection(getSpeed());
						Thread.sleep(6);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
}
