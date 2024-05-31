package gui;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import my2dGame.Model;

public class SpeedometerGUI extends GUI{
	int v;
	InputStream in;
	Font font;
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
	}
	@Override
	public void updateUiImage(Model model) {
		// TODO Auto-generated method stub
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
		this.getBuffG().setFont(font);
		this.getBuffG().drawString("" + (int)(model.getPlayer().getSpeed().size()*0.07) + "km/h", (int)getGuiWidth()/3+10,(int)getGuiHeight()/3+10);
	}
}
