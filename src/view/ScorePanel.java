package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Class for constructing a panel in which to keep the number of moves the player had done.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class ScorePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage scoreImage;
	
	/**
	 * No parameter constructor
	 */
	ScorePanel(){
		this(new String());
	}
	
	/**
	 * Constructor in which we add an image to the JPanel and set the size.
	 * @param path to the image
	 */
	ScorePanel(String path){
		try{
			scoreImage=ImageIO.read(new File(path + "\\filesResource\\moves.jpg"));
		}catch(IOException excp){
			System.out.println(path);//"Exception caught at buffering for score image" + excp.getMessage());
		}
		setPreferredSize(new Dimension(100,50));
		setOpaque(false);
	}
	
	/**
	 * Method to put the loaded background image to the JPanel.
	 * It uses the predefined method drawImage() from Graphics.
	 * drawImage(Image img, int x, int y, ImageObserver observer); Draws as much of the specified image as is currently available.
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scoreImage, 0, 0, null);
    }
	
	
}
