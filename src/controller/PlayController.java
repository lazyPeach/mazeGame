package controller;

import model.Maze;
import model.Model;
import view.PlayPanel;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }


    // Listeners
    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.switchToStart();
        }
    }
}
