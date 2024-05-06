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
	private int width = 1280;
	private int height = 720;
	
    public View(Model model){
        // 프레임의 대한 설정.
        setTitle("my2dGame"); // 프레임 제목 설정.
        setSize(width, height); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        this.model = model;
    }
    
    @Override
    public void paint(Graphics g) {
    	buffImg = createImage(width, height);
    	buffG = buffImg.getGraphics();
    	update(g);
    }
    
    @Override
    public void update(Graphics g) {
    	buffG.clearRect(0, 0, width, height); // 백지화
    	drawPlayer(buffG);
    	drawCrosshair(buffG);
    	Vector start = worldCorToViewCor(new Vector(0, 0));
    	Vector end = worldCorToViewCor(new Vector(width, 0));
    	buffG.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
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
		return new Vector(worldCor.getX() - this.position.getX(), this.height + this.position.getY() - worldCor.getY());
	}
	
	public Vector viewCorToWorldCor(Vector viewCor) {
		Vector v = new Vector(viewCor.getX(), this.height - viewCor.getY());
		return this.position.add(v);
	}
	
	public void drawPlayer(Graphics buffG) {
		int mouseX = this.getMousePosition().x;
		int mouseY = this.getMousePosition().y;
		Vector mouse_position = viewCorToWorldCor(new Vector(mouseX, mouseY));
		
    	Vector player_position = model.getPlayer().getPosition();
    	Vector view_cor = worldCorToViewCor(player_position);
    	Image sprite = model.getPlayer().getSptite();
    	int w = sprite.getWidth(rootPane);
    	int h = sprite.getHeight(rootPane);
    	if(player_position.sub(mouse_position).getX() < 0)
    		w = -w;
        buffG.drawImage(sprite, (int)view_cor.getX() - w/2 , (int)view_cor.getY() - h/2 , w, h, this); // 유저 비행기 그리기.
    	buffG.drawOval((int)view_cor.getX()-5, (int)view_cor.getY()-5, 10, 10);
	}
	
	public void drawCrosshair(Graphics buffG) {
		int mouseX = this.getMousePosition().x;
		int mouseY = this.getMousePosition().y;
		Vector player_position = model.getPlayer().getPosition();
    	Vector view_cor = worldCorToViewCor(player_position);
    	
		int length = 15;
		if(model.getPlayer().getOnShoot())
			buffG.setColor(Color.RED);
		buffG.drawLine((int)view_cor.getX(), (int)view_cor.getY(), mouseX, mouseY);
		buffG.drawLine(mouseX, mouseY + length, mouseX, mouseY - length);
		buffG.drawLine(mouseX + length, mouseY, mouseX - length, mouseY);
		buffG.setColor(Color.BLACK);
	}
}
