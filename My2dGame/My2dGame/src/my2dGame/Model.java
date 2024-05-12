package my2dGame;

import java.util.List;

import vector.Vector;
import visiableObject.Bullet;
import visiableObject.Map;
import visiableObject.Player;

import java.util.ArrayList;

public class Model {
	private Player player;
	private List<Bullet> bulletList;
	private Map map;
	
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
		
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
}
