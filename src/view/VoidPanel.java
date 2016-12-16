package view;

import java.awt.Dimension;
import javax.swing.*;

/**
 * Class used to add some spaces between labels on vertical axis.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class VoidPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	/**
	 * No parameter constructor.
	 */
	VoidPanel(){
		this(0);
	}
	
	/**
	 * Constructor with a parameter i which will set the width of the JPanel.
	 * @param Width of the Panel
	 */
	VoidPanel(int i){
		setPreferredSize(new Dimension(5,i));
		setOpaque(false);
	}
}
