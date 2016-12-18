package controller;

import model.MazeSelection;
import view.View;
import view.StartPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lorenzop on 12/18/16.
 */
public class StartController {
    private View view;
    private StartPanel startPanel;
    private MazeSelection mazeSelection;

    StartController(View view, StartPanel startPanel, MazeSelection mazeSelection) {
        this.view = view;
        this.startPanel = startPanel;
        this.mazeSelection = mazeSelection;

        startPanel.addStartListener(new StartListener());
        startPanel.addSelectListener(new SelectListener());
        startPanel.addCreateListener(new CreateListener());
        startPanel.addDoneListener(new DoneListener());
    }


    //Listeners section
    /**
     * Listener for Start button. The only method necessary form MouseListener is MouseClicked() in which we add the key listener
     * to view. At this step we create the maze based on the choice in view or on the implicit maze if there is no choice.
     */
    //// TODO: 12/18/16
    private class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
//            view.addMazeKeyListener(listener);
//            model.createPathToMaze(view.getMazeName());
//            model.loadMaze();
//            model.resetMoves();
//            view.setMazeBackground();
//            view.loadInitialMaze(model.getArray(),model.getLengthI(),model.getLengthJ());
        }
    }

    /**
     * Listener for Select button. The only method necessary form MouseListener is MouseClicked() in which we call the method addComboBox()
     * from View.
     */
    private class SelectListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            startPanel.switchToMazeSelection();
            startPanel.populateMazeSelectionPanel(mazeSelection.getMazes(), mazeSelection.getDificulties());
        }
    }

    private class CreateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //TODO
            //view.addComboBox();
        }
    }

    /**
     * Listener for Done button. The only method necessary form MouseListener is MouseClicked() in which we call the method removeComboBox()
     * from View and give a value to mazeName. The difficulty is also set.
     */
    class DoneListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            mazeSelection.setDifficulty(startPanel.getSelectedDifficulty());
            mazeSelection.setMazeName(startPanel.getSelectedMaze());
            startPanel.switchToStartButtons();
        }
    }

}
