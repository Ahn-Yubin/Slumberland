package my2dGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class View extends JFrame implements Runnable{
	Controller controller;
	Model model;
	
	private Image buffImg;
    private Graphics2D buffG;
	
	private Vector position = new Vector(0, -100);
	private double viewWidth = 1920;
	private double viewHeight = 1080;
	
	private int resolutionWidth = 1600;
	private int resolutionHeight = 900;
	
	public View(Model model){
        // Settings for the frame.
        setTitle("my2dGame"); // Set frame title.
        setSize(resolutionWidth, resolutionHeight); // Set the size of the frame.
        setResizable(false); // Set the frame size to not change.
        setVisible(true); // Show frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
        this.model = model;
    }
    
    @Override
    public void paint(Graphics g) {
    	buffImg = createImage(resolutionWidth, resolutionHeight); // create resolutionWidth X resolutionHeight empty image
    	buffG = (Graphics2D) buffImg.getGraphics();
    	update(g);
    }
    
    @Override
    public void update(Graphics g) {
    	buffG.clearRect(0, 0, resolutionWidth, resolutionHeight); // clear image
    	drawObject(buffG, model.getPlayer());
    	
    	drawCrosshair(buffG);
    	
    	buffG.drawString(Double.toString(model.getPlayer().getJetpackGauge()), 200, 200);
    	Vector start = worldCorToViewCor(new Vector(0, 0));
    	Vector end = worldCorToViewCor(new Vector(viewWidth, 0));
    	buffG.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
    	buffG.drawString(Integer.toString(model.getPlayer().getDashCount()), 100, 100);
    	
    	drawBullet(buffG);
    	
        g.drawImage(buffImg, 0, 0, this); // Move the image(buffImg) drawn in the buffer(buffG) to screen g.
        repaint();
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				//System.out.println("painted");
				repaint();
				Thread.sleep(6); // about 144 frame
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector worldCorToViewCor(Vector worldCor) {
		Vector tmp = worldCor.sub(this.position);
		return new Vector(tmp.getX() * (resolutionWidth/viewWidth), resolutionHeight - tmp.getY() * (resolutionHeight/viewHeight));
	}
	
	public Vector viewCorToWorldCor(Vector viewCor) {
		double x = viewCor.getX() * (viewWidth/resolutionWidth);
		double y = (-viewCor.getY() + resolutionHeight) * (viewHeight/resolutionHeight);
		return this.position.add(new Vector(x, y));
	}
	
	public void drawPlayer(Graphics2D buffG) {
		int mouseX = this.getMousePosition().x;
		int mouseY = this.getMousePosition().y;
		Vector mouse_position = viewCorToWorldCor(new Vector(mouseX, mouseY));
		
    	Vector player_position = model.getPlayer().getPosition();
    	Vector view_cor = worldCorToViewCor(player_position);
    	Image sprite = model.getPlayer().getSprite();
    	int w = sprite.getWidth(rootPane);
    	int h = sprite.getHeight(rootPane);
    	if(player_position.sub(mouse_position).getX() < 0)
    		w = -w;
        buffG.drawImage(sprite, (int)view_cor.getX() - w/2 , (int)view_cor.getY() - h/2 , w, h, this); // 유저 비행기 그리기.
    	buffG.drawOval((int)view_cor.getX()-5, (int)view_cor.getY()-5, 10, 10);
	}
	
	public void drawBullet(Graphics2D buffG) {
		for(Bullet b : model.getBulletList()) {
			drawObject(buffG, b);
		}
	}
	
	public void drawObject(Graphics2D buffG, VisiableObject obj) {
	      Vector position = obj.getPosition();
	      Image sprite = obj.getSprite(); 
	      double w = obj.getWidth();
	      double h = obj.getHeight();
	      Vector view_cor = worldCorToViewCor(position);
	      
	      AffineTransform trans = new AffineTransform();
	      //======================= AffineTransform =======================
	      trans.translate(view_cor.getX(), view_cor.getY());                                                                     //S3
	      trans.translate(-w*(resolutionHeight/viewHeight)/2, -h*(resolutionHeight/viewHeight)/2);                                //S2
	      trans.scale((w/sprite.getWidth(this))*(resolutionWidth/viewWidth), (h/sprite.getHeight(this))*(resolutionHeight/viewHeight));  //S1
	      //============================================================
	      
	      buffG.drawImage(sprite, trans, this);
	   }
	
	public void drawObject(Graphics2D buffG, Bullet obj) {
	      Vector position = obj.getPosition();
	      Image sprite = obj.getSprite(); 
	      double w = obj.getWidth();
	      double h = obj.getHeight();
	      Vector view_cor = worldCorToViewCor(position);
	      
	      AffineTransform trans = new AffineTransform();
	    //======================= AffineTransform =======================
	      trans.translate(view_cor.getX(), view_cor.getY());                                                                     //S4
	      trans.rotate(obj.getSpeed().getX(), -obj.getSpeed().getY());                                                           //S3
	      trans.translate(-w*(resolutionWidth/viewWidth)/2, -h*(resolutionHeight/viewHeight)/2);                                 //S2
	      trans.scale((w/sprite.getWidth(this))*(resolutionWidth/viewWidth), (h/sprite.getHeight(this))*(resolutionHeight/viewHeight));  //S1
	      //============================================================
	      
	      buffG.drawImage(sprite, trans, this);
	   }
	
	public void drawCrosshair(Graphics2D buffG) {
		int mouseX = this.getMousePosition().x;
		int mouseY = this.getMousePosition().y;
		Vector player_position = model.getPlayer().getPosition();
    	Vector view_cor = worldCorToViewCor(player_position);
    	
		int length = 15;
		if(model.getPlayer().isOnShoot())
			buffG.setColor(Color.RED);
		buffG.drawLine((int)view_cor.getX(), (int)view_cor.getY(), mouseX, mouseY);
		buffG.drawLine(mouseX, mouseY + length, mouseX, mouseY - length);
		buffG.drawLine(mouseX + length, mouseY, mouseX - length, mouseY);
		buffG.setColor(Color.BLACK);
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public double getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(double viewWidth) {
		this.viewWidth = viewWidth;
	}

	public double getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(double viewHeight) {
		this.viewHeight = viewHeight;
	}

	public int getResolutionWidth() {
		return resolutionWidth;
	}

	public void setResolutionWidth(int resolutionWidth) {
		this.resolutionWidth = resolutionWidth;
	}

	public int getResolutionHeight() {
		return resolutionHeight;
	}

	public void setResolutionHeight(int resolutionHeight) {
		this.resolutionHeight = resolutionHeight;
	}
}
