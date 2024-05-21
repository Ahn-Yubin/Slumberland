package gui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my2dGame.Model;

public abstract class GUI{
	private double guiWidth;
	private double guiHeight;
	
	private BufferedImage uiImage;
	private Graphics2D buffG;
	
	private float alpha = 0.0f;
	private float maxAlpha = 1.0f;
	private float minAlpha = 1.0f;
	
	private int alphaControlCount = 0;
	
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
		this.getBuffG().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, this.getAlpha()));
		this.getBuffG().drawImage(uiImage, 0, 0, null);
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
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

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public float getMaxAlpha() {
		return maxAlpha;
	}

	public void setMaxAlpha(float maxAlpha) {
		this.maxAlpha = maxAlpha;
	}

	public float getMinAlpha() {
		return minAlpha;
	}

	public void setMinAlpha(float minAlpha) {
		this.minAlpha = minAlpha;
	}

	public int getAlphaControlCount() {
		return alphaControlCount;
	}

	public void setAlphaControlCount(int alphaControlCount) {
		this.alphaControlCount = alphaControlCount;
	}
}
