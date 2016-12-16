package view;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.*;

/**
 * Class used for setting the image and the layout of the maze panel.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class MazeBackgroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	
	/**
	 * No parameter constructor.
	 */
	MazeBackgroundPanel(){
		this(new FlowLayout(),new String());
	}
	
	/**
	 * Constructor with a layout and a string parameter which represents the path to the project. 
	 * The layout of the JPanel is set. 
	 * @param Layout of the JPanel
	 * @param Path to the background image
	 */
	MazeBackgroundPanel(LayoutManager layout, String path){
		super(layout);
		try{
			background= ImageIO.read(new File(path + "\\filesResource\\wallBackground.jpg"));
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
}
