package physicalObject.entity;

import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import collision.Collider;
import my2dGame.Model;
import vector.Vector;
import physicalObject.bullet.*;

public class Boss extends Enemy {

	public Boss(Model model, Vector position) {
		super();
		this.setModel(model);
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/boss.png"));
		this.setWidth(507 * 2);
		this.setHeight(447 * 2);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.setMaxHP(10);
		this.setHp(getMaxHP());
		
		this.bossAttackPattern();
		this.bossMovePattern();
		this.livingCheck();
	}

	public void livingCheck() {
		new Thread() {
			public void run() {
				try {
					while (getHp() > 0) {
						Thread.sleep(6);
					}
					getModel().setGameState("Clear");
					getModel().setClearTime(System.currentTimeMillis());
					getModel().setEnemyBulletList(new CopyOnWriteArrayList<EnemyBullet>());
					getModel().setBossMissileList(new CopyOnWriteArrayList<BossMissile>());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void bossAttackPattern() {
		new Thread() {
			public void run() {
				while (getModel().getStageName().equals("bossStage") && getHp() > 0) {
					try {
						Thread.sleep(1000);

						shotMissile(getPosition(), new Vector(700, 0));
						shotMissile(getPosition(), new Vector(0, 700));
						shotMissile(getPosition(), new Vector(-700, 0));
						shotMissile(getPosition(), new Vector(700, 700));
						shotMissile(getPosition(), new Vector(-700, 700));

						Thread.sleep(1000);

						for (double i = 0; i < 2 * Math.PI; i += 0.07) {
							Thread.sleep(80);
							Vector v = new Vector(Math.cos(i), Math.sin(i));
							shotBullet(getPosition().add(new Vector(0, 190)), v.mul(2000));
						}

						Thread.sleep(1000);

						for (double i = 2 * Math.PI; i > 0; i -= 0.07) {
							Thread.sleep(80);
							Vector v = new Vector(Math.cos(i), Math.sin(i));
							shotBullet(getPosition().add(new Vector(0, 190)), v.mul(2000));
						}

						Thread.sleep(1000);

						for (int i = 0; i < 3; i++) {
							for (double j = 0; j < 2 * Math.PI; j += 0.07) {
								Vector v = new Vector(Math.cos(j), Math.sin(j));
								shotBullet(getPosition().add(new Vector(0, 190)), v.mul(2000));
							}
							Thread.sleep(500);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void bossMovePattern() {
		new Thread() {
			public void run() {
				while (getModel().getStageName().equals("bossStage") && getHp() > 0) {
					try {
						Thread.sleep(4000);
						if (Math.random() > 0.5)
							jump();
						else {
							jump();
							Thread.sleep(1000);
							jump();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void jump() {
		if (getModel().getStageName().equals("bossStage") && getHp() > 0) {
			if (this.getPosition().getX() > getModel().getPlayer().getPosition().getX())
				setDirection(-1);
			else
				setDirection(1);
			this.addSpeed(new Vector(getDirection() * 600, 1400));
		}
	}

	public void shotBullet(Vector p, Vector v) {
		if (getModel().getStageName().equals("bossStage") && getHp() > 0)
			getModel().getEnemyBulletList().add(new EnemyBullet(p, v));
	}

	public void shotMissile(Vector p, Vector v) {
		if (getModel().getStageName().equals("bossStage") && getHp() > 0)
			getModel().getBossMissileList().add(new BossMissile(p, v, getModel().getPlayer()));
	}
}
