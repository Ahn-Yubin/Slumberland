package physicalObject.entity;

import java.awt.Toolkit;

import collision.Collider;
import my2dGame.Model;
import vector.Vector;

public class Boss extends Enemy{
	
	public Boss(Model model, Vector position) {
		super(model, position);
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/boss.png"));
		this.setWidth(507*2);
		this.setHeight(447*2);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
	}
	
	public void whenCollisionPlayer() {
		System.out.println("boss!");
	}
}
