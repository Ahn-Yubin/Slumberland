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

import gameSystem.Model;

public class SpeedometerGUI extends GUI{
	private InputStream in;
	private Font font;
	
	public SpeedometerGUI(double guiWidth, double guiHeight) {
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
			//font = font.deriveFont(font.ITALIC);
			
			AffineTransform trans = new AffineTransform();
			trans.shear(-0.5, 0);		
			font = font.deriveFont(trans);
			
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
		
		double speed = model.getPlayer().getSpeed().size()*0.09;
		if(model.getGameState().equals("GameOver"))
			speed = 0;
		int rValue = (int)(255/(1 + Math.exp(4*speed/255 - 2)));
		this.getBuffG().setColor(new Color(rValue, 255, 255));
		
		this.getBuffG().drawString(String.format("%03d", (int)speed) + "km/h", 0, (int)getGuiHeight());
	}
}
