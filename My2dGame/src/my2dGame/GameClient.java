package my2dGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GameClient extends JFrame implements KeyListener {
	private boolean running = true;
	
	public GameClient(){
		setTitle("my2dGame"); // Set frame title.
		setSize(1024, 768); // Set the size of the frame.
		setResizable(true); // Set the frame size to not change.
		setVisible(true); // Show frame
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
		this.addKeyListener(this);
	}
	
	public boolean getRunning() {
		return running;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			setVisible(false);
			running = false;
			dispose();	
			Model model = new Model(); // View - Model - Controller Structure
	        View view = new View(model);
	    	Controller controller = new Controller(model, view);
	    	new Thread(controller).start();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
