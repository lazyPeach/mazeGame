package controller;

import model.Maze;
import view.PlayPanel;
import view.View;

import java.awt.event.*;

/**
 * Created by lorenzop on 12/18/16.
 */
public class PlayController {
    private View view;
    private PlayPanel playPanel;
    private Maze maze;

    PlayController(View view, PlayPanel playPanel, Maze maze) {
        this.view = view;
        this.playPanel = playPanel;
        this.maze = maze;

        playPanel.addBackListener(new BackListener());
        playPanel.addMazeListener(new MazeKeyListener());
    }


    // Listeners
    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.switchToStart();
        }
    }

    class MazeKeyListener extends KeyAdapter
            implements KeyListener {

        /**
         * Implementing the keyPressed method.
         * We verify whether up, down, left or right arrow key is pressed and we call for validation in model.
         * After getting the validation we update the maze in view accordingly.
         */
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP ){
                maze.move(Maze.Direction.up);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT ){
                maze.move(Maze.Direction.left);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
                maze.move(Maze.Direction.right);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN ){
                maze.move(Maze.Direction.down);
            }
        }

        public void keyReleased(KeyEvent e) {
            //not relevant for this project
        }

        public void keyTyped(KeyEvent e) {
            //not relevant for this project
        }
    }
}
