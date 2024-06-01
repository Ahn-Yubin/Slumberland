package my2dGame;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import physicalObject.*;
import vector.Vector;

public class Model{

	private Player player;
	private Vector initialPlayerPosition = new Vector(1000, 1000);
	
	private List<PlayerBullet> playerBulletList;
	private Map map;
	private Enemy enemy;
	private List<Enemy> enemyList;
	private List<EnemyBullet> enemyBulletList;
	private List<Obstacle> obstacleList;

	public Model() {
		this.player = new Player();
		this.player.setPosition(initialPlayerPosition);
		
		this.playerBulletList = new CopyOnWriteArrayList<PlayerBullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<EnemyBullet>();
		this.map = new Map();
		
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		
		//this.enemyList.add(new Enemy(this, new Vector(800 + Math.random()*800, 1000 + Math.random()*1000)));
		for(int i=0; i<10; i++)
			this.enemyList.add(new Enemy(this, new Vector(Math.random()*6000, 1000 + Math.random()*1000)));
		
		this.obstacleList = new CopyOnWriteArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(new Vector(7500.000000, 4512.500000), 15000.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(1500.000000, 4037.500000), 3000.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(7875.000000, 4037.500000), 2250.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(12000.000000, 4037.500000), 3000.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(14625.000000, 2137.500000), 750.000000, 4275.000000));
		this.obstacleList.add(new Obstacle(new Vector(375.000000, 1900.000000), 750.000000, 3800.000000));
		this.obstacleList.add(new Obstacle(new Vector(4125.000000, 3087.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(9000.000000, 3562.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(3375.000000, 2375.000000), 750.000000, 1900.000000));
		this.obstacleList.add(new Obstacle(new Vector(4875.000000, 2850.000000), 750.000000, 950.000000));
		this.obstacleList.add(new Obstacle(new Vector(1875.000000, 2612.500000), 2250.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(8625.000000, 2612.500000), 6750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(13875.000000, 2612.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(7500.000000, 2137.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(11625.000000, 2137.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(7875.000000, 1662.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(1875.000000, 712.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(5625.000000, 712.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(10500.000000, 1187.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(13500.000000, 712.500000), 1500.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(4875.000000, 475.000000), 750.000000, 950.000000));
		this.obstacleList.add(new Obstacle(new Vector(7125.000000, 475.000000), 750.000000, 950.000000));
		this.obstacleList.add(new Obstacle(new Vector(1125.000000, 237.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(3375.000000, 237.500000), 2250.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(6375.000000, 237.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(10125.000000, 237.500000), 5250.000000, 475.000000));
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

	public Vector getInitialPlayerPosition() {
		return initialPlayerPosition;
	}

	public void setInitialPlayerPosition(Vector initialPlayerPosition) {
		this.initialPlayerPosition = initialPlayerPosition;
	}
}