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
 * The images for the hidden cells of the maze are set.
 * There will be three images. One of the shadow and the other two are set accordingly.
 * Firstly the image of the shadow is activated but when we move the robot the image will switch discovering arount the robot.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Shadow extends Entity{
	private static final long serialVersionUID = 1L;
	private BufferedImage imageShadow, imageShadow1, imageShadow2;
	private GetPath gp = new GetPath();
	private String path;
	private JLabel shadowLabel,label1,label2;
	
	/**
	 * No parameter constructor.
	 */
	public Shadow(){
		this(' ');
	}
	
	/**
	 * Constructor in which the images for the hidden cells of the maze are loaded.
	 * The images are read from file, then they are set to labels which are added to the JPanel.
	 * A check to see what elements are hidden is done and the images are set accordingly.
	 * First image loaded is the one of the shadow. This will be the implicit image of the JPanel.
	 */
    public Shadow(char c) {
    	path = gp.getPath();
    	
    	//load shadow image
    	try {                
            imageShadow = ImageIO.read(new File(path +"/filesResource/shadow.gif"));
         } catch (IOException ex) {
              System.out.println("An exception occured while loading the shadow image");
         }    	
    	String element1 = new String();
    	String element2= new String();
    	if (c == ' ') {
    		element1 = "robot";
    		element2 = "path"; 
    	}
    	if (c == 'w') {
    		element1 = "wall";
    		element2 = "wall";
    	}
    	if (c == 'o') {
    		element1 = "wall";
    		element2 = "wall";
    	}
    	if (c == 'r') {
    		element1 = "robot";
    		element2 = "path";
    	}
    	if (c == 'f') {
    		element1 = "finish";
    		element2 = "finish";
    	}
    	try {                
            imageShadow1 = ImageIO.read(new File(path +"/filesResource/" + element1 + ".gif"));
         } catch (IOException ex) {
              System.out.println("An exception occured while loading the shadow1 image");
         }
    	try {                
            imageShadow2 = ImageIO.read(new File(path +"/filesResource/" + element2 + ".gif"));
         } catch (IOException ex) {
              System.out.println("An exception occured while loading the shadow2 image");
         }
    	
    	shadowLabel = new JLabel(new ImageIcon(imageShadow));
    	label1 = new JLabel(new ImageIcon( imageShadow1 ));
    	label2 = new JLabel(new ImageIcon( imageShadow2 ));
    	add( shadowLabel, "shadow" );
    	add(label1, "element1");
    	add(label2, "element2" );
    }
    
    /**
     * Method which switches the images.
     * The predefined method next() implemented in CarLayout is used to switch with the next image.
     */
    public void updateNext(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.next(this);
		this.repaint();
		this.validate();
	}
    
    /**
     * Method which switches the images.
     * The predefined method previous() implemented in CarLayout is used to switch with the previous image.
     */
    public void updateBack(){
    	CardLayout cl = (CardLayout)(this.getLayout());
    	cl.previous(this);
    	this.repaint();
    	this.validate();
    }
    
    /**
     * Method which switches the images.
     * The predefined method first() implemented in CarLayout is used to switch to the first image.
     */
    public void updateFirst(){
    	CardLayout cl = (CardLayout)(this.getLayout());
    	cl.first(this);
    	this.repaint();
    	this.validate();
    }
    
    /**
     * Method which switches the images.
     * The predefined method last() implemented in CarLayout is used to switch to the last image.
     */
    public void updateLast(){
    	CardLayout cl = (CardLayout)(this.getLayout());
    	cl.last(this);
    	this.repaint();
    	this.validate();
    }
}
