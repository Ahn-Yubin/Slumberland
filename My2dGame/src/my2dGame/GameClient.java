package my2dGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GameClient extends JFrame implements MouseListener, KeyListener {
	private boolean running = true;
	public GameClient(){
		setTitle("my2dGame"); // Set frame title.
		setSize(1024, 768); // Set the size of the frame.
		setResizable(true); // Set the frame size to not change.
		setVisible(true); // Show frame
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	public boolean getRunning() {
		return running;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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
		if(e.getButton() == MouseEvent.BUTTON3) {
		}
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
