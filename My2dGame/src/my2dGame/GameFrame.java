package my2dGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame(View view){
        // Settings for the frame.
        setTitle("my2dGame"); // Set frame title.
        setSize(view.getResolutionWidth(), view.getResolutionHeight()); // Set the size of the frame.
        setResizable(false); // Set the frame size to not change.
        setVisible(true); // Show frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Press the x button on the frame to end
        this.add(view);
    }
}
