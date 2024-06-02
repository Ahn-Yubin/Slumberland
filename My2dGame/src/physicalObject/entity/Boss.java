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
		shotMissile(new Vector(700, 0));
		shotMissile(new Vector(0, 700));
		shotMissile(new Vector(-700, 0));
		shotMissile(new Vector(700, 700));
		shotMissile(new Vector(-700, 700));
	}
	
	public void shotMissile(Vector v) {
		getModel().getBossMissileList().add(new BossMissile(this.getPosition(), v, getModel().getPlayer()));
		System.out.println(getModel().getEnemyBulletList().size());
	}
	
	public void whenCollisionPlayer() {
		
	}
}
