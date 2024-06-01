package physicalObject;

public abstract class Entity extends PhysicalObject {
	private double hp = 100;
	private double maxHP = 100;
	
	private boolean isOnGround = false;
	
	public double getHp() {
		return hp;
	}
	
	public void setHp(double hp) {
		this.hp = hp;
	}
	
	public double getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(double maxHP) {
		this.maxHP = maxHP;
	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}
}
