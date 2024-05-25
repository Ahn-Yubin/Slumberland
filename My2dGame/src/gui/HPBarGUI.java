package gui;

import java.awt.AlphaComposite;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;

import my2dGame.Model;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class HPBarGUI extends GUI {
	
	private Image HPBarComponentImg = Toolkit.getDefaultToolkit().getImage("res/img/dashCountComponent.png");
	
	private int borderThickness = 2; // The thickness of the border of HP bar
	private double whiteSpaceRatio = 0.1; // This value determines white space width which equals with (this value * hpBar component width)
	private double hpBarSlope = -0.5; // The Slope of the HPBar - Coded based on negative numbers
	
	private Graphics2D tmpBuffG;
	private BufferedImage tmpUiImage;
	
	public HPBarGUI(double guiWidth, double guiHeight) {
		// TODO Auto-generated constructor stub
		super(guiWidth, guiHeight);
		setMaxAlpha(0.8f);
		setMinAlpha(0.3f);
		setAlpha(getMinAlpha());
		this.tmpUiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.tmpBuffG = (Graphics2D) tmpUiImage.getGraphics();
	}

	public void updateUiImage(Model model) {
		int currentHP = model.getPlayer().getHP();
		int maxHP = model.getPlayer().getMaxHP();
		double calculatedHPBarSlope = (hpBarSlope * this.getGuiWidth()) / (this.getGuiWidth() + hpBarSlope * this.getGuiHeight());
		this.tmpBuffG.setComposite(AlphaComposite.Clear);
		this.tmpBuffG.fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.tmpBuffG.setComposite(AlphaComposite.SrcOver);
			
		
		this.tmpBuffG.setColor(Color.BLACK);
		this.tmpBuffG.fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		
		this.tmpBuffG.setColor(new Color(64, 64, 64));
		this.tmpBuffG.fillRect(borderThickness, borderThickness, (int)this.getGuiWidth() - 2*borderThickness, (int)this.getGuiHeight() - 2*borderThickness);
		
		int Barratio = 10;
		
		double w = (this.getGuiWidth() - 2*borderThickness) / (Barratio + whiteSpaceRatio*(Barratio-1));
		double h = this.getGuiHeight() - 2*borderThickness;
		double a = borderThickness;
		double d = (1+whiteSpaceRatio)*w;
		
		for(int i=0; i<currentHP / Barratio; i++)
			this.tmpBuffG.drawImage(HPBarComponentImg, (int)(a + i * d) , borderThickness, (int)w, (int)h, null);
		//====================== AffineTransform ======================
		AffineTransform trans = new AffineTransform();
		trans.translate(- calculatedHPBarSlope * this.getGuiHeight(), 0);										//S3
		trans.scale(this.getGuiWidth() / (this.getGuiWidth() - calculatedHPBarSlope * this.getGuiHeight()), 1); //S2
		trans.shear(calculatedHPBarSlope, 0);												     				//S1
		//=============================================================

		this.getBuffG().drawImage(this.tmpUiImage, trans, null);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
	}
}
