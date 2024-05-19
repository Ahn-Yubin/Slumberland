package my2dGame;

import java.util.List;

import physicalObject.*;
import vector.Vector;

import java.util.ArrayList;

public class Model {
	private Player player;
	private Monster monster;
	private List<Bullet> bulletList;
	private Map map;
	
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
		
		this.monster = new Monster();
		
		this.bulletList = new ArrayList<Bullet>();
		
		this.map = new Map();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public List<Bullet> getBulletList(){
		return this.bulletList;
	}
	
	public Map getMap() {
		return this.map;
	}

	public Monster getMonster() {
		return monster;
	}
}
