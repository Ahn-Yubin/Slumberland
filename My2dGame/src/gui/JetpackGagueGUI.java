package gui;

public class JetpackGagueGUI extends GUI {

	public JetpackGagueGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		// TODO Auto-generated constructor stub
	}
	
	public void updateUiImage(double jetpackGauge, double maxJetpackGauge) {
		this.getBuffG().clearRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), 
				(int)(this.getGuiHeight() * (jetpackGauge/(double)maxJetpackGauge)));
	}

}
