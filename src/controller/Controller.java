package controller;
import view.*;
import model.*;
import java.awt.event.*;

/**
 * Controller class which links the View and the Model and implements the functionality of the application by creating the 
 * neccessary listeners.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Controller {
	private View view;
	private Model model;


	private boolean up, down, left, right;

	private MazeKeyListener listener = new MazeKeyListener();
	
	/**
	 * Constructor of Controller class. When creating the controller we also add listeners to all instances of View.
	 * @param view - instance of View class
	 */
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;

        StartController startController = new StartController(view, model);
        PlayController playController = new PlayController(view, view.getPlayPanel(), model.getMaze());
	}


	// Listeners section

	/**
	 * Listener for Back button. The only method necessary form MouseListener is MouseClicked() in which we change the content of
	 * the frame by calling the methods removeMaze() and constructMainPage() in View. At this step the KeyListener is also removed
	 * since it is no longer necessary.
	 * @author Lorenzo-Eusebio Patras
	 *
	 */
	class BackListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			view.removeMazeKeyListener(listener);
//			view.removeMaze();
			//TODO REMOVE METHOD
//			view.constructMainPage();
		}
		public void mouseEntered(MouseEvent e) {	
			//not relevant for this project
		}
		public void mouseExited(MouseEvent e) {
			//not relevant for this project
		}
		public void mousePressed(MouseEvent e) {
			//not relevant for this project
		}
		public void mouseReleased(MouseEvent e) {	
			//not relevant for this project
		}		
	}
	
	/**
	 * KeyListener for moving the robot through the maze.
	 * @author Lorenzo-Eusebio Patras
	 *
	 */
	class MazeKeyListener extends KeyAdapter
		implements KeyListener{
		
		/**
		 * Implementing the keyPressed method.
		 * We verify whether up, down, left or right arrow key is pressed and we call for validation in model.
		 * After getting the validation we update the maze in view accordingly.  
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP ){
				up = model.getValidationUp();
				left = false;
				right = false;
				down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT ){
				up = false;
				left = model.getValidationLeft();
				right = false;
				down = false;
			} 
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
				up = false;
				left = false;
				right = model.getValidationRight();
				down = false;
			} 
			if (e.getKeyCode() == KeyEvent.VK_DOWN ){
				up = false;
				left = false;
				right = false;
				down = model.getValidationDown();
			} 
			view.updateMaze(up,right,down,left);
			view.setMoves(model.getMoves());
		}
		public void keyReleased(KeyEvent e) {
			//not relevant for this project
		}
		public void keyTyped(KeyEvent e) {	
			//not relevant for this project
		}
	}	
}
