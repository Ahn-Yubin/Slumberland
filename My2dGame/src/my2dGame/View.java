package my2dGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.*;
import physicalObject.*;
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
	private SpeedometerGUI speedometerGUI = new SpeedometerGUI(250, 100);
	
	private Image buffImg;
	private Graphics2D buffG;

	private Vector viewPosition = new Vector(0, -100);
	private double viewWidth = 3200;
	private double viewHeight = 1800;

	private int resolutionWidth = 1600;
	private int resolutionHeight = 900;

	private int mouseX;
	private int mouseY;

	public View(Model model) {

		// Settings for the frame.
		// setTitle("my2dGame"); // Set frame title.
		setSize(resolutionWidth, resolutionHeight); // Set the size of the frame.
		// setResizable(false); // Set the frame size to not change.
		setVisible(true); // Show frame
		setFocusable(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame
		// to end
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		buffImg = createImage(resolutionWidth, resolutionHeight); // Create resolutionWidth X resolutionHeight empty
		
		buffG = (Graphics2D) buffImg.getGraphics();
		buffG.clearRect(0, 0, resolutionWidth, resolutionHeight); // Clear image
		drawObject(model.getMap());
		drawObject(model.getPlayer());

		for (Enemy e : model.getEnemyList()) {
			drawObject(e);
		}

		drawCrosshair();
		for (Obstacle m : model.getObstacleList()) {
			drawObject(m);
		}
		// ===== Ground ======
		// Vector start = worldCorToViewCor(new Vector(0, 0));
		// Vector end = worldCorToViewCor(new Vector(viewWidth, 0));
		// buffG.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(),
		// (int)end.getY());
		// ===================

		drawBullet();

		drawGUI(dashCountGUI, new Vector(resolutionWidth / 2 - 60, resolutionHeight / 2));
		drawGUI(jetpackGaugeGUI, new Vector(resolutionWidth / 2 + 57, resolutionHeight / 2));
		drawGUI(hpBarGUI, new Vector(resolutionWidth / 2 + 40, resolutionHeight - 100));
		drawGUI(speedometerGUI, new Vector(resolutionWidth/2 -300, resolutionHeight - 100));
		
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
		trans.rotate(obj.getDirection().getX(), -obj.getDirection().getY()); // S3
		trans.translate(-w * (resolutionWidth / viewWidth) / 2, -h * (resolutionHeight / viewHeight) / 2); // S2
		trans.scale((w / sprite.getWidth(this)) * (resolutionWidth / viewWidth),
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
		double h = o.getHeight()* (resolutionHeight / viewHeight);
		Vector view_cor = worldCorToViewCor(position);
		Vector rect_cor = view_cor.sub(new Vector(w/2, h/2));
		
		int x = 10;
		int y = 10;
		
		buffG.setColor(new Color(15, 22, 29));
		buffG.fillRect((int)rect_cor.getX(), (int)rect_cor.getY(), (int)w, (int)h);
		buffG.setColor(new Color(38, 49, 61));
		buffG.fillRect((int)rect_cor.getX() + x, (int)rect_cor.getY()+ x, (int)w - 2*x, (int)h - 2*x);
		buffG.setColor(new Color(32, 42, 53));
		buffG.fillRect((int)rect_cor.getX() + x + y, (int)rect_cor.getY()+ x + y, (int)w - 2*(x+y), (int)h - 2*(x+y));
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