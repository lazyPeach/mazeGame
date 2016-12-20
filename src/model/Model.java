package model;

/**
 * The Model class in which the functionality of the application is described.
 */
public class Model {
	private MazeSelection mazeSelection;
    private Maze maze;


	/**
	 * No-parameter constructor of Model which creates the path to the project.
	 */
	public Model(){
		mazeSelection = new MazeSelection();
        maze = new Maze(mazeSelection.getMazeName(), mazeSelection.getMazePath(), mazeSelection.getDifficulty());
	}

	public MazeSelection getMazeSelection() {
        return mazeSelection;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
