package physicalObject.entity;

import physicalObject.PhysicalObject;

public abstract class Entity extends PhysicalObject {
	private double hp;
	private double maxHP;
	
	public Entity() {
		this.maxHP = 100;
		this.hp = this.maxHP;
	}
	
	private boolean isOnGround = false;
	
	public double getHp() {
		return hp;
	}
	
	public void setHp(double hp) {
        if(hp > maxHP)
            this.hp = maxHP;
        else if(hp < 0)
            this.hp = 0;
        else
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