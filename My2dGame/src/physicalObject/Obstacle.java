package physicalObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import collision.Collider;
import vector.Vector;

public class Obstacle extends PhysicalObject {
	
    private Graphics2D buffG;
	
	public Obstacle() {
		super();
		this.setWidth(160);
		this.setHeight(160);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		//spriteInitialize();
	}
	
	public Obstacle(Vector position, double width, double height) {
		super();
		int renderingErrorCorrection = 10;
		this.setPosition(position);
		this.setWidth(width + renderingErrorCorrection);
		this.setHeight(height + renderingErrorCorrection);
		this.setCollider(new Collider(this, this.getWidth(), this.getHeight()));
		//spriteInitialize();
	}
}