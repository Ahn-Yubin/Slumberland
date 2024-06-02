package physicalObject;

import my2dGame.Model;
import java.awt.Toolkit;
import collision.Collider;
import vector.Vector;
import weapon.Weapon;


public class Enemy extends Entity {

	private double attackBoundary;
	private Model model;
	private double leftMovementRestrictions;
	private double rightMovementRestrictions;
	private int moveDir;
	private boolean isReloading;

	public Enemy(Model model, Vector position) {
		super();
		this.model = model;
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/enemy.png"));
		this.setWidth(100);
		this.setHeight(160);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.attackBoundary = 700;
		this.leftMovementRestrictions = this.getPosition().getX() - 300;
		this.rightMovementRestrictions = this.getPosition().getX() + 300;
		this.moveDir = Math.random() > 0.5 ? 1 : -1;
		this.isReloading  = false;
		//this.simpleAI();
		this.simpleAI();
		this.setMass(1.0);
	}
	private void simpleAI() {
		new Thread() {
			public void run() {
				while(true) {
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
			}
		}.start();
	}
	public void move() {
		if(this.getPosition().getX() < leftMovementRestrictions)
			moveDir = 1;
		if(this.getPosition().getX() > rightMovementRestrictions)
			moveDir = -1;
		this.getSpeed().setX(moveDir * 200);
	}

	public void shoot() {
		this.getSpeed().setX(0);
		Vector dv = model.getPlayer().getPosition().sub(getPosition()).unit();
		model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.mul(2000)));
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
			moveDir = -1;
		else
			moveDir = 1;
		if(isOnGround()) {
			setOnGround(false);
			this.addSpeed(new Vector(0, Math.random() * 500 + 500));
		}
	}

	public Enemy getThis() {
		return this;
	}
}
