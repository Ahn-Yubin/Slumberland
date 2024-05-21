package physicalObject;

import java.awt.Toolkit;

import collision.Collider;
import vector.Vector;

public class Player extends PhysicalObject {
	private boolean jetpack = false;
	private boolean onShoot = false;
	
	private double maxJetpackGauge = 100;
	private double jetpackGauge = maxJetpackGauge;
	private double jetpackGaugeUsagePerSec = 20;
	
	private int maxDashCount = 6;
	private int dashCount = maxDashCount;
	private int dashRechargingSec = 2;
	
	public Player() {
		super();
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/player2.png"));
		this.setWidth(80);
		this.setHeight(160);
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

	public boolean isOnShoot() {
		return this.onShoot;
	}
	
	public void setOnShoot(boolean b) {
		this.onShoot = b;
	}
	
	public boolean isJetpack() {
		return jetpack;
	}

	public void setJetpack(boolean jetpack) {
		this.jetpack = jetpack;
	}
	
	public double getMaxJetpackGauge() {
		return maxJetpackGauge;
	}

	public void setMaxJetpackGauge(double maxJetpackGauge) {
		this.maxJetpackGauge = maxJetpackGauge;
	}

	public double getJetpackGauge() {
		return jetpackGauge;
	}

	public void setJetpackGauge(double jetpackGauge) {
		this.jetpackGauge = jetpackGauge;
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
}