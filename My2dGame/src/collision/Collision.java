package collision;

import vector.Vector;
import java.lang.Math;

public class Collision {
	private boolean isCollision;
	private Vector minimumTranslationVector;
	
	public Collision(boolean isCollision, Vector MTV) {
		this.isCollision = isCollision;
		this.minimumTranslationVector = MTV;
	}

	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	public Vector getMinimumTranslationVector() {
		return minimumTranslationVector;
	}

	public void setMinimumTranslationVector(Vector minimumTranslationVector) {
		this.minimumTranslationVector = minimumTranslationVector;
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
				return new Collision(false, new Vector());
			
			if(minimumR0 > (r1+r2-r0)) {
				minimumR0 = (r1+r2-r0);
				//System.out.println(""+minimumR0);
				RwhenR0IsMin = R;
				axisWhenR0IsMin = SA;
			}
		}
		
		if(RwhenR0IsMin < 0)
			minimumR0 = -minimumR0;
		
		return new Collision(true, axisWhenR0IsMin.mul(minimumR0));
	}
}
