package physicalObject.entity;

import java.awt.Toolkit;

import collision.Collider;
import my2dGame.Model;
import vector.Vector;
import physicalObject.bullet.*;

public class Boss extends Enemy{

	public Boss(Model model, Vector position) {
		super();
		this.setModel(model);
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/boss.png"));
		this.setWidth(507*2);
		this.setHeight(447*2);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.bossAttackPattern();
		this.bossMovePattern();
		this.setMaxHP(1000);
		this.setHp(1000);
	}

	public void bossAttackPattern() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
						shotMissile(new Vector(700, 0));
						shotMissile(new Vector(0, 700));
						shotMissile(new Vector(-700, 0));
						shotMissile(new Vector(700, 700));
						shotMissile(new Vector(-700, 700));
						Thread.sleep(1000);
						for(double i=0; i< 2 * Math.PI; i += 0.07) {
							Thread.sleep(80);
							Vector v = new Vector(Math.cos(i), Math.sin(i));
							getModel().getEnemyBulletList().add(new EnemyBullet(getPosition().add(new Vector(0, 190)), v.mul(2000)));
						}
						Thread.sleep(1000);
						for(double i = 2 * Math.PI; i>0; i-=0.07) {
							Thread.sleep(80);
							Vector v = new Vector(Math.cos(i), Math.sin(i));
							getModel().getEnemyBulletList().add(new EnemyBullet(getPosition().add(new Vector(0, 190)), v.mul(2000)));
						}
						Thread.sleep(1000);
						
						for(int i=0; i<3; i++) {
							for(double j = 0; j < 2 * Math.PI; j+=0.07) {
								Vector v = new Vector(Math.cos(j), Math.sin(j));
								getModel().getEnemyBulletList().add(new EnemyBullet(getPosition().add(new Vector(0, 190)), v.mul(1000)));
							}
							Thread.sleep(500);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();;
	}

	public void bossMovePattern() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(4000);
						if(Math.random() > 0.5)
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
		if(this.getPosition().getX() > getModel().getPlayer().getPosition().getX())
			setDirection(-1);
		else
			setDirection(1);
		this.addSpeed(new Vector(getDirection() * 600, 1400));
		System.out.println(getModel().getEnemyList().size());
	}

	public void shotMissile(Vector v) {
		getModel().getBossMissileList().add(new BossMissile(this.getPosition(), v, getModel().getPlayer()));
	}

	public void whenCollisionPlayer() {

	}
}
