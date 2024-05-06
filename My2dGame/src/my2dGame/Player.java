package my2dGame;

import java.awt.Image;
import java.awt.Toolkit;

public class Player {
	private Vector position;
	private Vector speed;
	private Vector acceleration;
	
	private boolean jetpack;
	private boolean onShoot = false;
	
	private int dashGauge;
	private int maxDashGauge;
	
	Toolkit imageTool = Toolkit.getDefaultToolkit();
	private Image sprite = imageTool.getImage("res/img/player2.png");
	
	public Player() {
		this.position = new Vector(0, 0);
		this.speed = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);
	}
	
	//================= Position ===================
	public Vector getPosition() {
		return this.position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void addPosition(Vector v) {
		this.position = this.position.add(v);
	}
	
	//================= Speed ===================
	public Vector getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(Vector speed) {
		this.speed = speed;
	}
	
	public void addSpeed(Vector v) {
		this.speed = this.speed.add(v);
	}
	
	//================= Acceleration ===================
	public Vector getAcceleration() {
		return this.acceleration;
	}
	
	public void setAcceleration(Vector accel) {
		this.acceleration = accel;
	}
	

	public void addAcceleration(Vector v) {
		this.acceleration = this.acceleration.add(v);
	}
	
	//================== OnShoot ====================
	public boolean getOnShoot() {
		return this.onShoot;
	}
	
	public void setOnShoot(boolean b) {
		this.onShoot = b;
	}
	
	//================== Sprite ====================
	public Image getSptite() {
		return this.sprite;
	}
	
	//==============================================
	public String toString() {
		String out = "=========\n";
		out += "Location Vector : " + this.position + "\n";
		out += "Speed Vector : " + this.speed + "\n";
		out += "Accel Vector : " + this.acceleration + "\n";
		return out;
	}
}
