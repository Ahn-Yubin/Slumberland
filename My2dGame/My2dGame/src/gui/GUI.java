package gui;

import java.awt.Image;
import javax.swing.JPanel;

import vector.Vector;

public class GUI extends JPanel{
	private Vector viewPosition;
	private double guiWidth;
	private double guiHeight;
	private Image uiImage;

	public GUI() {
		this.viewPosition = new Vector();
		this.guiWidth = 0;
		this.guiHeight = 0;
		this.uiImage = createImage((int)this.guiWidth, (int)this.guiHeight);
	}

	public Vector getViewPosition() {
		return viewPosition;
	}

	public void setViewPosition(Vector viewPosition) {
		this.viewPosition = viewPosition;
	}

	public double getGuiWidth() {
		return guiWidth;
	}

	public void setGuiWidth(double width) {
		this.guiWidth = width;
	}

	public double getGuiHeight() {
		return guiHeight;
	}

	public void setGuiHeight(double height) {
		this.guiHeight = height;
	}
}
