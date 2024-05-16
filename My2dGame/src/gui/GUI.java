package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my2dGame.Model;

public abstract class GUI{
	private double guiWidth;
	private double guiHeight;
	
	private BufferedImage uiImage;
	private Graphics2D buffG;
	
	private Graphics2D tmpBuffG;
	private BufferedImage tmpUiImage;
	
	private float alpha = 0.0f;
	private float maxAlpha = 1.0f;
	private float minAlpha = 1.0f;
	
	private boolean isAlphaIncreasing = false;

	public GUI(double guiWidth, double guiHeight) {
		this.guiWidth = guiWidth;
		this.guiHeight = guiHeight;
		this.uiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.buffG = (Graphics2D) uiImage.getGraphics();
		
		this.tmpUiImage = new BufferedImage((int)guiWidth, (int)guiHeight, BufferedImage.TYPE_INT_ARGB);
		this.setTmpBuffG((Graphics2D) tmpUiImage.getGraphics());
	}
	
	public void updateUiImage(Model model) {
	}
	
	public void alphaControl() {
		new Thread() {
			public void run() {
				try {
					//System.out.println("Call 투명도 조절 메커니즘" + getAlpha());
					while(isAlphaIncreasing()) {
						System.out.println("알파 증가중" + getAlpha());
						setAlpha(getAlpha() + 0.05f);
						if(getAlpha() > getMaxAlpha())
							setAlpha(getMaxAlpha());
						Thread.sleep(100);
					}
					while(!isAlphaIncreasing()) {
						System.out.println("알파 감소중" + getAlpha());
						setAlpha(getAlpha() - 0.05f);
						if(getAlpha() < getMinAlpha())
							setAlpha(getMinAlpha());
						Thread.sleep(100);
					}
					//System.out.println("투명도 증가 끝");
				} catch (InterruptedException e) {
					e. printStackTrace();
				}
			}
		}.start();
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

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public Graphics2D getTmpBuffG() {
		return tmpBuffG;
	}

	public void setTmpBuffG(Graphics2D tmpBuffG) {
		this.tmpBuffG = tmpBuffG;
	}
	
	public BufferedImage getTmpUiImage() {
		return tmpUiImage;
	}

	public void setTmpUiImage(BufferedImage tmpUiImage) {
		this.tmpUiImage = tmpUiImage;
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
	
	public boolean isAlphaIncreasing() {
		return isAlphaIncreasing;
	}

	public void setAlphaIncreasing(boolean isAlphaIncreasing) {
		this.isAlphaIncreasing = isAlphaIncreasing;
	}

}
