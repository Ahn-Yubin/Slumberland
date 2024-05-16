package gui;

import java.awt.AlphaComposite;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;

import my2dGame.Model;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class DashCountGUI extends GUI {
	
	private Image dashCountComponentImg = Toolkit.getDefaultToolkit().getImage("res/img/dashCountComponent.png");
	
	private int borderThickness = 2; // The thickness of the border of dash bar
	private double whiteSpaceRatio = 0.5; // This value determines white space height which equals with (this value * dash component height)
	private double dashBarSlope = 1; // The Slope of the dash bar
	
	public DashCountGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		setMaxAlpha(0.8f);
		setMinAlpha(0.1f);
		setAlpha(getMinAlpha());
		// TODO Auto-generated constructor stub
	}

	public void updateUiImage(Model model) {
		int dashCount = model.getPlayer().getDashCount();
		int maxDashCount = model.getPlayer().getMaxDashCount();

		this.getTmpBuffG().setColor(Color.BLACK);
		this.getTmpBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		
		this.getTmpBuffG().setColor(new Color(64, 64, 64));
		this.getTmpBuffG().fillRect(borderThickness, borderThickness, (int)this.getGuiWidth() - 2*borderThickness, (int)this.getGuiHeight() - 2*borderThickness);
		
		double h = (this.getGuiHeight() - 2*borderThickness) / (maxDashCount + whiteSpaceRatio*(maxDashCount-1));
		double a = this.getGuiHeight() - borderThickness - h;
		double d = -(1+whiteSpaceRatio)*h;
		
		for(int i=0; i< dashCount; i++)
			this.getTmpBuffG().drawImage(dashCountComponentImg, borderThickness, (int)(a+i*d), (int)this.getGuiWidth() - 2*borderThickness, (int)h, null);
		
		double calculatedDashBarSlope = (dashBarSlope * this.getGuiHeight()) / (this.getGuiHeight() - dashBarSlope * this.getGuiWidth());
		//====================== AffineTransform ======================
		AffineTransform trans = new AffineTransform();
		trans.scale(1, this.getGuiHeight() / (this.getGuiHeight() + calculatedDashBarSlope * this.getGuiWidth())); // S2
		trans.shear(0, calculatedDashBarSlope);																	   // S1
		//=============================================================
		
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
		this.getBuffG().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.getAlpha()));
		this.getBuffG().drawImage(this.getTmpUiImage(), trans, null);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
	}
}