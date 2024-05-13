package my2dGame;

import java.awt.event.*;

import vector.Vector;
import visiableObject.Bullet;
import visiableObject.VisiableObject;

public class Controller implements KeyListener, MouseListener, Runnable{
	
	private Model model; // Controller contains model, view
	private View view;
	
	private int fps = 144;
	private double dt = 1.0/fps;
	
	private double g = 200.0;
	
	private boolean leftMouseClick = false;
	private boolean rightMouseClick = false;
	private boolean spaceBar = false;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.addMouseListener(this);
        view.addKeyListener(this);
        
        Thread view_thread = new Thread(view);
        view_thread.setDaemon(true);
        view_thread.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == false) { // if space pressed -> dash 
			dash();
			spaceBar = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == true) { // if space released
			spaceBar = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1)
			leftMouseClick = false;
		if(e.getButton() == MouseEvent.BUTTON3) {
			rightMouseClick = false;
			model.getPlayer().setOnShoot(false);
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1)
			leftMouseClick = true;
		if(e.getButton() == MouseEvent.BUTTON3) {
			rightMouseClick = true;
			shoot();
		}
	}

	@Override
	public void run() { // Controller's Thread
		// TODO Auto-generated method stub
		try {
			while(true) {
				model.getPlayer().setAcceleration(new Vector()); // Set Player's acceleration (0, 0)
				jetpack();
				gravity();
				dragForce();
				move(model.getPlayer());
				for(Bullet b : model.getBulletList()) {
					move(b);
				}
				view.setPosition(model.getPlayer().getPosition().sub(new Vector(view.getViewWidth()/2, view.getViewHeight()/2))); // Let the View tracks Player
				System.out.println(model.getPlayer()); 
				//System.out.println((int)(1000*dt));
				Thread.sleep((int)(1000*dt));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move(VisiableObject obj) {
		obj.setPosition(obj.getPosition().add(obj.getSpeed().mul(dt)));
		obj.setSpeed(obj.getSpeed().add(obj.getAcceleration().mul(dt)));
	}
	
	public void gravity() {
		if (model.getPlayer().getPosition().getY() >= 0) {
			//System.out.println("gravity");
			model.getPlayer().addAcceleration(new Vector(0, -g));
		}
		else {
			model.getPlayer().getPosition().setY(0);
			model.getPlayer().getSpeed().setY(0);
			//model.getPlayer().getAcceleration().setY(0);
		}
	}
	
	public void dragForce() {
		double c = 0.001;
		model.getPlayer().addAcceleration(model.getPlayer().getSpeed().mul(-c * model.getPlayer().getSpeed().size()));
	}
	
	public void jetpack() { 
		// Depending on whether leftMouse is pressed or not It determines the consumption and charging of the JetpackGauge 
		// and handles exceptions to ensure that it does not deviate from the specified value and within the specified range.
		if (leftMouseClick) {
			model.getPlayer().setJetpackGauge(model.getPlayer().getJetpackGauge() - 0.1);
			if(model.getPlayer().getJetpackGauge() < 0)
				model.getPlayer().setJetpackGauge(0);
			if(model.getPlayer().getJetpackGauge() > 0) {
				int mouseX = view.getMousePosition().x;
				int mouseY = view.getMousePosition().y;
				Vector viewCor = new Vector(mouseX, mouseY);
				Vector worldCor = view.viewCorToWorldCor(viewCor);
				model.getPlayer().addAcceleration(new Vector(0, g));
				model.getPlayer().addAcceleration(worldCor.sub(model.getPlayer().getPosition()).unit().mul(1550));
			}
		}
		if(!leftMouseClick) {
			if(0 <= model.getPlayer().getJetpackGauge() && model.getPlayer().getJetpackGauge() < model.getPlayer().getMaxJetpackGauge())
				model.getPlayer().setJetpackGauge(model.getPlayer().getJetpackGauge() + 0.1);
				if(model.getPlayer().getJetpackGauge() > model.getPlayer().getMaxJetpackGauge())
					model.getPlayer().setJetpackGauge(model.getPlayer().getMaxJetpackGauge());
		}
	}
	
	public void shoot() {
		model.getPlayer().setOnShoot(true);
		int mouseX = view.getMousePosition().x;
		int mouseY = view.getMousePosition().y;
		//---------Convert mouse's vector to world vector then sub Player's vector-----------
		Vector viewCor = new Vector(mouseX, mouseY);
		Vector worldCor = view.viewCorToWorldCor(viewCor);
		Vector dv = worldCor.sub(model.getPlayer().getPosition()).unit();
		//-----------------------------------------------------------------------------------
		model.getPlayer().addSpeed(dv.mul(-300)); // Rebound
		model.getBulletList().add(new Bullet(model.getPlayer().getPosition(), dv.mul(1000)));
	}
	
	public void dash() {
		// Dash count charging mechanism and exception handling
		if(model.getPlayer().getDashCount() > 0) {
			if(model.getPlayer().getDashCount() == model.getPlayer().getMaxDashCount()) {
				new Thread() {
					public void run() {
						try {
							while(model.getPlayer().getDashCount() < model.getPlayer().getMaxDashCount()) {
								Thread.sleep(1000);
								model.getPlayer().setDashCount(model.getPlayer().getDashCount() + 1);		
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			model.getPlayer().setDashCount(model.getPlayer().getDashCount() -1);
			int mouseX = view.getMousePosition().x;
			int mouseY = view.getMousePosition().y;
			Vector viewCor = new Vector(mouseX, mouseY);
			Vector worldCor = view.viewCorToWorldCor(viewCor);
			model.getPlayer().addPosition(worldCor.sub(model.getPlayer().getPosition()).unit().mul(50));
			model.getPlayer().setSpeed(worldCor.sub(model.getPlayer().getPosition()).unit().mul(50));
		}
	}
}