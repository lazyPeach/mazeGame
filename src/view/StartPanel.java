package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {
    private BufferedImage background;
    private JPanel rightPanel;
    private StartButtonsPanel buttonPanel;
    private MazeSelectionPanel mazeSelectionPanel;

    private final String BUTTONS = "buttons";
    private final String MAZE_SELECTION = "mazeSelection";

    StartPanel(){
        super(new BorderLayout());

        setSize(800, 525);
        setBackgroundImage();

        rightPanel = new JPanel();
        rightPanel.setLayout(new CardLayout());
        rightPanel.setOpaque(false);

        buttonPanel = new StartButtonsPanel();
        mazeSelectionPanel = new MazeSelectionPanel();

        rightPanel.add(buttonPanel, BUTTONS);
        rightPanel.add(mazeSelectionPanel, MAZE_SELECTION);
        add(rightPanel, BorderLayout.EAST);
    }

    public void switchToMazeSelection() {
        CardLayout cl = (CardLayout)(rightPanel.getLayout());
        cl.show(rightPanel, MAZE_SELECTION);
    }

    public void switchToStartButtons() {
        CardLayout cl = (CardLayout)(rightPanel.getLayout());
        cl.show(rightPanel, BUTTONS);
    }

    public String getSelectedMaze() {
        return (String)mazeSelectionPanel.mazeCombo.getSelectedItem();
    }

    public String getSelectedDifficulty() {
        return (String)mazeSelectionPanel.difficultyCombo.getSelectedItem();
    }

    public void addStartListener(ActionListener startListener) {
        buttonPanel.startBtn.addActionListener(startListener);
    }

    public void addSelectListener(ActionListener selectListener) {
        buttonPanel.selectBtn.addActionListener(selectListener);
    }

    public void addCreateListener(ActionListener createListener) {
        buttonPanel.createBtn.addActionListener(createListener);
    }

    public void addDoneListener(ActionListener doneListener) {
        mazeSelectionPanel.doneBtn.addActionListener(doneListener);
    }

    /**
     * Method to put the loaded background image to the JPanel.
     * It uses the predefined method drawImage() from Graphics.
     * drawImage(Image img, int x, int y, ImageObserver observer); Draws as much of the specified image as is currently available.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    private void setBackgroundImage() {
        try{
            background= ImageIO.read(new File("./filesResource/mainBackground.jpg"));
        }catch(IOException excp){
            System.out.println("Exception caught at buffering image " + excp.getMessage());
        }
    }

    public void populateMazeSelectionPanel(String[] mazes, String[] difficulties) {
        mazeSelectionPanel.setMazes(mazes);
        mazeSelectionPanel.setDiffictulies(difficulties);
    }


    private class StartButtonsPanel extends JPanel{
        private JButton startBtn;
        private JButton selectBtn;
        private JButton createBtn;

        StartButtonsPanel() {
            BoxLayout buttonLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(buttonLayout);

            final int width = 100;
            final int height = 50;
            startBtn = new CustomButton("Start game", width, height);
            selectBtn = new CustomButton("Select maze", width, height);
            createBtn = new CustomButton("Create maze", width, height);

            add(startBtn);
            add(selectBtn);
            add(createBtn);

            setOpaque(false);
        }
    }

    private class MazeSelectionPanel extends JPanel{
        private JComboBox<String> difficultyCombo;
        private JComboBox<String> mazeCombo;
        private CustomButton doneBtn;

        /**
         * In the constructor, the implicit maze is set to 0 (maze1), an action listener is added, the ComboBox is added to the JPanel.
         * The panel' opacity is set for visual purpose: to see the combobox on the main bakground not on a white panel.
         */
        MazeSelectionPanel(){

            difficultyCombo = new JComboBox<>();
            mazeCombo = new JComboBox<>();
            doneBtn = new CustomButton("Done", 100, 50);

            BoxLayout difficultyLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(difficultyLayout);

            mazeCombo.setFont(new Font("", Font.BOLD, 20));
            mazeCombo.setCursor(new Cursor(Cursor.HAND_CURSOR));

            difficultyCombo.setFont(new Font("", Font.BOLD, 20));
            difficultyCombo.setCursor(new Cursor(Cursor.HAND_CURSOR));

            add(mazeCombo);
            add(difficultyCombo);
            add(doneBtn);

            setOpaque(false);
        }

        void setDiffictulies(String[] difficulties) {
            difficultyCombo.removeAllItems();
            for(String item : difficulties) {
                difficultyCombo.addItem(item);
            }
        }

        void setMazes(String[] mazes) {
            mazeCombo.removeAllItems();
            for(String item : mazes) {
                mazeCombo.addItem(item);
            }
        }
    }
}
