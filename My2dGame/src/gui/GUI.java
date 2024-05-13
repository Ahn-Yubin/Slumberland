package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my2dGame.Model;

public abstract class GUI{
	private double guiWidth;
	private double guiHeight;
	private BufferedImage uiImage;
	private Graphics2D buffG;

	public GUI(double guiWidth, double guiHeight) {
		this.guiWidth = guiWidth;
		this.guiHeight = guiHeight;
		this.uiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.buffG = (Graphics2D) uiImage.getGraphics();
	}
	
	public void updateUiImage(Model model) {
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
	
	public BufferedImage getUiImage() {
		return uiImage;
	}

	public void setUiImage(BufferedImage uiImage) {
		this.uiImage = uiImage;
	}

	public Graphics2D getBuffG() {
		return buffG;
	}

	public void setBuffG(Graphics2D buffG) {
		this.buffG = buffG;
	}
}
