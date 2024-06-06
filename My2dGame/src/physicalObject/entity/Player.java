package physicalObject.entity;

import java.awt.Toolkit;

import collision.Collider;
import weapon.Weapon;

public class Player extends Entity {

	private boolean jetpack = false;
	private boolean onShoot = false;

	private double maxJetpackGauge = 100;
	private double jetpackGauge = maxJetpackGauge;
	private double jetpackGaugeUsagePerSec = 20;

	private int maxDashCount = 6;
	private int dashCount = maxDashCount;
	private int dashRechargingSec = 2;

	private double hpRecoveryPerSec = 3;
	
	private Weapon weapon;

	public Player() {
		super();
		this.setMaxHP(100);
		this.setHp(this.getMaxHP());
		this.setSprite(Toolkit.getDefaultToolkit().getImage("res/img/player.png"));
		this.setWidth(100);
		this.setHeight(160);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		this.weapon = new Weapon(20, 3, 10);
		//new HpRecovery().start();
	}

	private class HpRecovery extends Thread {
		private double dt = 1.0/1000.0; // second

		public void run() {
			try {
				while (true) {
					//System.out.println("" + getHp());
					if(getHp() < getMaxHP()) {
						//System.out.println("" + getHp());
						if(getMaxHP() < getHp() + hpRecoveryPerSec*dt) {
							//System.out.println("풀피만들기" + getHp());
							setHp(getMaxHP());
						}
						else {
							//System.out.println("찔끔" + getHp());
							setHp(getHp() + hpRecoveryPerSec*dt);
						}
					}
					sleep((int) (1000 * dt));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		if (dashCount < 0)
			dashCount = 0;
		this.dashCount = dashCount;
	}

	// ================== OnShoot ====================
	public boolean isOnShoot() {
		return this.onShoot;
	}

	public void setOnShoot(boolean b) {
		this.onShoot = b;
	}

	// ================== Jetpack ====================
	public boolean isJetpack() {
		return jetpack;
	}

	public void setJetpack(boolean jetpack) {
		this.jetpack = jetpack;
	}

	// ================== MaxJetpackGauge ====================
	public double getMaxJetpackGauge() {
		return maxJetpackGauge;
	}

	public void setMaxJetpackGauge(double maxJetpackGauge) {
		this.maxJetpackGauge = maxJetpackGauge;
	}

	// ================== JetpackGauge ====================
	public double getJetpackGauge() {
		return jetpackGauge;
	}

	public void setJetpackGauge(double jetpackGauge) {
		this.jetpackGauge = jetpackGauge;
	}

	// ==============================================
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

	public double getHpRecoveryPerSec() {
		return hpRecoveryPerSec;
	}

	public void setHpRecoveryPerSec(double hpRecoveryPerSec) {
		this.hpRecoveryPerSec = hpRecoveryPerSec;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}