package view;
import model.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class which extends Entity.
 * The image for the final cell of the maze is set.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Finish extends Entity {
	private static final long serialVersionUID = 1L;
	private BufferedImage imageFinish;
	private GetPath gp = new GetPath();
	private String path;
	private JLabel finishLabel;
    
	/**
	 * Constructor in which the image for the final cell of the maze is loaded.
	 * The image is read from file, then it is set to a label which is added to the JPanel.
	 */
	public Finish() {
		path = gp.getPath();
		try {                
          imageFinish = ImageIO.read(new File(path + "/filesResource/finish.gif"));
       } catch (IOException ex) {
            System.out.println("An exception occured while loading the finish image");
       }
		finishLabel = new JLabel(new ImageIcon(imageFinish));
    	add(finishLabel, "robot");
    }
}
