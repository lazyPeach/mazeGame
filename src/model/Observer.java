package model;

/**
 * Created by lorenzop on 12/19/16.
 */
public interface Observer {
    void update(Maze.UpdateState newState);
}
