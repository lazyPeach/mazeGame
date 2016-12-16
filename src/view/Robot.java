package view;
import model.*;

import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class which extends Entity.
 * The images for the robot cell of the maze are set.
 * There will be two images. One of the path and one of the robot. Firstly the image of the robot is activated but when we move the robot
 * the image will switch to the image of the path.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Robot extends Entity {
	private static final long serialVersionUID = 1L;
	private BufferedImage imagePath,imageRobot;
	private GetPath gp = new GetPath();
	private String path;
	private JLabel pathLabel,robotLabel;
	
	/**
	 * Constructor in which the images for the robot cell of the maze are loaded.
	 * The images are read from file, then they are set to labels which are added to the JPanel.
	 * First image loaded is the one of the robot. This will be the implicit image of the JPanel.
	 */
	public Robot() {
       path=gp.getPath();
		try {                
          imageRobot = ImageIO.read(new File(path + "/filesResource/robot.gif"));
       } catch (IOException ex) {
            System.out.println("An exception occured while loading the robot image");
       }
		try {                
          imagePath = ImageIO.read(new File(path + "/filesResource/path.gif"));
       } catch (IOException ex) {
            System.out.println("An exception occured while loading the robot-path image");
       }
		robotLabel = new JLabel(new ImageIcon(imageRobot));
    	pathLabel = new JLabel(new ImageIcon( imagePath ));
    	add(robotLabel, "robot");
    	add( pathLabel, "path" );
    }
	
	 /**
     * Method which switches the images.
     * The predefined method next() implemented in CarLayout is used to switch with the next image.
     */
	public void update(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.next(this);
		this.repaint();
		this.validate();
	}
}
