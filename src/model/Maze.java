package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by lorenzop on 12/18/16.
 */


public class Maze {
    public static final int ROWS = 20;
    public static final int COLS = 24;

    public enum Direction {up, down, left, right}

    private String mazeName;
    private String mazePath;
    private Difficulty difficulty;
    private char[][] mazeArray;
    private Vector<Observer> observers = new Vector<>();

    public Maze(String mazeName, String mazePath, Difficulty difficulty) {
        this.mazeName = mazeName;
        this.mazePath = mazePath;
        this.difficulty = difficulty;
        mazeArray = new char[20][24];
        loadMaze();
    }

    /**
     * Read from the .txt file in which the maze is.
     * It uses the FileReader  and BufferedReader classes to read from file and calls the function readFromFile() to create
     * the array on which all the actions will be performed.
     */
    public void loadMaze(){
        try{
            FileReader fr = new FileReader(mazePath);
            BufferedReader br = new BufferedReader(fr);
            readFromFile(br);
        }catch (FileNotFoundException e){
            System.out.println("Maze path not found " + e.getMessage());
        }
    }

    /**
     * Create the array on which the program works.
     * Initially the array indexes are initialised otherwise the program could throw an OutOfBoundsException at a further call
     * of this method.
     * This method uses the built-in method readLine() of BufferedReader to read one line at a time. Then it reads character by
     * character until the end of file and updates the array of chars.
     */
    public void readFromFile(BufferedReader br){
        int i=0;
        int j=0;

        try{
            String line =new String();
            while((line = br.readLine()) != null){
                for (j = 0; j<line.length();j++){
                    mazeArray[i][j] = line.charAt(j);
                }
                i++;
            }
        }catch(IOException e){
            System.out.println("IOException when reading the maze: " + e.getMessage());
        }
    }

    public void subscribe(Observer o) {
        observers.addElement(o);
        o.update(mazeArray);
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(mazeArray);
        }
    }

    public void move(Direction direction) {
        if (!isValidMove(direction)) {
            return;
        }

        Position robotPosition = getRobotPosition();

        switch (direction) {
            case up:
                mazeArray[robotPosition.getI() - 1][robotPosition.getJ()] = 'r';
                mazeArray[robotPosition.getI()][robotPosition.getJ()] = ' ';
                break;
            case down:
                mazeArray[robotPosition.getI() + 1][robotPosition.getJ()] = 'r';
                mazeArray[robotPosition.getI()][robotPosition.getJ()] = ' ';
                break;
            case left:
                mazeArray[robotPosition.getI()][robotPosition.getJ() - 1] = 'r';
                mazeArray[robotPosition.getI()][robotPosition.getJ()] = ' ';
                break;
            case right:
                mazeArray[robotPosition.getI()][robotPosition.getJ() + 1] = 'r';
                mazeArray[robotPosition.getI()][robotPosition.getJ()] = ' ';
                break;
        }

        notifyObservers();
    }

    private boolean isValidMove(Direction direction) {
        Position robotPosition = getRobotPosition();
        return isInsideBounds(direction, robotPosition) && isOnPath(direction, robotPosition);
    }

    private boolean isInsideBounds(Direction direction, Position robotPosition) {
        if (direction == Direction.up && robotPosition.getI() == 0) return false;
        if (direction == Direction.down && robotPosition.getI() == ROWS - 1) return false;
        if (direction == Direction.left && robotPosition.getJ() == 0) return false;
        if (direction == Direction.right && robotPosition.getJ() == COLS - 1) return false;

        return true;
    }

    private boolean isOnPath(Direction direction, Position robotPosition) {
        if (direction == Direction.up &&
                mazeArray[robotPosition.getI() - 1][robotPosition.getJ()] != ' ') return false;

        if (direction == Direction.down &&
                mazeArray[robotPosition.getI() + 1][robotPosition.getJ()] != ' ') return false;

        if (direction == Direction.left &&
                mazeArray[robotPosition.getI()][robotPosition.getJ() - 1] != ' ') return false;

        if (direction == Direction.right &&
                mazeArray[robotPosition.getI()][robotPosition.getJ() + 1] != ' ') return false;

        return true;
    }

    private Position getRobotPosition() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (mazeArray[i][j] == 'r') return new Position(i, j);
            }
        }

        return new Position(0, 0);
    }

    private Position getFinishPosition() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (mazeArray[i][j] == 'f') return new Position(i, j);
            }
        }

        return new Position(0, 0);
    }
}
