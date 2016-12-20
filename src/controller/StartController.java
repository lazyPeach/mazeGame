package controller;

import model.Maze;
import model.MazeSelection;
import model.Model;
import view.View;
import view.StartPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class StartController {
    private View view;
    private Model model;
    private StartPanel startPanel;
    private MazeSelection mazeSelection;

    StartController(View view, Model model) {
        this.view = view;
        this.model = model;

        startPanel = view.getStartPanel();
        mazeSelection = model.getMazeSelection();

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
    private class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            view.switchToPlay();
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

    //// TODO: 12/20/16 switch to create panel
    private class CreateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            view.switchToCreate();
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
            model.setMaze(new Maze(mazeSelection.getMazeName(), mazeSelection.getMazePath(), mazeSelection.getDifficulty()));
            startPanel.switchToStartButtons();
        }
    }

}
