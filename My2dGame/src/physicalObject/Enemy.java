package physicalObject;

import my2dGame.Model;
import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;


public class Enemy extends Entity {
	private int HP = 100;
	private int maxHP = 100;
	private double attackBoundary = 700;
	private Model model;
	
	public Enemy(Model model, Vector position) {
		super();
		this.model = model;
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/tempEmemy.png"));
		this.setWidth(50);
		this.setHeight(80);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.simpleAI();
		this.moveAI();
		this.setMass(1.0);
	}
	
	private void simpleAI() {
		new Thread(){
			public void run () {
				while(true) {
					shoot();
					System.out.println("꺼억" + model.getEnemyBulletList().size());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	private void moveAI() {
		new Thread() {
			public void run() {
				while(true) {
					setAcceleration(new Vector(0, 0));
					gravity();
					System.out.println("중력");
					//dragForce();
					//move();
					if(HP == 0) {
						model.getEnemyList().remove(getThis());
					}
				}
			}
		}.start();
	}
	
	public void die() {
		model.getEnemyList().remove(this);
	}
	
	public void shoot() {
		if(model.getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary){
			Vector dv = model.getPlayer().getPosition().sub(getPosition()).unit();
			model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.mul(2000)));
		}
	}
	
	public void move() {
		Vector v = model.getPlayer().getPosition().sub(getPosition()).unit();
		if(HP < maxHP) {
			this.getSpeed().setX(v.getX() * 100);
		}
		if(model.getPlayer().isOnShoot()) {
			//System.out.println("working");
			//addSpeed(new Vector(0, 0.001));
		}
	}
	
	public void gravity() {
		if (getPosition().getY() >= 0) {
			//System.out.println("gravity");
			addAcceleration(new Vector(0, -400));
		}
		else {
			getPosition().setY(0);
			getSpeed().setY(0);
			//model.getPlayer().getAcceleration().setY(0);
		}
	}
	
	public void dragForce() {
		double c = 0.001;
		this.addAcceleration(this.getSpeed().mul(-c * this.getSpeed().size()));
	}
	
	public int getHP() {
		return HP;
	}
	
	public void setHP(int hP) {
		HP = hP;
	}

	public Enemy getThis() {
		return this;
	}
}
