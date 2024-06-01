package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import my2dGame.Model;

public class DetailedHPGUI extends GUI{
	private int v;
	private InputStream in;
	private Font font;
	
	public DetailedHPGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		setMaxAlpha(0.9f);
		setMinAlpha(0.7f);
		setAlpha(getMinAlpha());
		// TODO Auto-generated constructor stub
		setFont();
	}
	
	public void setFont() {
		try {
			in = new BufferedInputStream(new FileInputStream("res/font/koverwatch.ttf"));
			font = Font.createFont(Font.TRUETYPE_FONT, in);
			font = font.deriveFont(40f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getBuffG().setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		this.getBuffG().setFont(font);
	}
	
	@Override
	public void updateUiImage(Model model) {
		// TODO Auto-generated method stub
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
		int hp = (int)(Math.ceil(model.getPlayer().getHp()));
		int maxHP = (int)(Math.ceil(model.getPlayer().getMaxHP()));
		
		this.getBuffG().drawString(hp + " / " + maxHP, 0,(int)getGuiHeight());
	}
}
