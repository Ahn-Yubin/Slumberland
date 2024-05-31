package my2dGame;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameClient extends JFrame{
	private boolean running = true;

	public GameClient() {
		setTitle("my2dGame"); // Set frame title.
		setSize(1280, 720); // Set the size of the frame.
		setResizable(false); // Set the frame size to not change.
		//setUndecorated(true);
		setVisible(true); // Show frame
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
		
		BackGround background = new BackGround();
		this.add(background);
		
		JButton startButton = new JButton();
		startButton.addActionListener(new ButtonClickListener());
		startButton.setSize(100, 100);
		startButton.setLocation(100, 100);
		startButton.setText("Game Start");
		background.add(startButton);
		
		System.out.println(this.getX() + " " + this.getY());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("res/img/player.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(0, 0), "img");
		this.setCursor (c);
	}

	public boolean getRunning() {
		return running;
	}

	private class ButtonClickListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			running = false;
			dispose();
			Model model = new Model(); // View - Model - Controller Structure
			View view = new View(model);
			Controller controller = new Controller(model, view);
			new Thread(controller).start();
			GameFrame gameFrame = new GameFrame(view);
		}
		
	}
}

class BackGround extends JPanel{
	private BufferedImage buffImg;
	private Graphics2D buffG;
	
	private Image background;
	private int backgroundWidth = 1727;
	
	private double dt = 1.0/144.0;
	
	private double x;
	private double speed;

	public BackGround() {
		this.x = 0;
		this.speed = 200; // Pixel per second
		
		setSize(1280, 720);
		setVisible(true);
		
		buffImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		buffG = (Graphics2D) buffImg.getGraphics();
		setFont();
		
		try {
			this.background = ImageIO.read(new File("res/img/map2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.backgroundWidth = this.background.getWidth(this);
		
		(new MyThread()).start();
	}
	
	class MyThread extends Thread{
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					Thread.sleep((int)(1000 * dt));
					x -= speed * dt;
					if(x < -backgroundWidth)
						x += backgroundWidth;
					repaint();

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		buffG.clearRect(0, 0, this.getWidth(), this.getHeight());
		buffG.drawImage(background, (int)x, 0, this);
		buffG.drawImage(background, (int)(x+background.getWidth(this)), 0, this);
		buffG.drawString("slumberland", 100, 100);
		buffG.drawRect(100, 100, 5, 5);
		
		g.drawImage(buffImg, 0, 0, this);
	}
	
	public void setFont() {
		InputStream in;
		Font font = null;
		try {
			in = new BufferedInputStream(new FileInputStream("res/font/koverwatch.ttf"));
			font = Font.createFont(Font.TRUETYPE_FONT, in);
			font = font.deriveFont(100f);
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
		this.buffG.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		this.buffG.setFont(font);
	}
}


