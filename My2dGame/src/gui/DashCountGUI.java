package gui;

import java.awt.AlphaComposite;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;

import my2dGame.Model;

public class DashCountGUI extends GUI {
	
	private Image dashBarImg = Toolkit.getDefaultToolkit().getImage("res/img/dashBar.png");
	private Image dashCountComponentImg = Toolkit.getDefaultToolkit().getImage("res/img/dashCountComponent.png");
	
	public DashCountGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		// TODO Auto-generated constructor stub
	}

	public void updateUiImage(Model model) {
		/*
		int dashCount = model.getPlayer().getDashCount();
		int maxDashCount = model.getPlayer().getMaxDashCount();
		
		if(dashCount == maxDashCount) {
			this.getBuffG().setComposite(AlphaComposite.Clear);
			this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		}
		else {
			this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
			this.getBuffG().setColor(Color.black);
			this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
	
			int h = (int)(this.getGuiHeight() * (dashCount/(double)maxDashCount));
			this.getBuffG().setColor(Color.red);
			this.getBuffG().fillRect(0, (int)this.getGuiHeight() - h, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		}
		*/
		//this.getBuffG().drawImage(dashCountComponentImg, 0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight(), null);
		this.getBuffG().setColor(Color.BLACK);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setColor(new Color(64, 64, 64));
		int dp = 5;
		this.getBuffG().fillRect(dp, dp, (int)this.getGuiWidth() - 2*dp, (int)this.getGuiHeight() - 2*dp);
	}
}
