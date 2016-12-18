package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class StartPanel extends JPanel {
    private BufferedImage background;
    StartButtonsPanel buttonPanel;
    MazeSelectionPanel mazeSelectionPanel;

    /**
     * Constructor that creates a panel with border layout
     */
    StartPanel(){
        super(new BorderLayout());

        setSize(800, 525);
        setBackgroundImage();

        buttonPanel = new StartButtonsPanel();
        mazeSelectionPanel = new MazeSelectionPanel();
//        add(buttonPanel, BorderLayout.EAST);
        add(mazeSelectionPanel, BorderLayout.EAST);
    }

    void addStartListener(ActionListener startListener) {
        buttonPanel.startBtn.addActionListener(startListener);
    }

    void addSelectListener(ActionListener selectListener) {
        buttonPanel.selectBtn.addActionListener(selectListener);
    }

    void addCreateListener(ActionListener createListener) {
        buttonPanel.createBtn.addActionListener(createListener);
    }

    void addDoneListener(ActionListener doneListener) {
        mazeSelectionPanel.doneBtn.addActionListener(doneListener);
    }

    void addMazeComboListener(ActionListener mazeListener) {
        mazeSelectionPanel.mazeCombo.addActionListener(mazeListener);
    }

    void addDifficultyListener(ActionListener difficultyListener) {
        mazeSelectionPanel.difficultyCombo.addActionListener(difficultyListener);
    }

    private void setBackgroundImage() {
        try{
            background= ImageIO.read(new File("./filesResource/mainBackground.jpg"));
        }catch(IOException excp){
            System.out.println("Exception caught at buffering image" + excp.getMessage());
        }
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
        private String[] difficulties = {"Easy", "Medium","Hard"};
        private JComboBox difficultyCombo = new JComboBox(difficulties);
        private String difficulty = new String("Easy");

        private String[] mazeList={"maze1", "maze2"};
        private JComboBox mazeCombo = new JComboBox(mazeList);
        private String mazeName = new String("maze1");

        private CustomButton doneBtn;

        /**
         * In the constructor, the implicit maze is set to 0 (maze1), an action listener is added, the ComboBox is added to the JPanel.
         * The panel' opacity is set for visual purpose: to see the combobox on the main bakground not on a white panel.
         */
        MazeSelectionPanel(){
            doneBtn = new CustomButton("Done", 100, 50);

            BoxLayout difficultyLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(difficultyLayout);

            mazeCombo.setSelectedIndex(0);
            difficultyCombo.setSelectedIndex(0);

            mazeCombo.setFont(new Font("", Font.BOLD, 20));
            mazeCombo.setCursor(new Cursor(Cursor.HAND_CURSOR));

            difficultyCombo.setFont(new Font("", Font.BOLD, 20));
            difficultyCombo.setCursor(new Cursor(Cursor.HAND_CURSOR));

            add(mazeCombo);
            add(difficultyCombo);
            add(doneBtn);

            setOpaque(false);
        }

        /**
         * Implementation of actionPerformed to take the selection of the maze.
         * Maze takes the value of the selected item.
         */
//        public void actionPerformed(ActionEvent event) {
//            JComboBox cb = (JComboBox)event.getSource();
//
//            mazeName = (String)cb.getSelectedItem();
//        }

        /**
         * Method to return the maze selected.
         * @return mazeName
         */
        public String getMazeName(){
            return mazeName;
        }

    }
}
