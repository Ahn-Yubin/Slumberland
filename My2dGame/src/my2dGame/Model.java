package my2dGame;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import physicalObject.*;
import vector.Vector;

public class Model {
	private Player player;
	private List<Bullet> bulletList;
	private Map map;
	private Enemy enemy;
	private List<Bullet> enemyBulletList;
	private List<Obstacle> obstacleList;

	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
		
		this.bulletList = new CopyOnWriteArrayList<Bullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<Bullet>();
		this.map = new Map();
		
		this.enemy = new Enemy(this);
		this.enemy.setPosition(new Vector(250, 500));
		
		this.obstacleList = new CopyOnWriteArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(new Vector(250.000000, 250.000000), 500.000000, 100.000000));
		this.obstacleList.add(new Obstacle(new Vector(50.000000, 100.000000), 100.000000, 200.000000));
		this.obstacleList.add(new Obstacle(new Vector(250.000000, 100.000000), 100.000000, 200.000000));
		this.obstacleList.add(new Obstacle(new Vector(450.000000, 100.000000), 100.000000, 200.000000));
		this.obstacleList.add(new Obstacle(new Vector(150.000000, 50.000000), 100.000000, 100.000000));
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

	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}

	public void setObstacleList(List<Obstacle> obstacleList) {
		this.obstacleList = obstacleList;
	}
}
