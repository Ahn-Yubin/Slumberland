package my2dGame;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import physicalObject.*;
import vector.Vector;

import java.util.ArrayList;

public class Model {
	private Player player;
	private Monster monster;
	private List<Bullet> bulletList;
	private Map map;
	private Enemy enemy;
	private List<Bullet> enemyBulletList;
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
		
		this.monster = new Monster();
		this.monster.setPosition(new Vector(250, 0));
		
		this.bulletList = new ArrayList<Bullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<Bullet>();
		this.map = new Map();
		
		this.enemy = new Enemy(this);
	}

	public List<Bullet> getEnemyBulletList() {
		return enemyBulletList;
	}

	public void setEnemyBulletList(List<Bullet> enemyBulletList) {
		this.enemyBulletList = enemyBulletList;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
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
