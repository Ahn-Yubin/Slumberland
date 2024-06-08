package gameSystem;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	public GameFrame(View view){
        // Settings for the frame.
        setTitle("my2dGame"); // Set frame title.
        setSize(view.getResolutionWidth(), view.getResolutionHeight()); // Set the size of the frame.
        setLocation((1920 - this.getWidth()) /2, (1080 - this.getHeight()) / 2);
        setResizable(false); // Set the frame size to not change.
        setVisible(true); // Show frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
        this.add(view);
    }
}
