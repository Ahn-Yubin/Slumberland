package visiableObject;

import java.awt.Image;
import java.awt.Toolkit;

import vector.Vector;

public class Player extends VisiableObject {
	private boolean jetpack = false;
	private boolean onShoot = false;
	
	private double maxJetpackGauge = 100;
	private double jetpackGauge = maxJetpackGauge;
	
	private int maxDashCount = 6;
	private int dashCount = maxDashCount;

	public Player() {
		this.setPosition(new Vector(0, 0));
		this.setSpeed(new Vector(0, 0));
		this.setAcceleration(new Vector(0, 0));
		Toolkit imageTool = Toolkit.getDefaultToolkit();
		this.setSprite(imageTool.getImage("res/img/player2.png"));
		this.setWidth(50);
		this.setHeight(80);
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

	//================== OnShoot ====================
	public boolean isOnShoot() {
		return this.onShoot;
	}
	
	public void setOnShoot(boolean b) {
		this.onShoot = b;
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
	
	//==============================================
	public String toString() {
		String out = "=========\n";
		out += "Location Vector : " + this.getPosition() + "\n";
		out += "Speed Vector : " + this.getSpeed() + "\n";
		out += "Accel Vector : " + this.getAcceleration() + "\n";
		return out;
	}
}