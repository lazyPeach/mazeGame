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

    /**
     * Constructor that creates a panel with border layout
     */
    StartPanel(){
        super(new BorderLayout());

        setSize(800, 525);
        setBackgroundImage();

        buttonPanel = new StartButtonsPanel();
        add(buttonPanel,BorderLayout.EAST);
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
            setLayout (buttonLayout);

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
}
