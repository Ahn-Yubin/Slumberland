package my2dGame;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.math.*;

import physicalObject.*;
import vector.Vector;

public class Model {
	private Player player;
	private List<PlayerBullet> playerBulletList;
	private Map map;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private List<EnemyBullet> enemyBulletList;
	private List<Obstacle> obstacleList;

	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(1000, 1000));
		
		this.playerBulletList = new CopyOnWriteArrayList<PlayerBullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<EnemyBullet>();
		this.map = new Map();
		
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		
		this.enemyList.add(new Enemy(this, new Vector(800 + Math.random()*800, 1000 + Math.random()*1000)));
		/*
		for(int i=0; i<40; i++)
			this.enemyList.add(new Enemy(this, new Vector(Math.random()*6000, 1000 + Math.random()*1000)));
		*/
		
		this.obstacleList = new CopyOnWriteArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(new Vector(25000.000000, 9750.000000), 50000.000000, 500.000000));
		this.obstacleList.add(new Obstacle(new Vector(250.000000, 4750.000000), 500.000000, 9500.000000));
		this.obstacleList.add(new Obstacle(new Vector(19500.000000, 7250.000000), 6000.000000, 4500.000000));
		this.obstacleList.add(new Obstacle(new Vector(49750.000000, 4750.000000), 500.000000, 9500.000000));
		this.obstacleList.add(new Obstacle(new Vector(34250.000000, 3250.000000), 5500.000000, 6500.000000));
		this.obstacleList.add(new Obstacle(new Vector(46750.000000, 3250.000000), 5500.000000, 6500.000000));
		this.obstacleList.add(new Obstacle(new Vector(9500.000000, 2250.000000), 6000.000000, 4500.000000));
		this.obstacleList.add(new Obstacle(new Vector(40500.000000, 2250.000000), 7000.000000, 4500.000000));
		this.obstacleList.add(new Obstacle(new Vector(3500.000000, 250.000000), 6000.000000, 500.000000));
		this.obstacleList.add(new Obstacle(new Vector(22000.000000, 250.000000), 19000.000000, 500.000000));
	}

	public List<EnemyBullet> getEnemyBulletList() {
		return enemyBulletList;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
	public List<Enemy> getEnemyList() {
		return enemyList;
	}


	public Player getPlayer() {
		return this.player;
	}
	
	public List<PlayerBullet> getPlayerBulletList(){
		return this.playerBulletList;
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
