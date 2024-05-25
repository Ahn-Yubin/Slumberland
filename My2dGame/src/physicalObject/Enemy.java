package physicalObject;

import my2dGame.Model;
import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;
public class Enemy extends PhysicalObject {
	private int HP = 100;
	private int maxHP = 100;
	private double attackBoundary = 700;
	private Player player;
	private Model model;
	public Enemy(Model model) {
		super();
		this.player = model.getPlayer();
		this.model = model;
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/tempEmemy.png"));
		this.setWidth(50);
		this.setHeight(80);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.simpleAI();
		this.moveAI();
	}
	
	private void simpleAI() {
		new Thread(){
			public void run () {
				while(true) {
					shoot();
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
					model.getEnemy().setAcceleration(new Vector(0, 0));
					gravity();
					move();
				}
			}
		}.start();
	}
	
	public void shoot() {
		if(getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary){
			Vector dv = getPlayer().getPosition().sub(getPosition()).unit();
			model.getEnemyBulletList().add(new Bullet(getPosition(), dv.mul(2000)));
		}
	}
	
	public void move() {
		Vector v = getPlayer().getPosition().sub(getPosition()).unit();
		if(HP<maxHP) {
			this.getSpeed().setX(v.getX() * 100);
		}
		if(player.isOnShoot()) {
			System.out.println("working");
			addSpeed(new Vector(0, 0.001));
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
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
