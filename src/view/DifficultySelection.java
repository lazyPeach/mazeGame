package view;

import java.awt.event.*;
import javax.swing.*;

/**
 * Class which extends JPanel, implements ActionListener and creates a ComboBox to select the difficulty: Easy, Medium, Hard.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class DifficultySelection extends JPanel
	implements ActionListener{
	private static final long serialVersionUID = 1L;

	private String[] difficulties = {"Easy", "Medium","Hard"};
	private JComboBox difficultyList = new JComboBox(difficulties);
	private String difficulty = new String("Easy");
	
	/**
	 * In the constructor, the implicit difficulty is set to 0 (Easy), an action listener is added, the ComboBox is added to the JPanel.
	 * The panel' opacity is set for visual purpose: to see the combobox on the main bakground not on a white panel.
	 */
	DifficultySelection(){
		difficultyList.setSelectedIndex(0);
		difficultyList.addActionListener(this);
		add(difficultyList);
		this.setOpaque(false);
	}
	
	/**
	 * Implementation of actionPerformed to take the selection of the difficulty.
	 * Difficulty takes the value of the selected item.
	 */
	public void actionPerformed(ActionEvent event) {
		JComboBox cb = (JComboBox)event.getSource();
		difficulty = (String)cb.getSelectedItem();
	}
	
	/**
	 * Method to return the difficulty selected.
	 * @return difficulty
	 */
	public String getDifficulty(){
		return difficulty;
	}
}
