package physicalObject;

public abstract class Entity extends PhysicalObject {
	private int HP = 100;
	private int maxHP = 100;
	
	private boolean isOnGround = false;
	
	public int getHP() {
		return HP;
	}
	
	public void setHP(int hP) {
		HP = hP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}
}
