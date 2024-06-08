package physicalObject.entity;

import physicalObject.bullet.EnemyBullet;

import java.awt.Toolkit;
import collision.Collider;
import drawable.Drawable;
import gameSystem.Model;
import vector.Vector;

public class Enemy extends Entity {

	private double attackBoundary;
	private Model model;
	private double leftMovementRestrictions;
	private double rightMovementRestrictions;
	private boolean isReloading;
	
	public Enemy() {
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/entity/enemy.png"));
		this.setWidth(250);
		this.setHeight(225);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public Enemy(Model model, Vector position) {
		super();
		this.model = model;
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/entity/enemy.png"));
		this.setWidth(250);
		this.setHeight(225);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.attackBoundary = 1100;
		this.leftMovementRestrictions = this.getPosition().getX() - 300;
		this.rightMovementRestrictions = this.getPosition().getX() + 300;
		this.setDirection(Math.random() > 0.5 ? 1 : -1);
		this.isReloading  = false;
		this.simpleAI();
		this.setMass(1.0);
	}
	
	private void simpleAI() {
		new Thread() {
			public void run() {
				while(getHp() > 0) {
					move();
					if(model.getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary && !isReloading) {
						for(int i=0; i<5; i++) {
							try {
								shoot();
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						reload();
						
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jump();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(Math.random() > 0.5)
					model.getPlayer().setHp(model.getPlayer().getHp() + 10);
				model.getEnemyList().remove(getThis());
				Drawable afterImage = new Drawable(getPosition(), getWidth(), getHeight(), getSprite(), getDirection(), new Vector(0, -1));
				model.getAfterImageList().add(afterImage);
				while(afterImage.getAlpha() > 0) {
					afterImage.setAlpha((float)(afterImage.getAlpha() - 0.1));
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				model.getAfterImageList().remove(afterImage);
			}
		}.start();
	}
	public void move() {
		if(this.getPosition().getX() < leftMovementRestrictions)
			setDirection(1);
		if(this.getPosition().getX() > rightMovementRestrictions)
			setDirection(-1);
		this.getSpeed().setX(getDirection() * 200);
	}

	public void shoot() {
		if(this.getHp() > 0) {
			this.getSpeed().setX(0);
			
			double dr = 2 * Math.PI/180;
			int additionalBulletCount = 4; // must be even
			Vector dv = model.getPlayer().getPosition().sub(getPosition()).unit();
			
			double bulletSpeed = 2000;
			model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.mul(bulletSpeed)));
			
			for(int i = 1; i <= additionalBulletCount/2; i++) {
				model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.rotate(i * dr).mul(bulletSpeed)));
				model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.rotate(i * -dr).mul(bulletSpeed)));
			}
		}
	}

	public void reload() {
		new Thread() {
			public void run() {
				isReloading = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isReloading = false;
			}
		}.start();
	}
	
	public void jump() {
		double leftDis = getPosition().getX() - leftMovementRestrictions;
		double rightDis = rightMovementRestrictions - getPosition().getX();
		
		if(leftDis > rightDis)
			setDirection(-1);
		else
			setDirection(1);
		if(isOnGround()) {
			setOnGround(false);
			this.addSpeed(new Vector(0, Math.random() * 500 + 500));
		}
	}
	
	public void whenCollisionPlayer() {
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Enemy getThis() {
		return this;
	}
}