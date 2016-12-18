package view;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Class used for setting the background image of the final JPanel, the panel which appears when the robot find the end of the maze.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class FinalPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	
	/**
	 * No parameter constructor
	 */
	FinalPanel(){
		this(new String());
	}
	
	/**
	 * Constructor with a string parameter.
	 * The background image is read from file and loaded in the constructor. The path is used for portability.
	 * @param path to the background image
	 */
	FinalPanel(String path){
		try{
			background=ImageIO.read(new File(path + "./filesResource/final.jpg"));
		}catch(IOException excp){
			System.out.println("Exception caught at buffering final image" + excp.getMessage());
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
}
