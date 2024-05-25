package gui;

import java.awt.Color;
import java.awt.AlphaComposite;

import my2dGame.Model;

public class JetpackGaugeGUI extends GUI {
	
	//============ Calculated ================
	private final double sqrt2 = 1.41421356237;
	private final double r = -sqrt2*this.getGuiWidth() + this.getGuiHeight();
	private final double dr = sqrt2*this.getGuiWidth() + (-1 + sqrt2/2)*this.getGuiHeight();
	private final int theta = 45;
	//========================================
	
	private double dangerPercent = 0.4;
	
	public JetpackGaugeGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		setMaxAlpha(0.9f);
		setMinAlpha(0.7f);
		setAlpha(getMinAlpha());
		// TODO Auto-generated constructor stub
	}
	
	public void updateUiImage(Model model) {
		double jetPackGauge = model.getPlayer().getJetpackGauge();
		double maxJetpackGauge = model.getPlayer().getMaxJetpackGauge();
		
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
		this.getBuffG().setColor(Color.BLACK);
		this.getBuffG().fillArc((int)(this.getGuiWidth()-2*(r+dr)), (int)(this.getGuiHeight()/2 - (r+dr)), (int)(2*(r+dr)), (int)(2*(r+dr)), -theta, 2*theta);
		
		if (jetPackGauge <= dangerPercent*maxJetpackGauge)
			this.getBuffG().setColor(Color.RED);
		else
			this.getBuffG().setColor(Color.WHITE);
		this.getBuffG().fillArc((int)(this.getGuiWidth()-2*(r+dr)), (int)(this.getGuiHeight()/2 - (r+dr)), (int)(2*(r+dr)), (int)(2*(r+dr)), -theta, (int)(2*theta*(jetPackGauge/maxJetpackGauge)));
		
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillArc((int)(this.getGuiWidth()-2*r-dr), (int)(this.getGuiHeight()/2 - r), (int)(2*r), (int)(2*r), -theta, 2*theta);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
	}
}
