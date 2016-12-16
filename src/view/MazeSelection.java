package view;

import java.awt.event.*;
import javax.swing.*;

/**
 * Class which extends JPanel, implements ActionListener and creates a ComboBox to select the maze.
 * The class id based on the model presented on:
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/components/ComboBoxDemoProject/src/components/ComboBoxDemo.java
 * @author Lorenzo-Eusebio Patras
 *
 */
public class MazeSelection extends JPanel
	implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private String[] mazes={"maze1", "maze2"};
	private JComboBox mazeList = new JComboBox(mazes);
	private String mazeName = new String("maze1");
		
	/**
	 * In the constructor, the implicit maze is set to 0 (maze1), an action listener is added, the ComboBox is added to the JPanel.
	 * The panel' opacity is set for visual purpose: to see the combobox on the main bakground not on a white panel.
	 */
	MazeSelection(){
		mazeList.setSelectedIndex(0);
		mazeList.addActionListener(this);
		add(mazeList);
		this.setOpaque(false);
	}
	
	/**
	 * Implementation of actionPerformed to take the selection of the maze.
	 * Maze takes the value of the selected item.
	 */
	public void actionPerformed(ActionEvent event) {
		JComboBox cb = (JComboBox)event.getSource();
		mazeName = (String)cb.getSelectedItem();
	}
	
	/**
	 * Method to return the maze selected.
	 * @return mazeName
	 */
	public String getMazeName(){
		return mazeName;
	}
	
	//add frunctions from testGUI/comboboxdemo for images
}

