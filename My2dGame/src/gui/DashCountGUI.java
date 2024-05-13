package gui;

import java.awt.AlphaComposite;
import java.awt.Color;

import my2dGame.Model;

public class DashCountGUI extends GUI {
	
	public DashCountGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		// TODO Auto-generated constructor stub
	}

	public void updateUiImage(Model model) {
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
	}
}
