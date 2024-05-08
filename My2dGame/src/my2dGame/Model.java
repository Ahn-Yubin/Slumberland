package my2dGame;

import java.util.List;
import java.util.ArrayList;

public class Model {
	private Player player;
	private List<Bullet> bulletList;
	
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
		
		this.bulletList = new ArrayList<Bullet>();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public List<Bullet> getBulletList(){
		return this.bulletList;
	}
}
