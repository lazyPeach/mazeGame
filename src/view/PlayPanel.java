package view;

import model.Maze;
import model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lorenzop on 12/18/16.
 */
public class PlayPanel extends JPanel {
    private Model model;
    private BufferedImage background;
    private MazePanel mazePanel;
    private MovesPanel movesPanel;


    /**
     * No parameter constructor.
     */
    PlayPanel(Model model){
        setLayout(null);

        this.model = model;

        mazePanel = new MazePanel(model.getMaze());
        movesPanel = new MovesPanel();

        mazePanel.setBounds(5, 5, MazePanel.WIDTH, MazePanel.HEIGHT);
        movesPanel.setBounds(610, 5, 190, 500);

        add(mazePanel);
        add(movesPanel);

        setBackgroundImage();
    }

    public void addBackListener(ActionListener backListener) {
        movesPanel.backBtn.addActionListener(backListener);
    }

    private void setBackgroundImage() {
        try{
            background= ImageIO.read(new File("./filesResource/wallBackground.jpg"));
        }catch(IOException excp){
            System.out.println("Exception caught at buffering background wall image " + excp.getMessage());
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

    private class MovesPanel extends JPanel{
        private CustomButton backBtn;
        private JLabel moves;

        MovesPanel() {
            final int btnWidth = 100;
            final int btnHeight = 50;

            backBtn = new CustomButton("Back", btnWidth, btnHeight);
            moves = new JLabel("Moves: 0");
            moves.setFont(new Font("", Font.BOLD, 30));

            BoxLayout movesLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(movesLayout);

            add(moves);
            add(backBtn);

            setOpaque(false);
        }
    }
}
