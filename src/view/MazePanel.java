package view;

import model.Maze;
import model.MazeElement;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Class in which we create the visual maze.
 * The maze is created based in a GridLayout, each grid representing a cell of the maze.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class MazePanel extends JPanel implements Observer{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;

	private Maze maze;
	private Entity[][] mazeArray;


	private char[][] array = new char[21][30];


	/**
	 * No parameter constructor
	 */
	MazePanel() {}

	MazePanel(Maze maze){
		this.maze = maze;

		GridLayout layout= new GridLayout(Maze.ROWS, Maze.COLS);
		setLayout(layout);

		mazeArray = new Entity[Maze.ROWS][Maze.COLS];
		createArray();
		addArray();

        maze.subscribe(this);

        setFocusable(true);
	}

	public void addMazeListener(KeyListener keyEvent) {
		addKeyListener(keyEvent);
	}

	/**
	 * Method to create the array.
	 * The array is based on difficulty.
	 * If the difficulty is 0 (Easy) the array if fully vissible.
	 * If not the array is hidden and the area arround the robot and the final cell are visible.
	 */
	public void createArray(){
		for (int i = 0; i < Maze.ROWS; i++) {
			for (int j = 0; j < Maze.COLS; j++) {
				mazeArray[i][j] = new Entity();
			}
		}
	}

	private void addArray() {
		for (int i = 0; i< Maze.ROWS; i++) {
			for (int j = 0; j < Maze.COLS; j++) {
				add(mazeArray[i][j]);
			}
		}
	}

	public void update(Maze.UpdateState newState) {
		switch(newState) {
			case updateMaze:
				updateMaze();
				break;
			case finishGame:
				break;
		}
    }

	private void updateMaze() {
		MazeElement[][] mazeArrayRaw = maze.getMazeArray();

		for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLS; j++) {
                if (mazeArrayRaw[i][j].element == MazeElement.Element.path) mazeArray[i][j].displayPath();
                if (mazeArrayRaw[i][j].element == MazeElement.Element.wall) mazeArray[i][j].displayWall();
                if (mazeArrayRaw[i][j].element == MazeElement.Element.robot) mazeArray[i][j].displayRobot();
                if (mazeArrayRaw[i][j].element == MazeElement.Element.finish) mazeArray[i][j].displayFinish();
                if (!mazeArrayRaw[i][j].visibility) mazeArray[i][j].displayShadow();
            }
        }
	}

	/**
	 * Constructor which gets as parameters the char array representing the maze, the number of columns and rows and the difficulty.
	 * The constructor sets the parameters and calls the method createArray(); 
	 * @param array of chars representing the maze
	 * @param lengthI - number of rows
	 * @param lengthJ - number of collumns
	 * @param difficulty
	 */
	



	
//	public void updateMedium(boolean up, boolean right, boolean down, boolean left){
//		getRobotI();
//		getRobotJ();
//		mazeArray[robotI][robotJ].updateFirst();
//		mazeArray[robotI][robotJ].updateNext();
//		updateAroundMedium(robotI,robotJ);
//	}
	
	/**
	 * Method to update the visual maze according to the movement: if we want and can move up switch upper cell with robot, etc.
	 * It takes as parameters the direction in which we want to move which represents the ability to move in a direction or not.
	 * It checks the position of the robot and puts the second image in that panel (i.e. robot image; first image is shadow, 
	 * second robot and third path). It also updates around the robot calling updateArrountMedium(int robotI, int robotJ).
	 * It also checks for the movement and updates the track of the robot turning everything back to shadow.
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
//	public void updateHard(boolean up,boolean right,boolean down, boolean left){
//		getRobotI();
//		getRobotJ();
//		mazeArray[robotI][robotJ].updateFirst();
//		mazeArray[robotI][robotJ].updateNext();
//		updateAroundMedium(robotI,robotJ);
//		if (up){
//			updateUp(robotI,robotJ);
//		}
//		if (right){
//			updateRight(robotI,robotJ);
//		}
//		if (down){
//			updateDown(robotI,robotJ);
//		}
//		if (left){
//			updateLeft(robotI,robotJ);
//		}
//	}
	
	/**
	 * Updates each cell around the robot position.
	 * The try blocks are divided such that when it catches an exception to be able to look forward for others too not to stop there.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
//	public void updateAroundMedium(int robotI, int robotJ){
//		try{
//			mazeArray[robotI-1][robotJ].updateLast();
//			mazeArray[robotI-1][robotJ+1].updateLast();
//			mazeArray[robotI-1][robotJ-1].updateLast();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println("Exception above the robot");
//		}
//		try{
//			mazeArray[robotI+1][robotJ].updateLast();
//			mazeArray[robotI+1][robotJ+1].updateLast();
//			mazeArray[robotI+1][robotJ-1].updateLast();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println("Exception below the robot");
//		}
//		try{
//			mazeArray[robotI][robotJ+1].updateLast();
//			mazeArray[robotI][robotJ-1].updateLast();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println("Exception to the left or right of robot.");
//		}
//	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
//	public void updateUp(int robotI,int robotJ){
//		try{
//			mazeArray[robotI+2][robotJ].updateFirst();
//			mazeArray[robotI+2][robotJ+1].updateFirst();
//			mazeArray[robotI+2][robotJ-1].updateFirst();
//			}catch(ArrayIndexOutOfBoundsException e){
//				System.out.println(" ");
//			}
//	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
//	public void updateDown(int robotI, int robotJ){
//		try{
//			mazeArray[robotI-2][robotJ].updateFirst();
//			mazeArray[robotI-2][robotJ+1].updateFirst();
//			mazeArray[robotI-2][robotJ-1].updateFirst();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println(" ");
//		}
//	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
//	public void updateLeft(int robotI, int robotJ){
//		try{
//			mazeArray[robotI][robotJ+2].updateFirst();
//			mazeArray[robotI-1][robotJ+2].updateFirst();
//			mazeArray[robotI+1][robotJ+2].updateFirst();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println(" ");
//		}
//	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
//	public void updateRight(int robotI, int robotJ){
//		try{
//			mazeArray[robotI][robotJ-2].updateFirst();
//			mazeArray[robotI-1][robotJ-2].updateFirst();
//			mazeArray[robotI+1][robotJ-2].updateFirst();
//		}catch(ArrayIndexOutOfBoundsException e){
//			System.out.println(" ");
//		}
//	}
//

}
