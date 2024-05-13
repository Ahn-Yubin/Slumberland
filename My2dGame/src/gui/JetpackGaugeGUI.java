package gui;

import java.awt.Color;
import java.awt.AlphaComposite;

import my2dGame.Model;

public class JetpackGaugeGUI extends GUI {

	public JetpackGaugeGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		// TODO Auto-generated constructor stub
	}
	
	public void updateUiImage(Model model) {
		double jetPackGauge = model.getPlayer().getJetpackGauge();
		double maxJetpackGauge = model.getPlayer().getMaxJetpackGauge();
		
		if(jetPackGauge == maxJetpackGauge) {
			this.getBuffG().setComposite(AlphaComposite.Clear);
			this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		}
		else {
			this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
			this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
			
			this.getBuffG().setColor(Color.black);
			this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
			
			double h = (int)(this.getGuiHeight() * (jetPackGauge/maxJetpackGauge));
			
			this.getBuffG().setColor(Color.blue);
			this.getBuffG().fillRect(0, (int)(this.getGuiHeight() - h), (int)this.getGuiWidth(), (int)this.getGuiHeight());
		}
	}
}
