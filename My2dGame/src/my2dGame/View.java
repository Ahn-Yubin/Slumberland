package my2dGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class View extends JFrame implements Runnable{
	Controller controller;
	Model model;
	
	private Image buffImg;
    private Graphics buffG;
	
	private Vector position = new Vector(0, -100);
	private double width = 1920;
	private double height = 1080;
	
	private int resolutionWidth = 1600;
	private int resolutionHeight = 900;
	
    public View(Model model){
        // 프레임의 대한 설정.
        setTitle("my2dGame"); // 프레임 제목 설정.
        setSize(resolutionWidth, resolutionHeight); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        this.model = model;
    }
    
    @Override
    public void paint(Graphics g) {
    	buffImg = createImage(resolutionWidth, resolutionHeight);
    	buffG = buffImg.getGraphics();
    	update(g);
    }
    
    @Override
    public void update(Graphics g) {
    	buffG.clearRect(0, 0, resolutionWidth, resolutionHeight); // 백지화
    	drawObject(buffG, model.getPlayer());
    	
    	drawCrosshair(buffG);
    	
    	buffG.drawString(Double.toString(model.getPlayer().getJetpackGauge()), 200, 200);
    	Vector start = worldCorToViewCor(new Vector(0, 0));
    	Vector end = worldCorToViewCor(new Vector(width, 0));
    	buffG.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
    	buffG.drawString(Integer.toString(model.getPlayer().getDashCount()), 100, 100);
    	
        g.drawImage(buffImg, 0, 0, this); // 화면 g 에 버퍼(buffG)에 그려진 이미지(buffImg)옮김.
        repaint();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				//System.out.println("painted");
				repaint();
				Thread.sleep(6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public Vector worldCorToViewCor(Vector worldCor) {
		Vector tmp = worldCor.sub(this.position);
		return new Vector(tmp.getX() * (resolutionWidth/width), resolutionHeight - tmp.getY() * (resolutionHeight/height));
	}
	
	public Vector viewCorToWorldCor(Vector viewCor) {
		double x = viewCor.getX() * (width/resolutionWidth);
		double y = (-viewCor.getY() + resolutionHeight) * (height/resolutionHeight);
		return this.position.add(new Vector(x, y));
	}
	
	public void drawPlayer(Graphics buffG) {
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
	
	public void drawObject(Graphics buffG, VisiableObject obj) {
		Vector position = obj.getPosition();
		Image sprite = obj.getSprite(); 
		double w = obj.getWidth();
		double h = obj.getHeight();
		Vector view_cor = worldCorToViewCor(position.sub(new Vector(w/2, -h/2)));
		System.out.println(view_cor);
		buffG.drawImage(sprite, (int)view_cor.getX(), (int)view_cor.getY(), (int) (w*(resolutionWidth/width)), (int)(h*(resolutionHeight/height)), this);
		buffG.drawOval((int)worldCorToViewCor(position).getX()-5, (int)worldCorToViewCor(position).getY()-5, 10, 10);
	}
	
	public void drawCrosshair(Graphics buffG) {
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
}