package vector;

public class Vector{
	private double x;
	private double y;
	
	public Vector(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}
	
	public Vector sub(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}
	
	public Vector mul(double c) {
		return new Vector(c*this.x, c*this.y);
	}
	
	public double size() {
		return java.lang.Math.sqrt(x*x + y*y);
	}
	
	public Vector unit() {
		double s = this.size();
		if(s == 0)
			return new Vector();
		return new Vector(this.x/s, this.y/s);
	}

	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
