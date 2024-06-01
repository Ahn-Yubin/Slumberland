package weapon;

public class Weapon {
	private int maxAmmo;
	private int ammo;
	private double reloadTime;
	
	public Weapon(int maxAmmo, double reloadTime) {
		this.maxAmmo = maxAmmo;
		this.ammo = maxAmmo;
		this.reloadTime = reloadTime;
		reload();
	}
	
	public void reload() {
		new Thread() {
			public void run() {
				while(true) {
					if(ammo == 0) {
						try {
							System.out.println("reload");
							Thread.sleep((int)(1000*reloadTime));
							ammo = maxAmmo;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
}