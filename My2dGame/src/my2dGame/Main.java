package my2dGame;

public class Main {
    public static void main(String[] args){
    	Model model = new Model(); // View - Model - Controller Structure
        View view = new View(model);
    	Controller controller = new Controller(model, view);
    	new Thread(controller).start();
    }
}
