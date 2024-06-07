package my2dGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;

import drawable.Drawable;
import gui.*;
import physicalObject.*;
import physicalObject.bullet.Bullet;
import physicalObject.entity.Enemy;
import vector.Vector;

public class View extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Controller controller;
	Model model;

	private DashCountGUI dashCountGUI = new DashCountGUI(10, 100);
	private JetpackGaugeGUI jetpackGaugeGUI = new JetpackGaugeGUI(30, 120);
	private HPBarGUI hpBarGUI = new HPBarGUI(500, 50);
	private SpeedometerGUI speedometerGUI = new SpeedometerGUI(120, 30);
	private WeaponGUI weaponGUI = new WeaponGUI(150, 30);
	private DetailedHPGUI detailedHPGUI = new DetailedHPGUI(110, 30);
	private BossHPBarGUI bossHPBarGUI = new BossHPBarGUI(1000, 50);

	private Image buffImg;
	private Graphics2D buffG;

	private Vector viewPosition = new Vector(0, -100);
	private double viewWidth = 3840;
	private double viewHeight = 2160;

	private int resolutionWidth = 1600;
	private int resolutionHeight = 900;

	private int mouseX;
	private int mouseY;
	
	private Font font;
	
	public View(Model model) {
		// Settings for the panel.
		setSize(resolutionWidth, resolutionHeight); // Set the size of the panel.
		setVisible(true); // Show frame
		setFocusable(true);
		this.model = model;

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("res/img/cursor/crosshair.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(16, 16), "img");
		this.setCursor(c);

		this.font = loadFont("res/font/koverwatch.ttf");
	}

	@Override
	public void paintComponent(Graphics g) {
		buffImg = createImage(resolutionWidth, resolutionHeight); // Create resolutionWidth X resolutionHeight empty
		buffG = (Graphics2D) buffImg.getGraphics();
		buffG.clearRect(0, 0, resolutionWidth, resolutionHeight); // Clear image

		drawObject(model.getBackground());
		
		if (!model.getGameState().equals("GameOver"))
			drawObject(model.getPlayer());

		for (Enemy e : model.getEnemyList()) {
			drawObject(e);
		}

		// drawCrosshair();
		for (Obstacle m : model.getObstacleList()) {
			drawObject(m);
		}

		for (Drawable d : model.getAfterImageList()) {
			drawObject(d);
		}
		// ===== Ground ======
		// Vector start = worldCorToViewCor(new Vector(0, 0));
		// Vector end = worldCorToViewCor(new Vector(viewWidth, 0));
		// buffG.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(),
		// (int)end.getY());
		// ===================

		drawBullet();

		if (!model.getGameState().equals("GameOver")) {
			drawGUI(dashCountGUI, new Vector(resolutionWidth / 2 - 60, resolutionHeight / 2));
			drawGUI(jetpackGaugeGUI, new Vector(resolutionWidth / 2 + 57, resolutionHeight / 2));
		}
		drawGUI(hpBarGUI, new Vector(resolutionWidth / 2 + 40, resolutionHeight - 100));
		drawGUI(speedometerGUI, new Vector(resolutionWidth / 2 + 35, resolutionHeight - 170));
		drawGUI(weaponGUI, new Vector(resolutionWidth / 2 + 200, resolutionHeight - 145));
		drawGUI(detailedHPGUI, new Vector(resolutionWidth / 2 - 130, resolutionHeight - 145));
		
		if (model.getStageName().equals("bossStage"))
			drawGUI(bossHPBarGUI, new Vector(resolutionWidth / 2, 50));

		if (model.getGameState().equals("GameOver")) {
			buffG.setFont(font);
			buffG.setColor(Color.BLACK);
			buffG.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			buffG.fillRect(0, 0, resolutionWidth, resolutionHeight);
			buffG.setComposite(AlphaComposite.SrcOver);
			buffG.setColor(new Color(165, 49, 69));
			buffG.drawString("당신은 죽었습니다.", resolutionWidth / 2 - 250, resolutionHeight / 2 - 20);
			buffG.setColor(new Color(200, 200, 200));
			buffG.setFont(font.deriveFont(40.f));
			buffG.drawString("PRESS R TO RESTART", resolutionWidth / 2 - 250 + 100 + 40, resolutionHeight / 2 + 70 - 20);
			buffG.drawString("PRESS ESC TO EXIT", resolutionWidth / 2 - 250 + 180 - 30 + 5, resolutionHeight / 2 + 137 - 20);
		}

		// buffG.drawString("" + (int)(model.getPlayer().getAcceleration().size()/980 +
		// 1) + "G", mouseX, mouseY);

		g.drawImage(buffImg, 0, 0, this); // Move the image(buffImg) drawn in the buffer(buffG) to screen g.
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				// System.out.println("painted");
				// repaint();
				// Thread.sleep(16); // About 144 FPS
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector worldCorToViewCor(Vector worldCor) {
		Vector tmp = worldCor.sub(this.viewPosition);
		return new Vector(tmp.getX() * (resolutionWidth / viewWidth),
				resolutionHeight - tmp.getY() * (resolutionHeight / viewHeight));
	}

	public Vector viewCorToWorldCor(Vector viewCor) {
		double x = viewCor.getX() * (viewWidth / resolutionWidth);
		double y = (-viewCor.getY() + resolutionHeight) * (viewHeight / resolutionHeight);
		return this.viewPosition.add(new Vector(x, y));
	}

	public void drawBullet() {
		for (Bullet b : model.getPlayerBulletList()) {
			drawObject(b);
		}
		for (Bullet b : model.getEnemyBulletList()) {
			drawObject(b);
		}
		for (Bullet b : model.getBossMissileList()) {
			drawObject(b);
		}
	}

	public void drawObject(Drawable drawable) {
		Vector position = drawable.getPosition();
		Image sprite = drawable.getSprite();
		double w = drawable.getWidth();
		double h = drawable.getHeight();
		Vector view_cor = worldCorToViewCor(position);

		AffineTransform trans = new AffineTransform();
		// ====================== AffineTransform ======================
		trans.translate(view_cor.getX(), view_cor.getY()); // S3
		trans.translate(-w * (resolutionWidth / viewWidth) / 2, -h * (resolutionHeight / viewHeight) / 2); // S2
		if (drawable.getDirection() < 0)
			trans.translate(w * (resolutionWidth / viewWidth), 0);
		trans.scale(drawable.getDirection() * (w / sprite.getWidth(this)) * (resolutionWidth / viewWidth),
				(h / sprite.getHeight(this)) * (resolutionHeight / viewHeight)); // S1
		// ============================================================
		buffG.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, drawable.getAlpha()));
		buffG.drawImage(sprite, trans, this);
		buffG.setComposite(AlphaComposite.SrcOver);
	}

	public void drawObject(PhysicalObject obj) {
		Vector position = obj.getPosition();
		Image sprite = obj.getSprite();
		double w = obj.getWidth();
		double h = obj.getHeight();
		Vector view_cor = worldCorToViewCor(position);

		AffineTransform trans = new AffineTransform();
		// ====================== AffineTransform ======================
		trans.translate(view_cor.getX(), view_cor.getY()); // S4
		trans.rotate(obj.getAngle().getX(), -obj.getAngle().getY()); // S3
		trans.translate(-w * (resolutionWidth / viewWidth) / 2, -h * (resolutionHeight / viewHeight) / 2); // S2
		if (obj.getDirection() < 0)
			trans.translate(w * (resolutionWidth / viewWidth), 0);
		trans.scale(obj.getDirection() * (w / sprite.getWidth(this)) * (resolutionWidth / viewWidth),
				(h / sprite.getHeight(this)) * (resolutionHeight / viewHeight)); // S1
		// ============================================================
		buffG.drawImage(sprite, trans, this);

		boolean onHitbox = false;
		if (onHitbox) {
			Vector[] temp = obj.getCollider().getRotatedNormal();
			Vector x0 = worldCorToViewCor(position.sub(temp[0]).add(temp[1]));
			Vector x1 = worldCorToViewCor(position.add(temp[0]).add(temp[1]));
			Vector x2 = worldCorToViewCor(position.add(temp[0]).sub(temp[1]));
			Vector x3 = worldCorToViewCor(position.sub(temp[0]).sub(temp[1]));

			int[] x = { (int) x0.getX(), (int) x1.getX(), (int) x2.getX(), (int) x3.getX() };
			int[] y = { (int) x0.getY(), (int) x1.getY(), (int) x2.getY(), (int) x3.getY() };

			buffG.drawPolygon(x, y, 4);
		}
	}

	public void drawObject(Obstacle o) {
		Vector position = o.getPosition();
		double w = o.getWidth() * (resolutionWidth / viewWidth);
		double h = o.getHeight() * (resolutionHeight / viewHeight);
		Vector viewCor = worldCorToViewCor(position);
		Vector rectCor = viewCor.sub(new Vector(w / 2, h / 2));

		int x = 10;
		int y = 10;
		int z = 18;
		int r1 = 10;
		int r2 = 8;

		AffineTransform trans = new AffineTransform();
		// ====================== AffineTransform ======================
		trans.translate(viewCor.getX(), viewCor.getY()); // S4
		trans.rotate(o.getAngle().getX(), -o.getAngle().getY()); // S3
		trans.translate(-w / 2, -h / 2); // S2
		// ============================================================
		buffG.setTransform(trans);

		buffG.setColor(new Color(15, 22, 29));
		buffG.fillRect(0, 0, (int) w, (int) h);
		buffG.setColor(new Color(38, 49, 61));
		buffG.fillRect(x, x, (int) w - 2 * x, (int) h - 2 * x);
		buffG.setColor(new Color(32, 42, 53));
		buffG.fillRect(x + y, x + y, (int) w - 2 * (x + y), (int) h - 2 * (x + y));

		// Vector firstDotCor = rectCor.add(new Vector(x + y + z - r1, x + y + z - r1));
		Vector firstDotCor = new Vector(x + y + z - r1, x + y + z - r1);
		double dx = w - 2 * (x + y + z);
		double dy = h - 2 * (x + y + z);

		buffG.setColor(new Color(15, 22, 29));
		buffG.fillOval((int) firstDotCor.getX(), (int) firstDotCor.getY(), 2 * r1, 2 * r1);
		buffG.fillOval((int) (firstDotCor.getX() + dx), (int) firstDotCor.getY(), 2 * r1, 2 * r1);
		buffG.fillOval((int) firstDotCor.getX(), (int) (firstDotCor.getY() + dy), 2 * r1, 2 * r1);
		buffG.fillOval((int) (firstDotCor.getX() + dx), (int) (firstDotCor.getY() + dy), 2 * r1, 2 * r1);

		buffG.setColor(new Color(49, 65, 80));
		// buffG.setColor(new Color(71, 87, 104));
		buffG.fillOval((int) firstDotCor.getX(), (int) firstDotCor.getY(), 2 * r2, 2 * r2);
		buffG.fillOval((int) (firstDotCor.getX() + dx), (int) firstDotCor.getY(), 2 * r2, 2 * r2);
		buffG.fillOval((int) firstDotCor.getX(), (int) (firstDotCor.getY() + dy), 2 * r2, 2 * r2);
		buffG.fillOval((int) (firstDotCor.getX() + dx), (int) (firstDotCor.getY() + dy), 2 * r2, 2 * r2);

		buffG.setTransform(new AffineTransform());
	}

	public void drawCrosshair() {
		int mouseX = this.getMouseX();
		int mouseY = this.getMouseY();

		Vector player_position = model.getPlayer().getPosition();
		Vector view_cor = worldCorToViewCor(player_position);

		int length = 15;
		if (model.getPlayer().isOnShoot())
			buffG.setColor(Color.RED);
		buffG.drawLine((int) view_cor.getX(), (int) view_cor.getY(), mouseX, mouseY);
		buffG.drawLine(mouseX, mouseY + length, mouseX, mouseY - length);
		buffG.drawLine(mouseX + length, mouseY, mouseX - length, mouseY);
		buffG.setColor(Color.BLACK);
	}

	public void drawGUI(GUI gui, Vector viewCor) {
		viewCor = viewCor.sub(new Vector(gui.getGuiWidth() / 2, gui.getGuiHeight() / 2));
		gui.updateUiImage(this.model);
		buffG.drawImage(gui.getUiImage(), (int) viewCor.getX(), (int) viewCor.getY(), this);
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

	public void setViewPosition(Vector position) {
		this.viewPosition = position;
	}

	public Vector getViewPosition() {
		return this.viewPosition;
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

	public DashCountGUI getDashCountGUI() {
		return dashCountGUI;
	}

	public void setDashCountGUI(DashCountGUI dashCountGUI) {
		this.dashCountGUI = dashCountGUI;
	}

	public JetpackGaugeGUI getJetpackGaugeGUI() {
		return jetpackGaugeGUI;
	}

	public void setJetpackGaugeGUI(JetpackGaugeGUI jetpackGaugeGUI) {
		this.jetpackGaugeGUI = jetpackGaugeGUI;
	}

	public int getMouseX() {
		try {
			this.mouseX = this.getMousePosition().x;
		} catch (NullPointerException e) {
		}
		return this.mouseX;
	}

	public int getMouseY() {
		try {
			this.mouseY = this.getMousePosition().y;
		} catch (NullPointerException e) {
		}
		return this.mouseY;
	}
}