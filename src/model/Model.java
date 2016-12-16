package model;

import java.io.*;

/**
 * The Model class in which the functionality of the application is described.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Model {
	private String mazePath;
	private String path;
	private GetPath gp = new GetPath();
	private FileReader fr = null;
	private BufferedReader br = null;
	private char[][] array = new char[22][31];
	private int i=0,j=0,lengthI=0,lengthJ=0;
	private Integer moves = new Integer(0);
	private Integer score = new Integer(0);
	private int difficulty = 0;
	private boolean up,right,down,left;
	
	/**
	 * No-parameter constructor of Model which creates the path to the project.
	 */
	public Model(){
		path = gp.getPath();
	}
	
	/**
	 * Create the path to the maze selected.
	 * @param maze - the name of the maze selected
	 */
	public void createPathToMaze(String maze){
		mazePath = path + "/filesResource/maze/" + maze + ".txt";
	}
	
	/**
	 * Read from the .txt file in which the maze is.
	 * It uses the FileReader  and BufferedReader classes to read from file and calls the function readFromFile() to create
	 * the array on which all the actions will be performed.
	 */
	public void loadMaze(){
		try{
			fr = new FileReader(mazePath);
		}catch (FileNotFoundException e){
			System.out.println("Maze not found" + e.getMessage());
		}
		br = new BufferedReader(fr);
		readFromFile();	
	}
	
	/**
	 * Create the array on which the program works.
	 * Initially the array indexes are initialised otherwise the program could throw an OutOfBoundsException at a further call
	 * of this method.
	 * This method uses the built-in method readLine() of BufferedReader to read one line at a time. Then it reads character by
	 * character until the end of file and updates the array of chars.  
	 */
	public void readFromFile(){
		i=0;
		j=0;
		lengthI=0;
		lengthJ=0;
		try{
			String line =new String();
			while((line = br.readLine())!= null){
				for (j = 0; j<line.length();j++){
					array[i][j]=line.charAt(j);
				}
				i++;
			}
			lengthJ=j;
			lengthI=i;
		}catch(IOException e){
			System.out.println("IOException:" + e.getMessage());
		}
	}
	
	/**
	 * Set the difficulty of the maze.
	 * @param diff - the difficulty is sent from View as an integer: 0=="Easy", 1=="Medium", 2=="Hard"
	 */
	public void setDifficulty(int diff){
		difficulty=diff;
	}
	
	/**
	 * Send the path to other classes which will use it for loading images from file. 
	 * @return The path to the project
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Sent the char array to other classes to create the graphic interface based on it.
	 * @return The char array
	 */
	public char[][] getArray(){
		return array;
	}
	
	/**
	 * Get the number of rows of the array.
	 * @return The number of rows
	 */
	public int getLengthI(){
		return lengthI;
	}
	
	/**
	 * Get the number of columns of the array
	 * @return The number of columns
	 */
	public int getLengthJ(){
		return lengthJ;
	}
	
	/**
	 * Get the position of the robot in the maze; the row on which it is.
	 * @return The row on which the robot is
	 */
	public int getRobotI(){
		int robotI=0;
		for(i=0;i<=lengthI;i++)
			for(j=0;j<=lengthJ;j++){
				if(array[i][j]=='r') robotI=i;
			}
		return robotI;
	}
	
	/**
	 * Get the column on which the robot is.
	 * @return The column on which the robot is
	 */
	public int getRobotJ(){
		int robotJ=0;
		for(i=0;i<=lengthI;i++)
			for(j=0;j<=lengthJ;j++){
				if(array[i][j]=='r') robotJ=j;
			}
		return robotJ;
	}
	
	/**
	 * Get validation for moving the robot up one position.
	 * Verify if in the upper cell is a wall or the path is clear. If it is a wall the robot can not go up. If the path is clear
	 * the robot goes up one position.
	 * The method also prevents the move of the robot "outside" the maze.
	 * @return Whether the robot is able to move up one position of not.
	 */
	public boolean getValidationUp(){
		int robotJ=getRobotJ();
		int robotI=getRobotI();
		if (array[robotI-1][robotJ]=='w' || array[robotI-1][robotJ]=='o' || robotI==0) up=false;
		else{
			array[robotI-1][robotJ]='r';
			array[robotI][robotJ]=' ';
			robotI=robotI-1;
			up = true;
		}
		return up;
	}
	
	/**
	 * Get validation for moving the robot left one position.
	 * Verify if in the left cell is a wall or the path is clear. If it is a wall the robot can not go left. If the path is clear
	 * the robot goes left one position.
	 * The method also prevents the move of the robot "outside" the maze.
	 * @return Whether the robot is able to move left one position of not.
	 */
	public boolean getValidationLeft(){
		int robotJ=getRobotJ();
		int robotI=getRobotI();
		if (array[robotI][robotJ-1]=='w' || array[robotI][robotJ-1]=='o' || robotJ==0) left=false;
		else{
			array[robotI][robotJ-1]='r';
			array[robotI][robotJ]=' ';
			robotJ=robotJ-1;
			left = true;
		}
		return left;
	}
	
	/**
	 * Get validation for moving the robot right one position.
	 * Verify if in the right cell is a wall or the path is clear. If it is a wall the robot can not go right. If the path is clear
	 * the robot goes right one position.
	 * The method also prevents the move of the robot "outside" the maze.
	 * @return Whether the robot is able to move right one position of not.
	 */
	public boolean getValidationRight(){
		int robotJ=getRobotJ();
		int robotI=getRobotI();
		if (array[robotI][robotJ+1]=='w' || array[robotI][robotJ+1]=='o' || robotJ==29) right = false;
		else{
			array[robotI][robotJ+1]='r';
			array[robotI][robotJ]=' ';
			robotJ=robotJ+1;
			right = true;
		}
		return right;
	}
	
	/**
	 * Get validation for moving the robot down one position.
	 * Verify if in the down cell is a wall or the path is clear. If it is a wall the robot can not go down. If the path is clear
	 * the robot goes down one position.
	 * The method also prevents the move of the robot "outside" the maze.
	 * @return Whether the robot is able to move down one position of not.
	 */
	public boolean getValidationDown(){
		int robotJ=getRobotJ();
		int robotI=getRobotI();
		if (array[robotI+1][robotJ]=='w' || array[robotI+1][robotJ]=='o' || robotI==20) down = false;
		else{
			array[robotI+1][robotJ]='r';
			array[robotI][robotJ]=' ';
			robotI=robotI+1;
			down = true;
		}
		return down;
	}
	
	/**
	 * Get the number of moves of the robot. Each time the robot moves one cell the number of moves increments.
	 * @return The number of moves
	 */
	public String getMoves(){
		if( up || right || down || left)
		moves = moves + 1;
		return moves.toString();
	}
	
	/**
	 * Reset the number of moves.
	 */
	public void resetMoves(){
		moves = 0;
	}
	
	/**
	 * Get the final score. For each difficult level the final possible score is greater. From the final score we subtract the number 
	 * of moves.
	 * @return The final score
	 */
	public String getScore(){
		if (difficulty == 0) score = 1000 - moves;
		if (difficulty == 1) score = 1500 - moves;
		if (difficulty == 2) score = 2000 - moves;
		return score.toString();
	}	
}
