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
	private Graphics2D buffG2;
	private BufferedImage uiTmpImage;
	public DashCountGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		uiTmpImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		buffG2 = (Graphics2D) uiTmpImage.getGraphics();
		// TODO Auto-generated constructor stub
	}

	public void updateUiImage(Model model) {
		int dashCount = model.getPlayer().getDashCount();
		int maxDashCount = model.getPlayer().getMaxDashCount();

		this.getBuffG().setColor(Color.BLACK);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setColor(new Color(64, 64, 64));
		
		int dp = 2;
		this.getBuffG().fillRect(dp, dp, (int)this.getGuiWidth() - 2*dp, (int)this.getGuiHeight() - 2*dp);
		
		double k = 0.5;
		double h = (this.getGuiHeight() - 2*dp) / (maxDashCount + k*(maxDashCount-1));
		double a = this.getGuiHeight() - dp - h;
		double d = -(1+k)*h;
		
		for(int i=0; i< dashCount; i++) {
			this.getBuffG().drawImage(dashCountComponentImg, dp, (int)(a+i*d), (int)this.getGuiWidth() - 2*dp, (int)h, null);
		}
		
		AffineTransform trans = new AffineTransform();
		double value = 1;
		trans.shear(0, value);
		trans.scale(1, this.getGuiHeight() / (this.getGuiHeight() + value * this.getGuiWidth()));
		this.buffG2.drawImage(this.getUiImage(), trans, null);
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		this.getBuffG().drawImage(uiTmpImage, 0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight(), null);
	}
}