package my2dGame;

import java.awt.event.*;
import java.util.Iterator;

import collision.Collision;
import physicalObject.*;
import vector.Vector;

public class Controller implements KeyListener, MouseListener, Runnable{
	
	private Model model; // Controller contains model, view
	private View view;
	
	private int fps = 144;
	private double dt = 1.0/fps;
	
	private double g = 400.0;
	
	private boolean leftMouseClick = false;
	private boolean rightMouseClick = false;
	private boolean spaceBar = false;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.addMouseListener(this);
        view.addKeyListener(this);
        /*
        Thread view_thread = new Thread(view);
        view_thread.setDaemon(true);
        view_thread.start();
        */
		
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
				move(model.getEnemy());
				for(Bullet b : model.getBulletList()) {
					move(b);
				}
				for(Bullet b : model.getEnemyBulletList()) {
					move(b);
				}
			
				model.getMonster().setDirection(model.getMonster().getDirection().add(model.getMonster().getDirection().normal().unit().mul(0.3*dt)));
				
				view.setViewPosition(model.getPlayer().getPosition().sub(new Vector(view.getViewWidth()/2, view.getViewHeight()/2))); // Let the View tracks Player
				//System.out.println((int)(1000*dt));
				
				for(Bullet b: model.getBulletList()) {
					if(Collision.collisionTest(b.getCollider(), model.getEnemy().getCollider()).isCollision()) {
						model.getEnemy().setHP(model.getEnemy().getHP() - 10);
						System.out.println("Enemy's HP :" + model.getEnemy().getHP());
						model.getBulletList().remove(b);
						continue;
					}
					if(Collision.collisionTest(b.getCollider(), model.getMonster().getCollider()).isCollision())
						model.getBulletList().remove(b);
				}
				for(Bullet b : model.getEnemyBulletList()) {
					System.out.println(""+ model.getEnemyBulletList().size());
					if(Collision.collisionTest(b.getCollider(), model.getPlayer().getCollider()).isCollision()) {
						model.getPlayer().setHP(model.getPlayer().getHP() - 10);
						System.out.println("Player's HP :" + model.getPlayer().getHP());
						model.getEnemyBulletList().remove(b);
						continue;
					}
					if(Collision.collisionTest(b.getCollider(), model.getMonster().getCollider()).isCollision())
						model.getEnemyBulletList().remove(b);
				}
				Collision coll = Collision.collisionTest(model.getPlayer().getCollider(), model.getMonster().getCollider());
				//System.out.println("" + coll.isCollision() + " " + model.getPlayer().getSpeed());
				if(coll.isCollision()) {
					double speedLossRate = 0.9;
					model.getPlayer().addPosition(coll.getMinimumTranslationVector());
					//System.out.println("" + coll.getMinimumTranslationVector());
					Vector axis = coll.getMinimumTranslationVector().unit();
					if(model.getPlayer().getSpeed().dot(axis) < 0) {
						model.getPlayer().addSpeed(axis.mul( -(2-speedLossRate) * model.getPlayer().getSpeed().dot(axis)));
					}
				}
				Collision coll2 = Collision.collisionTest(model.getPlayer().getCollider(), model.getEnemy().getCollider());
				if(coll2.isCollision()) {
					double speedLossRate = 0.9;
					model.getPlayer().addPosition(coll2.getMinimumTranslationVector());
					Vector axis = coll2.getMinimumTranslationVector().unit();
					if(model.getPlayer().getSpeed().dot(axis) < 0)
						model.getPlayer().addSpeed(axis.mul( -(2-speedLossRate) * model.getPlayer().getSpeed().dot(axis)));
				}
				Collision coll3 = Collision.collisionTest(model.getEnemy().getCollider(), model.getMonster().getCollider());
				if(coll3.isCollision()) {
					double speedLossRate = 0.9;
					model.getEnemy().addPosition(coll3.getMinimumTranslationVector());
					Vector axis = coll3.getMinimumTranslationVector().unit();
					if(model.getEnemy().getSpeed().dot(axis) < 0)
						model.getEnemy().addSpeed(axis.mul( -(2-speedLossRate) * model.getEnemy().getSpeed().dot(axis)));
				}
				view.repaint();
				Thread.sleep((int)(1000*dt));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == false) { // If space pressed -> dash 
			dash();
			spaceBar = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == true) { // If space released
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

	public void move(PhysicalObject obj) {
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
			model.getPlayer().setJetpackGauge(model.getPlayer().getJetpackGauge() - dt*model.getPlayer().getJetpackGaugeUsagePerSec());
			if(model.getPlayer().getJetpackGauge() < 0)
				model.getPlayer().setJetpackGauge(0);
			if(model.getPlayer().getJetpackGauge() > 0) {
				int mouseX = view.getMouseX();
				int mouseY = view.getMouseY();
				Vector viewCor = new Vector(mouseX, mouseY);
				Vector worldCor = view.viewCorToWorldCor(viewCor);
				model.getPlayer().addAcceleration(new Vector(0, g));
				model.getPlayer().addAcceleration(worldCor.sub(model.getPlayer().getPosition()).unit().mul(1550));
			}
		}
		if(!leftMouseClick) {
			if(0 <= model.getPlayer().getJetpackGauge() && model.getPlayer().getJetpackGauge() < model.getPlayer().getMaxJetpackGauge())
				model.getPlayer().setJetpackGauge(model.getPlayer().getJetpackGauge() + dt*model.getPlayer().getJetpackGaugeUsagePerSec()); //JetpackGauge Charging
				if(model.getPlayer().getJetpackGauge() > model.getPlayer().getMaxJetpackGauge())
					model.getPlayer().setJetpackGauge(model.getPlayer().getMaxJetpackGauge());
		}
	}
	
	public void shoot() {
		model.getPlayer().setOnShoot(true);
		int mouseX = view.getMouseX();
		int mouseY = view.getMouseY();
		//---------Convert mouse's vector to world vector then sub Player's vector-----------
		Vector viewCor = new Vector(mouseX, mouseY);
		Vector worldCor = view.viewCorToWorldCor(viewCor);
		Vector dv = worldCor.sub(model.getPlayer().getPosition()).unit();
		//-----------------------------------------------------------------------------------
		model.getBulletList().add(new Bullet(model.getPlayer().getPosition(), dv.mul(2000)));
		model.getPlayer().addSpeed(dv.mul(-300)); // Rebound
	}
	
	public void dash() {
		// Dash count charging mechanism and exception handling
		if(model.getPlayer().getDashCount() > 0) {
			int prevDashCount = model.getPlayer().getDashCount();
			model.getPlayer().setDashCount(model.getPlayer().getDashCount() - 1);
			
			// Only First
	        if(prevDashCount == model.getPlayer().getMaxDashCount()) {
	        	view.getDashCountGUI().setAlphaControlCount(view.getDashCountGUI().getAlphaControlCount() + 1);
	        	int alphaControlNumber = view.getDashCountGUI().getAlphaControlCount();
	        	new Thread() {
	        		public void run() {
	        			try {
		                    while(model.getPlayer().getDashCount() < model.getPlayer().getMaxDashCount()) {
		                    	Thread.sleep(1000*model.getPlayer().getDashRechargingSec());
		                        model.getPlayer().setDashCount(model.getPlayer().getDashCount() + 1);      
		                    }
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	               }
	        	}.start();
	        
		        new Thread() {
		        	public void run() {
		        		try {
			        		while(true) {
			        			view.getDashCountGUI().setAlpha(view.getDashCountGUI().getAlpha() + 0.05f);
								if(view.getDashCountGUI().getAlpha() > view.getDashCountGUI().getMaxAlpha()) {
									view.getDashCountGUI().setAlpha(view.getDashCountGUI().getMaxAlpha());
									if(model.getPlayer().getDashCount() == model.getPlayer().getMaxDashCount()) {
										break;
									}
								}
								Thread.sleep(100);
			        		}
			        		Thread.sleep(3000);
			        		while(view.getDashCountGUI().getAlphaControlCount() == alphaControlNumber) {
			        			view.getDashCountGUI().setAlpha(view.getDashCountGUI().getAlpha() - 0.05f);
								if(view.getDashCountGUI().getAlpha() < view.getDashCountGUI().getMinAlpha()) {
									view.getDashCountGUI().setAlpha(view.getDashCountGUI().getMinAlpha());
									break;
								}
								Thread.sleep(100);
			        		}
		        		} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		        }.start();
	        }
	        int mouseX = view.getMouseX();
			int mouseY = view.getMouseY();
			Vector mouseViewCor = new Vector(mouseX, mouseY);
			Vector mouseWorldCor = view.viewCorToWorldCor(mouseViewCor);
			
			// Legacy dash mechanism
			Vector ds = mouseWorldCor.sub(model.getPlayer().getPosition()).unit();
			new Thread() {
				public void run() {
					try {
						for(int i=0; i<5; i++) {
							model.getPlayer().addPosition(ds.mul(50));
							Thread.sleep(25);	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			model.getPlayer().setSpeed(ds.mul(500));
		}
	}
}