package my2dGame;

import java.awt.event.*;

public class Controller implements KeyListener, MouseListener, Runnable{
	
	private Model model;
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
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == false) {
			dash();
			spaceBar = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spaceBar == true) {
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
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				model.getPlayer().setAcceleration(new Vector());
				jetpack();
				gravity();
				dragForce();
				move();
				System.out.println(model.getPlayer());
				System.out.println((int)(1000*dt));
				Thread.sleep((int)(1000*dt));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move() {
		model.getPlayer().setPosition(model.getPlayer().getPosition().add(model.getPlayer().getSpeed().mul(dt)));
		model.getPlayer().setSpeed(model.getPlayer().getSpeed().add(model.getPlayer().getAcceleration().mul(dt)));
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
		if (leftMouseClick) {
			int mouseX = view.getMousePosition().x;
			int mouseY = view.getMousePosition().y;
			Vector viewCor = new Vector(mouseX, mouseY);
			Vector worldCor = view.viewCorToWorldCor(viewCor);
			model.getPlayer().addAcceleration(new Vector(0, g));
			model.getPlayer().addAcceleration(worldCor.sub(model.getPlayer().getPosition()).unit().mul(350));
		}
	}
	
	public void shoot() {
		model.getPlayer().setOnShoot(true);
		int mouseX = view.getMousePosition().x;
		int mouseY = view.getMousePosition().y;
		Vector viewCor = new Vector(mouseX, mouseY);
		Vector worldCor = view.viewCorToWorldCor(viewCor);
		model.getPlayer().addSpeed(worldCor.sub(model.getPlayer().getPosition()).unit().mul(-300));
	}
	
	public void dash() {
		int mouseX = view.getMousePosition().x;
		int mouseY = view.getMousePosition().y;
		Vector viewCor = new Vector(mouseX, mouseY);
		Vector worldCor = view.viewCorToWorldCor(viewCor);
		model.getPlayer().addPosition(worldCor.sub(model.getPlayer().getPosition()).unit().mul(50));
		model.getPlayer().setSpeed(worldCor.sub(model.getPlayer().getPosition()).unit().mul(50));
	}
}
