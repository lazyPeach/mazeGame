package view;
import model.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class which extends Entity.
 * The image for the outer cells of the maze is set.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class OuterSpace extends Entity {
	private static final long serialVersionUID = 1L;
	private BufferedImage imageOuter;
	private GetPath gp = new GetPath();
	private String path;
	private JLabel outerLabel;
    
	/**
	 * Constructor in which the image for the outer cells of the maze is loaded.
	 * The image is read from file, then it is set to a label which is added to the JPanel.
	 */
	public OuterSpace() {
		path = gp.getPath();
		try {                
          imageOuter = ImageIO.read(new File(path + "/filesResource/wall.gif"));
       } catch (IOException ex) {
            System.out.println("An exception occured while loading the outer image");
       }
		outerLabel = new JLabel(new ImageIcon(imageOuter));
    	add(outerLabel, "robot");
    }
}
