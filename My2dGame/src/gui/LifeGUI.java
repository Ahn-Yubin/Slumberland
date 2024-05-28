package gui;

import java.awt.AlphaComposite;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import my2dGame.Model;

public class LifeGUI extends GUI {
	
	
	private Image image=Toolkit.getDefaultToolkit().getImage("res/img/life.png");
	
	public LifeGUI(double guiWidth, double guiHeight) {
		// TODO Auto-generated constructor stub
		super(guiWidth, guiHeight);
		setAlpha(1.0f);
		
	}
	
	public void updateUiImage(Model model) {
		int currentLife=model.getPlayer().getLife();
		int maxLife=model.getPlayer().getMaxLife();
		
		this.getBuffG().setComposite(AlphaComposite.Clear);
		this.getBuffG().fillRect(0, 0, (int)this.getGuiWidth(), (int)this.getGuiHeight());
		this.getBuffG().setComposite(AlphaComposite.SrcOver);
		
		String text=": "+ Integer.toString(currentLife)+" remained";
		this.getBuffG().setColor(Color.black);
		this.getBuffG().setFont(new Font("Arial",Font.BOLD,20));
		this.getBuffG().drawString(text,(int)getGuiWidth()/3+10,(int)getGuiHeight()/3+10);
		this.getBuffG().drawImage(image,15,15,(int)getGuiWidth()/3,(int)getGuiHeight()/3,null);
	}
	
}
