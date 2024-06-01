package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import my2dGame.Model;

public class WeaponGUI extends GUI{
	private int v;
	private InputStream in;
	private Font font;
	private Image image;
	
	public WeaponGUI(double guiWidth, double guiHeight) {
		super(guiWidth, guiHeight);
		setMaxAlpha(0.9f);
		setMinAlpha(0.7f);
		setAlpha(getMinAlpha());
		// TODO Auto-generated constructor stub
		setFont();
		image = Toolkit.getDefaultToolkit().getImage("res/img/playerWeapon.png");
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
		
		int maxAmmo = model.getPlayer().getWeapon().getMaxammo();
		int currentAmmo = model.getPlayer().getWeapon().getAmmo();
		
		if(currentAmmo > 0) {
			this.getBuffG().drawImage(image, 0, 0, 90, 50, null);
			this.getBuffG().drawString("" + currentAmmo + " / " + maxAmmo, 110,(int)getGuiHeight() - 10);
		}
		else {
			font = font.deriveFont(50f);
			this.getBuffG().setFont(font);
			this.getBuffG().drawString("reloading...", 10,(int)getGuiHeight() - 5);
			font = font.deriveFont(40f);
			this.getBuffG().setFont(font);
		}
	}
}

