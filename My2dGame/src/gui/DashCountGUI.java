package gui;

public class DashCountGUI extends GUI {
	
	public DashCountGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		// TODO Auto-generated constructor stub
	}

	public void updateUiImage(int dashCount, int maxDashCount) {
		//this.getBuffG().clearRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		System.out.println((int)this.getGuiWidth() + " " + (int)(this.getGuiHeight() * (dashCount/(double)maxDashCount)));
		//this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)(this.getGuiHeight() * (dashCount/(double)maxDashCount)));
	}
}
