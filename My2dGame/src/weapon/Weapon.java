package weapon;

public class Weapon {
	private int maxAmmo;
	private int ammo;
	private double reloadTime;
	private boolean isReloading;
	private double attackDamage;
	
	public Weapon(int maxAmmo, double reloadTime, double attackDamage) {
		this.maxAmmo = maxAmmo;
		this.ammo = maxAmmo;
		this.reloadTime = reloadTime;
		this.setAttackDamage(attackDamage);
		reload();
	}

	public void reload() {
		new Thread() {
			public void run() {
				while(true) {
					System.out.println(ammo);
					if(ammo == 0 && !isReloading) {
						settingAmmo();
					}
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void settingAmmo() {
		new Thread() {
			public void run() {
				try {
					setReloading(true); 
					Thread.sleep((int)(1000*reloadTime));
					ammo = maxAmmo;
					setReloading(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public int getMaxammo() {
		return maxAmmo;
	}

	public void setMaxammo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		if (ammo < 0)
			ammo = 0;
		this.ammo = ammo;
	}

	public double getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(double reloadTime) {
		this.reloadTime = reloadTime;
	}

	public boolean isReloading() {
		return isReloading;
	}

	public void setReloading(boolean isReloading) {
		this.isReloading = isReloading;
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}
}
