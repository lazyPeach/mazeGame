package view;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.JPanel;

/**
 * Abstract class which extends JPanel and which will be inherited by the elements of the maze.
 * It uses the card layout for its ability to change through its elements in this case labels.(see Wall, Path, Robot, Finish or Shadow)
 * @author Lorenzo-Eusebio Patras
 *
 */
public abstract class Entity extends JPanel {
	private static final long serialVersionUID = 1L;
	protected CardLayout layout = new CardLayout();
	/**
	 * Constructor in which the layout and the size is set.
	 */
	Entity(){
		setPreferredSize(new Dimension(23,23));
		setLayout(layout);
	}
	/**
	 * The following methods are to be developed in the derived classes.
	 */
	public void update(){}
	public void updateNext(){}
	public void updateBack(){}
	public void updateFirst(){}
	public void updateLast(){}
	
}
