package view;

import javax.swing.*;
import java.awt.*;

/**
 * Class in which we create the visual maze.
 * The maze is created based in a GridLayout, each grid representing a cell of the maze.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class MazePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private char[][] array = new char[21][30];
	private Entity[][] mazeArray = new Entity[21][30];
	private int lengthI,lengthJ,robotI,robotJ,i,j,finishI,finishJ,difficulty;
	private GridLayout layout= new GridLayout(21,30);
	
	/**
	 * No parameter constructor
	 */
	MazePanel(){
		
	}
	
	/**
	 * Constructor which gets as parameters the char array representing the maze, the number of columns and rows and the difficulty.
	 * The constructor sets the parameters and calls the method createArray(); 
	 * @param array of chars representing the maze
	 * @param lengthI - number of rows
	 * @param lengthJ - number of collumns
	 * @param difficulty
	 */
	MazePanel(char[][] array, int lengthI, int lengthJ, int difficulty){
		setLayout(layout);
		this.array=array;
		this.lengthI=lengthI;
		this.lengthJ=lengthJ;
		this.difficulty=difficulty;
		
		createArray();
		for (int i=0;i<lengthI;i++)
			for (int j=0;j<lengthJ;j++){
				add(mazeArray[i][j]);
			}
		setOpaque(false);
	}
	
	/**
	 * Method to get the row on which the robot is situated.
	 */
	public void getRobotI(){
		for (int i = 0; i<lengthI;i++)
			for (int j = 0; j<lengthJ; j++)
				if (array[i][j] == 'r')
					robotI=i;//works on global variable
	}
	
	/**
	 * Method to get the column on which the robot is situated.
	 */
	public void getRobotJ(){
		for (int i = 0; i<lengthI;i++)
			for (int j = 0; j<lengthJ; j++)
				if (array[i][j] == 'r')
					robotJ=j;
	}
	
	/**
	 * Method to get the row on which the finish is situated.
	 */
	public void getFinishI(){
		for (int i = 0; i<lengthI;i++)
			for (int j = 0; j<lengthJ; j++)
				if (array[i][j] == 'f')
					finishI=i;
	}
	
	/**
	 * Method to get the column on which the finish is situated.
	 */
	public void getFinishJ(){
		for (int i = 0; i<lengthI;i++)
			for (int j = 0; j<lengthJ; j++)
				if (array[i][j] == 'f')
					finishJ=j;
	}
	
	/**
	 * Method to create the array.
	 * The array is based on difficulty.
	 * If the difficulty is 0 (Easy) the array if fully vissible.
	 * If not the array is hidden and the area arround the robot and the final cell are visible.
	 */
	public void createArray(){
		getRobotI();
		getRobotJ();
		getFinishI();
		getFinishJ();
		if (this.difficulty == 0){
			for (int i = 0; i<lengthI;i++)
				for (int j = 0; j<lengthJ; j++){
					if (array[i][j] == ' ') mazeArray[i][j]=new Path();
					if (array[i][j] == 'w') mazeArray[i][j]=new Wall();
					if (array[i][j] == 'o') mazeArray[i][j]=new OuterSpace();
					if (array[i][j] == 'r') mazeArray[i][j]=new Robot();
					if (array[i][j] == 'f') mazeArray[i][j]=new Finish();
				}
			
		}
		else{
			for (int i = 0; i<lengthI;i++)
				for (int j = 0; j<lengthJ; j++){
					mazeArray[i][j]=new Shadow(array[i][j]);
				}
			mazeArray[robotI][robotJ].updateNext();
			mazeArray[finishI][finishJ].updateNext();
			updateAroundMedium(robotI,robotJ);
		}
	}
	
	/**
	 * Method to update the visual maze according to the movement: if we want and can move up switch upper cell with robot, etc.
	 * It takes as parameters the direction in which we want to move which represents the ability to move in a direction or not.
	 * If we can move it just changes the actual picture with the next one. As a result robot -> path and path -> robot.
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
	public void updateEasy(boolean up, boolean right, boolean down, boolean left){
		
		if (up){
			mazeArray[robotI][robotJ].update();
			mazeArray[robotI-1][robotJ].update();
		}
		if (right){
			mazeArray[robotI][robotJ].update();
			mazeArray[robotI][robotJ+1].update();
		}
		if (down){
			mazeArray[robotI][robotJ].update();
			mazeArray[robotI+1][robotJ].update();
		}
		if (left){
			mazeArray[robotI][robotJ].update();
			mazeArray[robotI][robotJ-1].update();
		}
		getRobotI();
		getRobotJ();
	}
	
	/**
	 * Method to update the visual maze according to the movement: if we want and can move up switch upper cell with robot, etc.
	 * It takes as parameters the direction in which we want to move which represents the ability to move in a direction or not.
	 * It checks the position of the robot and puts the second image in that panel (i.e. robot image; first image is shadow, 
	 * second robot and third path). It also updates around the robot calling updateArrountMedium(int robotI, int robotJ).
	 * This function will keep the track where we have been with the robot.
	 * Actually the method does not care for the direction. It will update everything around the robot no matter where it goes.
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
	public void updateMedium(boolean up, boolean right, boolean down, boolean left){
		getRobotI();
		getRobotJ();
		mazeArray[robotI][robotJ].updateFirst();
		mazeArray[robotI][robotJ].updateNext();
		updateAroundMedium(robotI,robotJ);
	}
	
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
	public void updateHard(boolean up,boolean right,boolean down, boolean left){
		getRobotI();
		getRobotJ();
		mazeArray[robotI][robotJ].updateFirst();
		mazeArray[robotI][robotJ].updateNext();
		updateAroundMedium(robotI,robotJ);
		if (up){
			updateUp(robotI,robotJ);
		}
		if (right){
			updateRight(robotI,robotJ);
		}
		if (down){
			updateDown(robotI,robotJ);
		}
		if (left){
			updateLeft(robotI,robotJ);
		}
	}
	
	/**
	 * Updates each cell around the robot position.
	 * The try blocks are divided such that when it catches an exception to be able to look forward for others too not to stop there.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
	public void updateAroundMedium(int robotI, int robotJ){
		try{
			mazeArray[robotI-1][robotJ].updateLast();
			mazeArray[robotI-1][robotJ+1].updateLast();
			mazeArray[robotI-1][robotJ-1].updateLast();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Exception above the robot");
		}
		try{
			mazeArray[robotI+1][robotJ].updateLast();
			mazeArray[robotI+1][robotJ+1].updateLast();
			mazeArray[robotI+1][robotJ-1].updateLast();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Exception below the robot");
		}
		try{
			mazeArray[robotI][robotJ+1].updateLast();
			mazeArray[robotI][robotJ-1].updateLast();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Exception to the left or right of robot.");
		}
	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
	public void updateUp(int robotI,int robotJ){
		try{
			mazeArray[robotI+2][robotJ].updateFirst();
			mazeArray[robotI+2][robotJ+1].updateFirst();
			mazeArray[robotI+2][robotJ-1].updateFirst();
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println(" ");
			}
	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
	public void updateDown(int robotI, int robotJ){
		try{
			mazeArray[robotI-2][robotJ].updateFirst();
			mazeArray[robotI-2][robotJ+1].updateFirst();
			mazeArray[robotI-2][robotJ-1].updateFirst();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(" ");
		}
	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
	public void updateLeft(int robotI, int robotJ){
		try{
			mazeArray[robotI][robotJ+2].updateFirst();
			mazeArray[robotI-1][robotJ+2].updateFirst();
			mazeArray[robotI+1][robotJ+2].updateFirst();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(" ");
		}
	}
	
	/**
	 * Checks for robot position and updates the cells which were set to visible before back to shadow.
	 * @param robotI - robot's row
	 * @param robotJ - robot's column
	 */
	public void updateRight(int robotI, int robotJ){
		try{
			mazeArray[robotI][robotJ-2].updateFirst();
			mazeArray[robotI-1][robotJ-2].updateFirst();
			mazeArray[robotI+1][robotJ-2].updateFirst();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(" ");
		}
	}
	
	/**
	 * Method to verify if the robot is in the final position.
	 * @return true if the robot is in the final position and false otherwise
	 */
	public boolean verifyFinal(){
		if((robotI == finishI) && (robotJ == finishJ)) return true;
		else return false;
	}
}
