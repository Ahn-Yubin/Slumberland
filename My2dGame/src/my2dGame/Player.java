package my2dGame;

import java.awt.Image;
import java.awt.Toolkit;

public class Player extends VisiableObject {
	private Vector position;
	private Vector speed;
	private Vector acceleration;
	
	private boolean jetpack = false;
	private boolean onShoot = false;
	
	private double maxJetpackGauge = 100;
	private double jetpackGauge = maxJetpackGauge;
	
	private int maxDashCount = 6;
	private int dashCount = maxDashCount;
	
	Toolkit imageTool = Toolkit.getDefaultToolkit();
	private Image sprite = imageTool.getImage("res/img/player2.png");
	
	private double width;
	private double height;
	
	public Player() {
		this.position = new Vector(0, 0);
		this.speed = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);
	}
	
	public int getMaxDashCount() {
		return maxDashCount;
	}

	public void setMaxDashCount(int maxDashCount) {
		this.maxDashCount = maxDashCount;
	}

	public int getDashCount() {
		return dashCount;
	}

	public void setDashCount(int dashCount) {
		this.dashCount = dashCount;
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
	public boolean isOnShoot() {
		return this.onShoot;
	}
	
	public void setOnShoot(boolean b) {
		this.onShoot = b;
	}
	
	//================== Sprite ====================
	public Image getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	//================== Jetpack ====================
	public boolean isJetpack() {
		return jetpack;
	}

	public void setJetpack(boolean jetpack) {
		this.jetpack = jetpack;
	}
	
	//================== MaxJetpackGauge ====================
	public double getMaxJetpackGauge() {
		return maxJetpackGauge;
	}

	public void setMaxJetpackGauge(double maxJetpackGauge) {
		this.maxJetpackGauge = maxJetpackGauge;
	}

	//================== JetpackGauge ====================
	public double getJetpackGauge() {
		return jetpackGauge;
	}

	public void setJetpackGauge(double jetpackGauge) {
		this.jetpackGauge = jetpackGauge;
	}

	//================== Width ====================
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	//================== Height ====================
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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