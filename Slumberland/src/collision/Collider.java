package collision;

import physicalObject.PhysicalObject;
import vector.Vector;


public class Collider{
	
	private PhysicalObject includedObject;
	private double width;
	private double height;
	
	public Collider(PhysicalObject includedObject, double width, double height) {
		this.includedObject = includedObject;
		this.width = width;
		this.height = height;
	}
	
	public Vector[] getRotatedNormal(){
		double cos = this.includedObject.getAngle().getX()/this.includedObject.getAngle().size();
		double sin = this.includedObject.getAngle().getY()/this.includedObject.getAngle().size();
		
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



