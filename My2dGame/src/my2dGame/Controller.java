package my2dGame;

import java.awt.event.*;

import collision.Collision;
import drawable.Drawable;
import physicalObject.*;
import physicalObject.bullet.BossMissile;
import physicalObject.bullet.EnemyBullet;
import physicalObject.bullet.PlayerBullet;
import physicalObject.entity.Enemy;
import physicalObject.entity.Entity;
import physicalObject.entity.Player;
import vector.Vector;

public class Controller implements KeyListener, MouseListener, Runnable{

	private Model model; // Controller contains model, view
	private View view;

	private int fps = 144;
	private double dt = 1.0/fps;

	private double g = 980.0;

	private boolean leftMouseClick = false;
	private boolean rightMouseClick = false;
	private boolean spaceBar = false;

	public Controller(Model model, View view) {
		this.model = model;
		this.model.initializeFirstStage();
		this.view = view;

		view.addMouseListener(this);
		view.addKeyListener(this);

		/*
        Thread view_thread = new Thread(view);
        view_thread.setDaemon(true);
        view_thread.start();
		 */

		//model.getMap().setPosition(model.getInitialPlayerPosition().add(new Vector(model.getMap().getWidth(), model.getMap().getHeight())));
	}

	@Override
	public void run() { // Controller's Thread
		// TODO Auto-generated method stub
		try {
			while(true) {
				applyNetForce(model.getPlayer());
				jetpack();
				move(model.getPlayer());

				System.out.println(model.getPlayer().getDirection());
				playerDirectionControll();

				//model.getMap().setPosition(model.getPlayer().getPosition());
				model.getBackground().setPosition(model.getBackgroundInitialPosition().add(model.getPlayer().getPosition().sub(model.getPlayerInitialPosition()).mul(0.8)));

				for(Enemy e : model.getEnemyList()) {
					applyNetForce(e);
					move(e);
					collisionEffect(e, model.getPlayer());
				}

				for(Obstacle o : model.getObstacleList()) {
					collisionEffect(o, model.getPlayer());
					for(Enemy e : model.getEnemyList()) {
						collisionEffect(o, e);
					}
				}

				for(PlayerBullet pb : model.getPlayerBulletList()) {
					move(pb);
					pb.setAngle(pb.getSpeed().unit());
					for(Enemy e : model.getEnemyList())
						collisionEffect(pb, e);
					for(Obstacle o : model.getObstacleList())
						collisionEffect(pb, o);
				}

				for(EnemyBullet eb : model.getEnemyBulletList()) {
					move(eb);
					collisionEffect(eb, model.getPlayer());
					for(Obstacle o : model.getObstacleList())
						collisionEffect(eb, o);
					for(PlayerBullet pb : model.getPlayerBulletList())
						collisionEffect(eb, pb);
				}


				for(BossMissile bm : model.getBossMissileList()) {
					move(bm);
					collisionEffect(bm, model.getPlayer());
					for(Obstacle o : model.getObstacleList())
						collisionEffect(bm, o);
					for(PlayerBullet pb : model.getPlayerBulletList())
						collisionEffect(bm, pb);
				}

				if(model.getEnemyList().size() == 0) {
					model.bossStage();
					view.setBossStage(true);
				}

				view.setViewPosition(model.getPlayer().getPosition().sub(new Vector(view.getViewWidth()/2, view.getViewHeight()/2))); // Let the View tracks Player

				view.repaint();
				Thread.sleep((int)(1000*dt));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void applyNetForce(PhysicalObject obj) {
		obj.setAcceleration(new Vector());
		gravity(obj);
		dragForce(obj);
	}

	public void gravity(PhysicalObject obj) {
		obj.addAcceleration(new Vector(0, -g));
	}

	public void dragForce(PhysicalObject obj) {
		double c = 0.001;
		obj.addAcceleration(obj.getSpeed().mul(-c * obj.getSpeed().size()));
		//obj.addAcceleration(obj.getSpeed().mul(-c));
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
		if(e.getKeyCode() == KeyEvent.VK_R && !model.getPlayer().getWeapon().isReloading()) {
			model.getPlayer().getWeapon().settingAmmo();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == true) { // If space released
			spaceBar = false;
		}
		/*
		if(e.getKeyCode() == KeyEvent.VK_H) {
			model.bossStage();
		}
		 */
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

	/*
	public void save() throws IOException {
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		    fout = new FileOutputStream("save.ser");
		    oos = new ObjectOutputStream(fout);
		    oos.writeObject(this.model);
		    //oos.writeObject(this.model.getPlayer().getPosition());
		} catch (Exception ex) {
		    ex.printStackTrace();
		} finally {
		    if(oos != null){
		        oos.close();
		    } 
		}
	}

	public void load() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("save.ser");
            ois = new ObjectInputStream(fis);
            //this.model.getPlayer().setPosition((Vector)ois.readObject()) ;
            //this.model = (Model)ois.readObject();
            view.model = (Model)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}
	 */

	public void move(PhysicalObject obj) {
		obj.setPosition(obj.getPosition().add(obj.getSpeed().mul(dt)));
		obj.setSpeed(obj.getSpeed().add(obj.getAcceleration().mul(dt)));
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
				model.getPlayer().addAcceleration(worldCor.sub(model.getPlayer().getPosition()).unit().mul(8000));
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
		if(model.getPlayer().getWeapon().getAmmo() > 0 && !model.getPlayer().getWeapon().isReloading()) {
			model.getPlayer().setOnShoot(true);
			model.getPlayer().getWeapon().setAmmo(model.getPlayer().getWeapon().getAmmo() - 1);
			int mouseX = view.getMouseX();
			int mouseY = view.getMouseY();
			//---------Convert mouse's vector to world vector then sub Player's vector-----------
			Vector viewCor = new Vector(mouseX, mouseY);
			Vector worldCor = view.viewCorToWorldCor(viewCor);
			Vector dv = worldCor.sub(model.getPlayer().getPosition()).unit();
			//-----------------------------------------------------------------------------------
			PlayerBullet pb = new PlayerBullet(model.getPlayer().getPosition(), dv.mul(2000));
			//pb.addSpeed(model.getPlayer().getSpeed());
			//pb.addAcceleration(model.getPlayer().getAcceleration());
			model.getPlayerBulletList().add(pb);
			model.getPlayer().addSpeed(dv.mul(-300)); // Rebound
		}
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
						for(int i=0; i<20; i++) {
							if(i% 3 == 1) {
								Drawable afterImage = new Drawable(model.getPlayer().getPosition(), model.getPlayer().getWidth(),
										model.getPlayer().getHeight(), model.getPlayer().getSprite(), model.getPlayer().getDirection());
								model.getAfterImageList().add(afterImage);
								System.out.println(model.getPlayer().getPosition());
								afterImageAlphaControl(afterImage);
							}
							model.getPlayer().addPosition(ds.mul(50));
							Thread.sleep(5);	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			model.getPlayer().setSpeed(ds.mul(500));
		}
	}

	public void afterImageAlphaControl(Drawable afterImage) {
		new Thread() {
			public void run() {
				while(afterImage.getAlpha() > 0) {
					afterImage.setAlpha((float)(afterImage.getAlpha() - 0.1));
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				model.getAfterImageList().remove(afterImage);
			}
		}.start();
	}

	public void playerDirectionControll() {
		int mouseX = view.getMouseX();
		if(view.worldCorToViewCor(model.getPlayer().getPosition()).getX() <= mouseX)
			model.getPlayer().setDirection(1);
		else
			model.getPlayer().setDirection(-1);
	}


	public void collisionEffect(Enemy e, Player p) {
		Collision coll = Collision.collisionTest(e.getCollider(), p.getCollider());
		if(coll.isCollision()) {
			e.whenCollisionPlayer();
		}
	}

	public void collisionEffect(Obstacle o, Entity ent) {
		Collision coll = Collision.collisionTest(o.getCollider(), ent.getCollider());
		if(coll.isCollision()) {

			ent.setOnGround(true);

			ent.addPosition(coll.getMinimumTranslationVector().mul(-1));

			double speedLossRate = 0.9;
			Vector axis = coll.getMinimumTranslationVector().unit().mul(-1);
			if(ent.getSpeed().dot(axis) < 0)
				ent.addSpeed(axis.mul( -(2-speedLossRate) * ent.getSpeed().dot(axis)));
		}
	}

	public void collisionEffect(PlayerBullet pB, Enemy e) {
		Collision coll = Collision.collisionTest(pB.getCollider(), e.getCollider());
		if(coll.isCollision()) {
			e.setHp(e.getHp() - model.getPlayer().getWeapon().getAttackDamage());
			model.getPlayerBulletList().remove(pB);
		}
	}

	public void collisionEffect(PlayerBullet pB, Obstacle o) {
		Collision coll = Collision.collisionTest(pB.getCollider(), o.getCollider());
		if(coll.isCollision()) {
			model.getPlayerBulletList().remove(pB);
		}
	}

	public void collisionEffect(EnemyBullet eB, Player p) {
		Collision coll = Collision.collisionTest(eB.getCollider(), p.getCollider());
		if(coll.isCollision()) {
			p.setHp(p.getHp() - eB.getDamage());
			model.getEnemyBulletList().remove(eB);
		}
	}

	public void collisionEffect(EnemyBullet eB, Obstacle o) {
		Collision coll = Collision.collisionTest(eB.getCollider(), o.getCollider());
		if(coll.isCollision()) {
			model.getEnemyBulletList().remove(eB);
		}
	}

	public void collisionEffect(EnemyBullet eB, PlayerBullet pB) {
		Collision coll = Collision.collisionTest(eB.getCollider(), pB.getCollider());
		if(coll.isCollision()) {
			model.getEnemyBulletList().remove(eB);
		}	
	}

	public void collisionEffect(BossMissile bM, Player p) {
		Collision coll = Collision.collisionTest(bM.getCollider(), p.getCollider());
		if(coll.isCollision()) {
			p.setHp(p.getHp() - bM.getDamage());
			model.getBossMissileList().remove(bM);
		}
	}

	public void collisionEffect(BossMissile bM, Obstacle o) {
		Collision coll = Collision.collisionTest(bM.getCollider(), o.getCollider());
		if(coll.isCollision()) {
			model.getBossMissileList().remove(bM);
		}
	}

	public void collisionEffect(BossMissile bM, PlayerBullet pB) {
		Collision coll = Collision.collisionTest(bM.getCollider(), pB.getCollider());
		if(coll.isCollision()) {
			model.getPlayerBulletList().remove(pB);
		}
	}
}