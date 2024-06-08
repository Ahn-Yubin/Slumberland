package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import gameSystem.Model;

public class HPBarGUI extends GUI {	
	private int borderThickness = 5; // The thickness of the border of HP bar
	private double whiteSpaceRatio = 0.1; // This value determines white space width which equals with (this value * hpBar component width)
	private double hpBarSlope = -0.5; // The Slope of the HPBar - Coded based on negative numbers

	private int roundDeg = 15;

	private Graphics2D tmpBuffG;
	private BufferedImage tmpUiImage;

	public HPBarGUI(double guiWidth, double guiHeight) {
		// TODO Auto-generated constructor stub
		super(guiWidth, guiHeight);
		setMaxAlpha(0.8f);
		setMinAlpha(0.5f);
		setAlpha(getMinAlpha());
		this.tmpUiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.tmpBuffG = (Graphics2D) tmpUiImage.getGraphics();
	}

	public void updateUiImage(Model model) {
		double currentHP = model.getPlayer().getHp();

		double calculatedHPBarSlope = (hpBarSlope * this.getGuiWidth()) / (this.getGuiWidth() + hpBarSlope * this.getGuiHeight());
		this.tmpBuffG.setComposite(AlphaComposite.Clear);
		this.tmpBuffG.fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.tmpBuffG.setComposite(AlphaComposite.SrcOver);


		this.tmpBuffG.setColor(Color.BLACK);
		this.tmpBuffG.fillRoundRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight(), roundDeg, roundDeg);

		this.tmpBuffG.setColor(new Color(64, 64, 64));
		this.tmpBuffG.fillRoundRect(borderThickness, borderThickness, (int)this.getGuiWidth() - 2*borderThickness, (int)this.getGuiHeight() - 2*borderThickness, roundDeg, roundDeg);

		int Barratio = 10;
		double hpPerBar = model.getPlayer().getMaxHP()/Barratio;
		int drawHPBar = (int)(currentHP / hpPerBar);


		double w = (this.getGuiWidth() - 2*borderThickness) / (Barratio + whiteSpaceRatio*(Barratio-1));
		double h = this.getGuiHeight() - 2*borderThickness;
		double a = borderThickness;
		double d = (1+whiteSpaceRatio)*w;

		this.tmpBuffG.setColor(Color.WHITE);
		for(int i = 0; i <= drawHPBar; i++) {
			if(i == drawHPBar)
				this.tmpBuffG.fillRoundRect((int)(a + i * d) , borderThickness,  (int)((w * (currentHP % hpPerBar)) / hpPerBar), (int)h, roundDeg, roundDeg);
			else
				this.tmpBuffG.fillRoundRect((int)(a + i * d) , borderThickness, (int)w, (int)h, roundDeg, roundDeg);
		}
		//====================== AffineTransform ======================
		AffineTransform trans = new AffineTransform();
		trans.scale(this.getGuiWidth() / (this.getGuiWidth() - calculatedHPBarSlope * this.getGuiHeight()), 1); //S3
		trans.translate( -calculatedHPBarSlope * this.getGuiHeight(), 0);										//S2
		trans.shear(calculatedHPBarSlope, 0);												     				//S1
		//=============================================================

		this.getBuffG().drawImage(this.tmpUiImage, trans, null);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
	}
}