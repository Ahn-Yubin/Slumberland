package physicalObject;

import java.awt.Toolkit;

import collision.Collider;

public class Player extends PhysicalObject {
	private boolean jetpack = false;
	private boolean onShoot = false;
	
	private double maxJetpackGauge = 100;
	private double jetpackGauge = maxJetpackGauge;
	private double jetpackGaugeUsagePerSec = 20;
	
	private int maxDashCount = 6;
	private int dashCount = maxDashCount;
	private int dashRechargingSec = 2;
	
	private int Life = 3;
	private int maxHP = 100;
	private int HP = 100;
	private int attackDamage = 10;
	
	public Player() {
		super();
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/player2.png"));
		this.setWidth(50);
		this.setHeight(80);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
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
		if(dashCount < 0)
			dashCount = 0;
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

	public double getJetpackGaugeUsagePerSec() {
		return jetpackGaugeUsagePerSec;
	}

	public void setJetpackGaugeUsagePerSec(double jetpackGaugeUsagePerSec) {
		this.jetpackGaugeUsagePerSec = jetpackGaugeUsagePerSec;
	}

	public int getDashRechargingSec() {
		return dashRechargingSec;
	}

	public void setDashRechargingSec(int dashRechagingSec) {
		this.dashRechargingSec = dashRechagingSec;
	}

	public int getLife() {
		return Life;
	}

	public void setLife(int life) {
		Life = life;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
		if(HP>maxHP)
			HP = maxHP;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
}