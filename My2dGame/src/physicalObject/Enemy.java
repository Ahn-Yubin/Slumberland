package physicalObject;

import my2dGame.Model;
import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;
public class Enemy extends PhysicalObject {
	private int HP = 100;
	private double attackBoundary = 700;
	private Player player;
	private Model model;
	public Enemy(Model model) {
		super();
		this.player = model.getPlayer();
		this.model = model;
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/box.png"));
		this.setWidth(200);
		this.setHeight(200);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.simpleAI();
	}
	private void simpleAI() {
		new Thread(){
			public void run () {
				while(true) {
					shoot();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public void shoot() {
		if(getPlayer().getPosition().sub(getPosition()).size() <= attackBoundary){
			Vector dv = getPlayer().getPosition().sub(model.getEnemy().getPosition()).unit();
			model.getEnemyBulletList().add(new Bullet(model.getEnemy().getPosition(), dv.mul(2000)));
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
