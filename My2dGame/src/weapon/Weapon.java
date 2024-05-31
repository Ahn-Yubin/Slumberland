package weapon;

public class Weapon {
	private int maxAmmo;
	private int ammo;
	private int reloadTime;
	public Weapon(int maxAmmo, int reload_time) {
		this.maxAmmo = maxAmmo;
		this.ammo = maxAmmo;
		this.reloadTime = reload_time;
		reload();
	}
	public void reload() {
		new Thread() {
			public void run() {
				while(true) {
					if(ammo == 0) {
						try {
							System.out.println("reload");
							Thread.sleep(reloadTime);
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
		this.ammo = ammo;
	}
	public double getReload_time() {
		return reloadTime;
	}
	public void setReload_time(int reloadTime) {
		this.reloadTime = reloadTime;
	}
	
}
