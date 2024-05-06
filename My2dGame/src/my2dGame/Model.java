package my2dGame;

public class Model {
	private Player player;
	
	public Model() {
		this.player = new Player();
		this.player.setPosition(new Vector(100, 250));
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
