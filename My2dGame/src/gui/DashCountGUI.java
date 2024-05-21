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
	
	private Graphics2D tmpBuffG;
	private BufferedImage tmpUiImage;
	
	public DashCountGUI(double guiWidth, double guiHeight) {
		// TODO Auto-generated constructor stub
		super(guiWidth, guiHeight);
		setMaxAlpha(0.7f);
		setMinAlpha(0.3f);
		setAlpha(getMinAlpha());
		this.tmpUiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.tmpBuffG = (Graphics2D) tmpUiImage.getGraphics();
	}

	public void updateUiImage(Model model) {
		int dashCount = model.getPlayer().getDashCount();
		int maxDashCount = model.getPlayer().getMaxDashCount();
		
		this.tmpBuffG.setComposite(AlphaComposite.Clear);
		this.tmpBuffG.fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.tmpBuffG.setComposite(AlphaComposite.SrcOver);
			
		this.tmpBuffG.setColor(Color.BLACK);
		this.tmpBuffG.fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		
		this.tmpBuffG.setColor(new Color(64, 64, 64));
		this.tmpBuffG.fillRect(borderThickness, borderThickness, (int)this.getGuiWidth() - 2*borderThickness, (int)this.getGuiHeight() - 2*borderThickness);
		
		double h = (this.getGuiHeight() - 2*borderThickness) / (maxDashCount + whiteSpaceRatio*(maxDashCount-1));
		double a = this.getGuiHeight() - borderThickness - h;
		double d = -(1+whiteSpaceRatio)*h;
		
		for(int i=0; i< dashCount; i++)
			this.tmpBuffG.drawImage(dashCountComponentImg, borderThickness, (int)(a+i*d), (int)this.getGuiWidth() - 2*borderThickness, (int)h, null);
		
		double calculatedDashBarSlope = (dashBarSlope * this.getGuiHeight()) / (this.getGuiHeight() - dashBarSlope * this.getGuiWidth());
		//====================== AffineTransform ======================
		AffineTransform trans = new AffineTransform();
		trans.scale(1, this.getGuiHeight() / (this.getGuiHeight() + calculatedDashBarSlope * this.getGuiWidth())); // S2
		trans.shear(0, calculatedDashBarSlope);																	   // S1
		//=============================================================

		this.getBuffG().drawImage(this.tmpUiImage, trans, null);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
	}
}