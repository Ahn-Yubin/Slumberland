package collider;

import java.lang.Math;

import physicalObject.PhysicalObject;
import vector.Vector;


public class Collider {
	private PhysicalObject includedObject;
	private double width;
	private double height;
	
	public Collider(PhysicalObject includedObject, double width, double height) {
		this.includedObject = includedObject;
		this.width = width;
		this.height = height;
	}
	
	public boolean isCollision(Collider col) {
		Vector[] myRotatedNormal = this.getRotatedNormal();
		Vector[] othersRotatedNormal = col.getRotatedNormal();
		Vector[] separatingAxis = {myRotatedNormal[0].unit(), myRotatedNormal[1].unit(), othersRotatedNormal[0].unit(), othersRotatedNormal[1].unit()};
		
		double r1 = 0, r2 = 0, r0 = 0;
		for(Vector SA : separatingAxis) {
			r1 = Math.abs(myRotatedNormal[0].dot(SA)) + Math.abs(myRotatedNormal[1].dot(SA));
			r2 = Math.abs(othersRotatedNormal[0].dot(SA)) + Math.abs(othersRotatedNormal[1].dot(SA));
			r0 = Math.abs((this.includedObject.getPosition().sub(col.includedObject.getPosition())).dot(SA));
			if(r0 > r1 + r2)
				return false;
		}
		return true;
	}
	
	public Vector[] getRotatedNormal(){
		double cos = this.includedObject.getDirection().getX()/this.includedObject.getDirection().size();
		double sin = this.includedObject.getDirection().getY()/this.includedObject.getDirection().size();
		
		Vector rotatedNormalX = new Vector(cos*this.getWidth()/2, sin*this.getWidth()/2);
		Vector rotatedNormalY = new Vector(-sin*this.getHeight()/2, cos*this.getHeight()/2);
		
		Vector[] rotatedNomarls = {rotatedNormalX, rotatedNormalY};
		return rotatedNomarls;
	}

	public PhysicalObject getIncludedObject() {
		return includedObject;
	}

	public void setIncludedObject(PhysicalObject includedObject) {
		this.includedObject = includedObject;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}



