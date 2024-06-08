package physicalObject.entity;

import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import collision.Collider;
import vector.Vector;
import physicalObject.bullet.*;
import drawable.Drawable;
import drawable.MissileEffect;
import gameSystem.Model;

public class Boss extends Enemy {

	public Boss(Model model, Vector position) {
		super();
		this.setModel(model);
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/entity/boss.png"));
		this.setWidth(507 * 2);
		this.setHeight(447 * 2);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.setMaxHP(2000);
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
					
					getSpeed().setX(0);
					getAcceleration().setX(0);
					
					for(int i = 0; i < 10; i++) {
						Drawable afterImage = new MissileEffect(getPosition().add(new Vector(-getWidth() / 2 + Math.random() * 900,
								-getHeight()/2 + Math.random() * 900)), 700, 700, getAngle(), 1);
						getModel().getAfterImageList().add(afterImage);
						while(afterImage.getAlpha() > 0) {
							afterImage.setAlpha((float)(afterImage.getAlpha() - 0.1));
							try {
								Thread.sleep(30);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						getModel().getAfterImageList().remove(afterImage);
					}
					Drawable afterImage = new MissileEffect(getPosition(), 1200, 1200, getAngle(), 1);
					getModel().getAfterImageList().add(afterImage);
					setSprite(Toolkit.getDefaultToolkit().getImage("res/img/entity/diedBoss.png"));
					while(afterImage.getAlpha() > 0) {
						afterImage.setAlpha((float)(afterImage.getAlpha() - 0.1));
						try {
							Thread.sleep(120);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					getModel().getAfterImageList().remove(afterImage);
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
