package physicalObject;

import my2dGame.Model;
import java.awt.Toolkit;
import collision.Collider;
import vector.Vector;


public class Enemy extends Entity {
	
	private double attackBoundary = 700;
	private Model model;

	public Enemy(Model model, Vector position) {
		super();
		this.model = model;
		this.setPosition(position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/adf.png"));
		this.setWidth(100);
		this.setHeight(160);
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
					move();
					if(getHP() == 0) {
						model.getEnemyList().remove(getThis());
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void shoot() {
		if(model.getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary){
			Vector dv = model.getPlayer().getPosition().sub(getPosition()).unit();
			model.getEnemyBulletList().add(new EnemyBullet(getPosition(), dv.mul(2000)));
		}
	}
	
	public void move() {
		Vector v = model.getPlayer().getPosition().sub(getPosition()).unit();
		if(this.getHP() < this.getMaxHP() || model.getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary) {
			this.getSpeed().setX(v.getX() * 300);
		}
		else
			this.getSpeed().setX(0);
		if(isOnGround() && Math.random() < 0.5) {
			this.addSpeed(new Vector(0, 1000));
		}
	}
	
	public Enemy getThis() {
		return this;
	}
}
