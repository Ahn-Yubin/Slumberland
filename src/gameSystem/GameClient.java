package gameSystem;

import java.awt.Color;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

@SuppressWarnings("serial")
public class GameClient extends JFrame {
	private Font font;
	private BackGround background;

	public GameClient() {
		setTitle("Slumberland"); // Set frame title.
		setSize(1280, 720); // Set the size of the frame.
		setResizable(false); // Set the frame size to not change.
		setVisible(true); // Show frame
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
		setLocation((1920 - this.getWidth()) /2, (1080 - this.getHeight()) / 2);

		this.font = loadFont("res/font/koverwatch.ttf");

		background = new BackGround();
		background.stringFontSetting(font);
		this.add(background);

		MenuButton startButton = new MenuButton(1225 - 280, 200, 280, 100, "게임 시작", font, 80.f, 90.f, Color.DARK_GRAY,
				Color.BLACK);
		startButton.addActionListener(new StartButtonClickListener());
		background.add(startButton);

		MenuButton howToPlayButton = new MenuButton(1225 - 280, 350, 280, 100, "게임 방법", font, 80.f, 90.f,
				Color.DARK_GRAY, Color.BLACK);
		howToPlayButton.addActionListener(new HowToPlayButtonClickListener());
		background.add(howToPlayButton);

		MenuButton quitButton = new MenuButton(1225 - 100, 500, 100, 80, "종료", font, 60.f, 70.f, new Color(165, 49, 69),
				Color.DARK_GRAY);
		quitButton.addActionListener(new QuitButtonClickListener());
		background.add(quitButton);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("res/img/cursor/pointer.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
		this.setCursor(c);
	}

	public Font loadFont(String fontFileLink) {
		InputStream in;
		Font font = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fontFileLink));
			font = Font.createFont(Font.TRUETYPE_FONT, in);
			font = font.deriveFont(100f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return font;
	}

	private class StartButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			dispose();
			Model model = new Model(); // View - Model - Controller Structure
			View view = new View(model);
			Controller controller = new Controller(model, view);
			new Thread(controller).start();
			@SuppressWarnings("unused")
			GameFrame gameFrame = new GameFrame(view);
		}
	}

	private class HowToPlayButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			background.setOnHowToPlay(!background.isOnHowToPlay());
		}

	}

	private class QuitButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

	}
}

@SuppressWarnings("serial")
class MenuButton extends JButton implements MouseListener {
	private float basicFontSize;
	private float activatedFontSize;

	private Font font;

	private boolean isMouseEntered = false;

	private String text;
	private Color fontColor;
	private Color borderColor;

	public MenuButton(int x, int y, int width, int height, String text, Font font, float basicFontSize,
			float activatedFontSize, Color fontColor, Color borderColor) {
		this.setLocation(x, y);
		this.setSize(width, height);
		this.text = text;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.font = font;
		this.basicFontSize = basicFontSize;
		this.activatedFontSize = activatedFontSize;
		this.fontColor = fontColor;
		this.borderColor = borderColor;

		this.setFont(this.font.deriveFont(Font.PLAIN, basicFontSize));
		this.addMouseListener(this);
		new MyThread().start();
	}

	public void paintComponent(Graphics g) {
		float fontSize = isMouseEntered ? activatedFontSize : basicFontSize;
		g.setFont(font.deriveFont(fontSize));

		int spacebarCount = text.length() - text.replace(String.valueOf(" "), "").length();
		int normalCharCount = text.length() - spacebarCount;

		double totalWidth = (spacebarCount * 0.2 + normalCharCount * 0.7) * fontSize;

		g.setColor(this.borderColor);
		g.drawString(this.text, (int) (this.getWidth() - totalWidth + 2), (int) (fontSize - 10 + 2));

		g.setColor(this.fontColor);
		g.drawString(this.text, (int) (this.getWidth() - totalWidth), (int) (fontSize - 10));

	}

	class MyThread extends Thread {
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					repaint();
					Thread.sleep(6);

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseEntered = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseEntered = false;
	}
}

@SuppressWarnings("serial")
class BackGround extends JPanel implements MouseListener {
	private BufferedImage buffImg;
	private Graphics2D buffG;

	private Image background;
	private int backgroundWidth = 1727;

	private double dt = 1.0 / 144.0;

	private double x;
	private double speed;

	private Image howToPlayImg;
	private int howToPlayImgWidth = 440;
	private int howToPlayImgHeight = 600;

	private boolean isOnHowToPlay = false;

	public BackGround() {
		this.x = 0;
		this.speed = 200; // Pixel per second

		setSize(1280, 720);
		setFocusable(true);
		setVisible(true);

		buffImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		buffG = (Graphics2D) buffImg.getGraphics();
		buffG.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		try {
			this.background = ImageIO.read(new File("res/img/drawable/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.backgroundWidth = this.background.getWidth(this);

		try {
			this.howToPlayImg = ImageIO.read(new File("res/img/client/howToPlay.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addMouseListener(this);

		(new MyThread()).start();
	}

	class MyThread extends Thread {
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {
					Thread.sleep((int) (1000 * dt));
					x -= speed * dt;
					if (x < -backgroundWidth)
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
		buffG.drawImage(background, (int) x, 0, this);
		buffG.drawImage(background, (int) (x + background.getWidth(this)), 0, this);
		buffG.setColor(new Color(100, 100, 100));
		buffG.drawString("slumberland", 58, 128);
		buffG.setColor(Color.WHITE);
		buffG.drawString("slumberland", 55, 125);
		if (isOnHowToPlay)
			buffG.drawImage(howToPlayImg, (this.getWidth() - howToPlayImgWidth) / 2, (this.getHeight() - howToPlayImgHeight) / 2, 
					howToPlayImgWidth, howToPlayImgHeight, null);
		g.drawImage(buffImg, 0, 0, this);
	}

	public void stringFontSetting(Font font) {
		AffineTransform trans = new AffineTransform();
		trans.shear(-0.5, 0);
		buffG.setFont(font.deriveFont(trans));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1 && isOnHowToPlay) {
			int clickBoundary = 33;

			int centerX = this.getWidth() / 2;
			int centerY = this.getHeight() / 2;

			int firstX = centerX + howToPlayImgWidth / 2 - clickBoundary;
			int firstY = centerY - howToPlayImgHeight / 2;

			int secondX = centerX + howToPlayImgWidth / 2;
			int secondY = centerY - howToPlayImgHeight / 2 + clickBoundary;

			if (firstX <= e.getX() && e.getX() <= secondX && firstY <= e.getY() && e.getY() <= secondY) {
				setOnHowToPlay(false);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isOnHowToPlay() {
		return isOnHowToPlay;
	}

	public void setOnHowToPlay(boolean isOnHowToPlay) {
		this.isOnHowToPlay = isOnHowToPlay;
	}
}