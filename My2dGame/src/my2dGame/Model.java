package my2dGame;

import java.awt.Toolkit;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import drawable.Drawable;
import physicalObject.*;
import physicalObject.bullet.*;
import physicalObject.entity.*;
import vector.Vector;

public class Model {
	private String stageTitle;

	private Player player;
	private Vector playerInitialPosition;
	private List<PlayerBullet> playerBulletList;

	private Drawable background;
	private Vector backgroundInitialPosition;

	private List<Enemy> enemyList;
	private List<EnemyBullet> enemyBulletList;

	private Boss boss;
	private List<BossMissile> bossMissileList;
	
	private List<Obstacle> obstacleList;

	private List<Drawable> afterImageList;
	
	public Model() {
	}

	public void initializeFirstStage() {
		this.setStageTitle("firstStage");

		this.player = new Player();
		this.playerInitialPosition = new Vector(2000, 2000);
		this.player.setPosition(playerInitialPosition);

		this.playerBulletList = new CopyOnWriteArrayList<PlayerBullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<EnemyBullet>();
		
		setAfterImageList(new CopyOnWriteArrayList<Drawable>());
		
		this.setBackground(new Drawable(new Vector(1000, 1000), 8000, 2500, Toolkit.getDefaultToolkit().getImage("res/img/background.png"), 1));
		setBackgroundInitialPosition(new Vector(playerInitialPosition.getX() - 2000, playerInitialPosition.getY() - 1000).add(new Vector(background.getWidth()/2, background.getHeight()/2)));

		this.enemyList = new CopyOnWriteArrayList<Enemy>();

		this.enemyList.add(new Enemy(this, new Vector(10875.000000, 1425.000000)));
		//this.enemyList.add(new Enemy(this, new Vector(6375.000000, 1900.000000)));
		//this.enemyList.add(new Enemy(this, new Vector(3375.000000, 2375.000000)));
		/*
		 * this.enemyList.add(new Enemy(this, new Vector(7125.000000, 2375.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(8625.000000, 2375.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(10125.000000, 2375.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(11625.000000, 2375.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(13125.000000, 2375.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(3375.000000, 3800.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(11625.000000, 3800.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(14625.000000, 3800.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(6375.000000, 4275.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(2625.000000, 4750.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(4875.000000, 4750.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(7875.000000, 4750.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(10125.000000, 4750.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(11625.000000, 4750.000000)));
		 * this.enemyList.add(new Enemy(this, new Vector(13125.000000, 4750.000000)));
		 */

		this.obstacleList = new CopyOnWriteArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(new Vector(9000.000000, 5937.500000), 18000.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(2250.000000, 4987.500000), 4500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(9375.000000, 4987.500000), 2250.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(13500.000000, 4987.500000), 3000.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(16875.000000, 2612.500000), 2250.000000, 5225.000000));
		this.obstacleList.add(new Obstacle(new Vector(1125.000000, 2375.000000), 2250.000000, 4750.000000));
		this.obstacleList.add(new Obstacle(new Vector(5625.000000, 4037.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(10500.000000, 4512.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(4875.000000, 3325.000000), 750.000000, 1900.000000));
		this.obstacleList.add(new Obstacle(new Vector(6375.000000, 3800.000000), 750.000000, 950.000000));
		this.obstacleList.add(new Obstacle(new Vector(3375.000000, 3562.500000), 2250.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(10125.000000, 3562.500000), 6750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(15375.000000, 3562.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(9000.000000, 3087.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(13125.000000, 3087.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(9375.000000, 2612.500000), 750.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(3375.000000, 1187.500000), 750.000000, 2375.000000));
		this.obstacleList.add(new Obstacle(new Vector(7125.000000, 1187.500000), 750.000000, 2375.000000));
		this.obstacleList.add(new Obstacle(new Vector(12000.000000, 2137.500000), 1500.000000, 475.000000));
		this.obstacleList.add(new Obstacle(new Vector(15000.000000, 1187.500000), 1500.000000, 2375.000000));
		this.obstacleList.add(new Obstacle(new Vector(6375.000000, 950.000000), 750.000000, 1900.000000));
		this.obstacleList.add(new Obstacle(new Vector(8625.000000, 950.000000), 750.000000, 1900.000000));
		this.obstacleList.add(new Obstacle(new Vector(2625.000000, 712.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(4875.000000, 712.500000), 2250.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(7875.000000, 712.500000), 750.000000, 1425.000000));
		this.obstacleList.add(new Obstacle(new Vector(11625.000000, 712.500000), 5250.000000, 1425.000000));
		
		this.bossMissileList = new CopyOnWriteArrayList<BossMissile>();
	}

	public void bossStage() {
		this.setStageTitle("bossStage");

		this.playerInitialPosition = new Vector(2000, 2000);
		this.player.setPosition(playerInitialPosition);

		this.playerBulletList = new CopyOnWriteArrayList<PlayerBullet>();
		this.enemyBulletList = new CopyOnWriteArrayList<EnemyBullet>();
		
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		
		this.bossMissileList = new CopyOnWriteArrayList<BossMissile>();
		this.boss = new Boss(this, new Vector(4000, 2000));
		this.enemyList.add(boss);
		
		this.obstacleList = new CopyOnWriteArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(new Vector(4500.000000, 5056.000000), 9000.000000, 1264.000000));
		this.obstacleList.add(new Obstacle(new Vector(1000.000000, 2212.000000), 2000.000000, 4424.000000));
		this.obstacleList.add(new Obstacle(new Vector(8000.000000, 2212.000000), 2000.000000, 4424.000000));
		Obstacle centerWall = new Obstacle(new Vector(4500.000000, 3318.000000), 1000.000000, 316.000000);
		centerWall.spin();
		this.obstacleList.add(centerWall);
		this.obstacleList.add(new Obstacle(new Vector(4500.000000, 632.000000), 5000.000000, 1264.000000));
	}

	public List<EnemyBullet> getEnemyBulletList() {
		return enemyBulletList;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public Player getPlayer() {
		return this.player;
	}

	public List<PlayerBullet> getPlayerBulletList() {
		return this.playerBulletList;
	}
	
	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}

	public void setObstacleList(List<Obstacle> obstacleList) {
		this.obstacleList = obstacleList;
	}

	public Vector getPlayerInitialPosition() {
		return playerInitialPosition;
	}

	public void setPlayerInitialPosition(Vector initialPlayerPosition) {
		this.playerInitialPosition = initialPlayerPosition;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	public List<BossMissile> getBossMissileList() {
		return bossMissileList;
	}

	public void setBossMissileList(List<BossMissile> bossMissileList) {
		this.bossMissileList = bossMissileList;
	}

	public Drawable getBackground() {
		return background;
	}

	public void setBackground(Drawable map) {
		this.background = map;
	}

	public Vector getBackgroundInitialPosition() {
		return backgroundInitialPosition;
	}

	public void setBackgroundInitialPosition(Vector backgroundInitialPosition) {
		this.backgroundInitialPosition = backgroundInitialPosition;
	}

	public List<Drawable> getAfterImageList() {
		return afterImageList;
	}

	public void setAfterImageList(List<Drawable> afterImageList) {
		this.afterImageList = afterImageList;
	}
}