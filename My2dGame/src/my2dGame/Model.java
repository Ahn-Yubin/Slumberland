package my2dGame;

import java.util.List;

import physicalObject.*;
import vector.Vector;

import java.util.ArrayList;

public class Model {
	private Player player;
	private Monster monster;
	private List<Obstacle> obstacleList;

	private List<Bullet> bulletList;
	private Map map;
	
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(1000, 1000));
		
		this.monster = new Monster();
		this.monster.setPosition(new Vector(100, 100));
		
		this.bulletList = new ArrayList<Bullet>();
		
		double w = 100;
		double h = 2000;
		Vector center = new Vector(h/2, h/2);
		this.obstacleList = new ArrayList<Obstacle>();
		this.obstacleList.add(new Obstacle(center.add(new Vector((w+h)/2, 0)), w, h));
		this.obstacleList.add(new Obstacle(center.add(new Vector(-(w+h)/2, 0)), w, h));
		this.obstacleList.add(new Obstacle(center.add(new Vector(0, (w+h)/2)), h, w));
		this.obstacleList.add(new Obstacle(center.add(new Vector(0, -(w+h)/2)), h, w));
		
		this.map = new Map();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public List<Bullet> getBulletList(){
		return this.bulletList;
	}
	
	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}
	
	public Map getMap() {
		return this.map;
	}

	public Monster getMonster() {
		return monster;
	}
}
