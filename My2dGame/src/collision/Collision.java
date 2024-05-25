package collision;

import vector.Vector;
import java.lang.Math;

public class Collision {
	private boolean isCollision;
	private Vector translationVector1;
	private Vector translationVector2;
	
	public Collision(boolean isCollision, Vector TV1, Vector TV2) {
		this.isCollision = isCollision;
		this.translationVector1 = TV1;
		this.translationVector2 = TV2;
	}

	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	public Vector getTranslationVector1() {
		return translationVector1;
	}

	public void setTranslationVector1(Vector translationVector1) {
		this.translationVector1 = translationVector1;
	}

	public Vector getTranslationVector2() {
		return translationVector2;
	}

	public void setTranslationVector2(Vector translationVector2) {
		this.translationVector2 = translationVector2;
	}

	public static Collision collisionTest(Collider col1, Collider col2) {
		Vector[] myRotatedNormal = col1.getRotatedNormal();
		Vector[] othersRotatedNormal = col2.getRotatedNormal();
		Vector[] separatingAxis = {myRotatedNormal[0].unit(), myRotatedNormal[1].unit(), othersRotatedNormal[0].unit(), othersRotatedNormal[1].unit()};
		
		double r1 = 0, r2 = 0, r0 = 0;
		double R = 0;
		
		Vector SA = separatingAxis[0];
		
		double RwhenR0IsMin = 0;
		double minimumR0 = Double.MAX_VALUE; 
		Vector axisWhenR0IsMin = SA;
		
		for(int i=0; i<4; i++) {
			SA = separatingAxis[i];
			R = (col1.getIncludedObject().getPosition().sub(col2.getIncludedObject().getPosition())).dot(SA);
			r0 = Math.abs(R);
			
			r1 = Math.abs(myRotatedNormal[0].dot(SA)) + Math.abs(myRotatedNormal[1].dot(SA));
			r2 = Math.abs(othersRotatedNormal[0].dot(SA)) + Math.abs(othersRotatedNormal[1].dot(SA));
			
			if(r0 > r1 + r2)
				return new Collision(false, new Vector(), new Vector());
			
			if(minimumR0 > (r1+r2-r0)) {
				minimumR0 = (r1+r2-r0);
				//System.out.println(""+minimumR0);
				RwhenR0IsMin = R;
				axisWhenR0IsMin = SA;
			}
		}
		
		if(RwhenR0IsMin < 0)
			minimumR0 = -minimumR0;
		
		Vector MTV = axisWhenR0IsMin.mul(minimumR0);
		double m1 = col1.getIncludedObject().getMass();
		double m2 = col2.getIncludedObject().getMass();
		if(m1 < 0)
			return new Collision(true, new Vector(), MTV.mul(-1));
		if(m2 < 0)
			return new Collision(true, MTV, new Vector());
		System.out.println("=========계산된거============");
		System.out.println("MTV = " + MTV);
		System.out.println("tv1 = " + MTV.mul(m2/(m1+m2)));
		System.out.println("tv2 = " + MTV.mul(-m1/(m1+m2)));
		System.out.println("=====================");
		return new Collision(true, MTV.mul(m2/(m1+m2)), MTV.mul(-m1/(m1+m2)));
	}
}