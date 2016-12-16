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
	private String mazeName = new String("maze1");
	private String difficultyName = new String("Easy");
	private boolean up, down, left, right;
	private int moves;
	private MazeKeyListener listener = new MazeKeyListener();
	
	/**
	 * Constructor of Controller class. When creating the controller we also add listeners to all instances of View.
	 * @param view - instance of View class
	 * @param model - instance of Model class
	 */
	public Controller(View view, Model model){
		this.view=view;
		this.model=model;
		view.addSelectListener(new SelectListener());
		view.addStartListener(new StartListener());
		view.addDoneListener(new DoneListener());
		view.addBackListener(new BackListener());
	}
	
	//implement listeners
	/**
	 * Listener for Start button. The only method necessary form MouseListener is MouseClicked() in which we add the key listener
	 * to view. At this step we create the maze based on the choice in view or on the implicit maze if there is no choice. 
	 * @author Lorenzo-Eusebio Patras
	 *
	 */
	class StartListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			view.addMazeKeyListener(listener);
			model.createPathToMaze(view.getMazeName());
			model.loadMaze();
			model.resetMoves();
			view.setMazeBackground();
			view.loadInitialMaze(model.getArray(),model.getLengthI(),model.getLengthJ());
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
	 * Listener for Select button. The only method necessary form MouseListener is MouseClicked() in which we call the method addComboBox()
	 * from View.
	 * @author Lorenzo-Eusebio Patras
	 *
	 */
	class SelectListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			view.addComboBox();
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
	 * Listener for Done button. The only method necessary form MouseListener is MouseClicked() in which we call the method removeComboBox()
	 * from View and give a value to mazeName. The difficulty is also set.
	 * @author Lorenzo
	 *
	 */
	class DoneListener implements MouseListener{
		public void mouseClicked(MouseEvent e){
			view.removeComboBox();
			mazeName = view.getMazeName();
			model.setDifficulty(view.getDifficulty());
		}
		public void mouseEntered(MouseEvent e){
			//not relevant for this project
		}
		public void mouseExited(MouseEvent e){
			//not relevant for this project
		}
		public void mousePressed(MouseEvent e){
			//not relevant for this project
		}
		public void mouseReleased(MouseEvent e){
			//not relevant for this project
		}
	}
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
			view.removeMaze();
			view.constructMainPage();			
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
