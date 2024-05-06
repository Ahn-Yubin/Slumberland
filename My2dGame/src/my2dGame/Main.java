package my2dGame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
    	Model model = new Model();
        View view = new View(model);
    	Controller controller = new Controller(model, view);
    	new Thread(controller).start();
    }
}