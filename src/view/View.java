package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

public class View extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int width = 800;
	private final int height = 525;
	private JPanel mainPanel;
	private StartPanel startPanel;


	private String mazeName = new String();
	private String difficulty = new String();
	private String path = new String();
	private Model model = new Model();
	private JButton btnStart = new JButton();
	private JButton btnSelect = new JButton();
	private JButton btnDone = new JButton();
	private JButton btnBack = new JButton();
	private JPanel buttonPanel = new JPanel();
	private JPanel scorePanel = new JPanel();
	private JPanel field = new JPanel();
	private MazeBackgroundPanel mazeBackgroundPanel = new MazeBackgroundPanel();
	private ScorePanel moves = new ScorePanel();
	private JTextField movestxt = new JTextField(5);
	private JTextField finalScore = new JTextField(5);
	private MazeSelectionPanel comboBoxMaze = new MazeSelectionPanel();
	private DifficultySelection comboBoxDiff = new DifficultySelection();
	private MazePanel mazePanel = new MazePanel();
	private FinalBackground finalPanel = new FinalBackground();
	private int diff = 0;
	
	/**
	 * Constructor with parameter and instance of Model class.
	 * In the constructor the path to the project is found which is necessary for sending it further to other classes.
	 * The mainPanel is set, and a function to construct the main page is called.
	 * The the close operation, the frame name and the visibility is also set in the constructor.
	 * @param model
	 */
	public View(Model model){
		this.model=model;
		path = "";

		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new CardLayout());

		startPanel = new StartPanel();
		mainPanel.add(startPanel, "StartPanel");
		//constructMainPage();

		this.setTitle("Robot in a Maze");
		jFrameSetup();
	}

	private void jFrameSetup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		setResizable(false);
	}

	public void addStartListener(ActionListener startListener) {
		startPanel.addStartListener(startListener);
	}

	public void addSelectListener(ActionListener selectListener) {
		startPanel.addSelectListener(selectListener);
	}

	public void addCreateListener(ActionListener createListener) {
		startPanel.addCreateListener(createListener);
	}

	
	/**
	 * Method for adding the 'Done' button to buttonPanel.
	 */
	public void addDoneButton(){
		addImageToDoneButton();
		buttonPanel.add(btnDone);
		buttonPanel.validate();
		this.validate();
	}
	
	/**
	 * Method for removing the 'Done' button from buttonPanel.
	 */
	public void removeDoneButton(){
		buttonPanel.remove(btnDone);
		buttonPanel.validate();
		this.validate();
	}
	
	/**
	 * Method for adding 'Back' button to the panel with the maze.
	 */
	public void addBackButton(){
		addImageToBackButton();
		mazeBackgroundPanel.add(btnBack);
		mazeBackgroundPanel.validate();
	}
	
	/**
	 * Method for removing the 'Back' button from the panel with the maze.
	 */
	public void removeBackButton(){
		mazeBackgroundPanel.remove(btnBack);
		mazeBackgroundPanel.validate();
	}
	
	/**
	 * Method for adding the two ComboBoxes: maze selection and difficulty selection.
	 * Instances of VoidPanel are used for spacing between components.
	 * The ComboBoxes are added instead of 'Start' and 'Select' buttons in the right side of the mainPanel.
	 */
	public void addComboBox(){
		//removeButtons();
		buttonPanel.add(new VoidPanel(35));
		buttonPanel.add(comboBoxMaze);
		buttonPanel.add(new VoidPanel(15));
		buttonPanel.add(comboBoxDiff);
		buttonPanel.add(new VoidPanel(300));
		addDoneButton();
		buttonPanel.add(new VoidPanel(45));
		buttonPanel.validate();
		this.validate();
	}
	
	/**
	 * Method for removing the ComboBox from the mainPanel and adding back the 'Select' and 'Start' buttons. 
	 */
	public void removeComboBox(){
		buttonPanel.removeAll();
//		addButtons();
		buttonPanel.validate();
		this.validate();
	}
	

	/**
	 * Method for adding image to 'Done' button.
	 */
	public void addImageToDoneButton(){
		try {
			Image img = ImageIO.read(new File("./filesResource/done.jpg"));
			btnDone.setIcon(new ImageIcon(img));
			btnDone.setMargin(new Insets(0, 0, 0, 0));//make the whole button an image
		} catch (IOException excp) {
			  System.out.println("Exception caught at buffering startButton image" + excp.getMessage());
		  }
	}
	
	/**
	 * Method for adding image to 'Back' button.
	 */
	public void addImageToBackButton(){
		path = ".";
		try {
			Image img = ImageIO.read(new File(path + "/filesResource/back.jpg"));
			btnBack.setIcon(new ImageIcon(img));
			btnBack.setMargin(new Insets(0, 0, 0, 0));//make the whole button an image
		} catch (IOException excp) {
			  System.out.println("Exception caught at buffering backButton image" + excp.getMessage());
		  }
	}
	
	/**
	 * Method for adding listener to 'Start' button.
	 * @param startLsn
	 */
	public void addStartListener(MouseListener startLsn){
		btnStart.addMouseListener(startLsn);
	}
	
	/**
	 * Method for adding listener to 'Select' button.
	 * @param selLsn
	 */
	public void addSelectListener(MouseListener selLsn){
		btnSelect.addMouseListener(selLsn);
	}
		
	/**
	 * Method for adding listener to 'Done' button.
	 * @param doneLsn
	 */
	public void addDoneListener(MouseListener doneLsn){
		btnDone.addMouseListener(doneLsn);
	}
	
	/**
	 * Method for adding listener to 'Back' button.
	 * @param backLsn
	 */
	public void addBackListener(MouseListener backLsn){
		btnBack.addMouseListener(backLsn);
	}
	
	/**
	 * Method for adding KeyListener to the frame for moving the robot through the maze.
	 * @param frameLsn
	 */
	public void addMazeKeyListener(KeyListener frameLsn){
		this.addKeyListener(frameLsn);
		this.setFocusable(true);
	}
	
	/**
	 * Method for removing the KeyListener.
	 * @param frameLsn
	 */
	public void removeMazeKeyListener(KeyListener frameLsn){
		this.removeKeyListener(frameLsn);
	}
	
	/**
	 * Method for creating the JPanel for TextField to view the number of moves.
	 * The text field can't be changed by anyone but an instance of Model class.
	 */
	public void createTextField(){
		setMoves("0");
		field.add(movestxt);
		field.setPreferredSize( new Dimension( 2, 2 ) );
		movestxt.setEditable(false);
		field.setOpaque(false);
	}
	
	/**
	 * Setting the text for the TextField
	 * @param moves
	 */
	public void setMoves(String moves){
		movestxt.setText(moves);
	}
	
	/**
	 * Method for creating a scorePanel.
	 * The TextField is added and a Back button.
	 * The panel's layout is BoxLayout for arranging the elements on vertical axis.
	 */
	public void createScorePanel(){
		scorePanel.removeAll();
		addImageToBackButton();
		createTextField();
		moves = new ScorePanel(path);
		scorePanel.add(new VoidPanel(3));
		scorePanel.add(moves);
		scorePanel.add(field);
		scorePanel.add(new VoidPanel(15));
		scorePanel.add(btnBack);
		scorePanel.setOpaque(false);
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		scorePanel.setPreferredSize(new Dimension(100,100));
	}
	
	/**
	 * Method for setting the background of the main panel with the maze.
	 * The scorePanel is added in the right part.
	 */
	public void setMazeBackground(){
		mazeBackgroundPanel = new MazeBackgroundPanel(new BorderLayout(),path);
		createScorePanel();
		mazeBackgroundPanel.add(scorePanel,BorderLayout.EAST);
		mazeBackgroundPanel.repaint();
		this.setContentPane(mazeBackgroundPanel);
		this.pack();
	}
	
	/**
	 * Method for removing the maze from the background panel.
	 * The method also removes the 'Back' button and the TextField with moves.
	 */
	public void removeMaze(){
		mazeBackgroundPanel.removeAll();
		mazeBackgroundPanel.repaint();
		this.remove(mazeBackgroundPanel);
		this.repaint();
	}
	
	/**
	 * Method for getting the maze name from ComboBox.
	 * @return the maze name
	 */
	public String getMazeName(){
		mazeName = comboBoxMaze.getMazeName();
		return mazeName;
	}
	
	/**
	 * Method for getting the difficulty from ComboBox and transforming it to its corresponding integer.
	 * @return the difficulty
	 */
	public int getDifficulty(){
		difficulty = comboBoxDiff.getDifficulty();
		if (difficulty ==  "Easy") diff = 0;
		if (difficulty == "Medium") diff = 1;
		if (difficulty == "Hard") diff = 2;
		return diff;
	}
	
	/**
	 * Method for loading the maze in initial state as it is described in the .txt file.
	 * The maze is added to mazeBackgroundPanel in the left side.
	 * @param char array with the maze 
	 * @param lengthI - number of rows
	 * @param lengthJ - number of columns
	 */
	public void loadInitialMaze(char[][] array, int lengthI, int lengthJ){
		mazePanel = new MazePanel(array,lengthI,lengthJ,getDifficulty());
		mazeBackgroundPanel.add(mazePanel,BorderLayout.WEST);
		mazeBackgroundPanel.repaint();
		mazeBackgroundPanel.validate();
		this.setSize(800,525);
	}
	
	/**
	 * Method updating the maze based on the movement direction.
	 * The final state is also checked here by calling verifyFinal() method from mazePanel.
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
	public void updateMaze(boolean up, boolean right, boolean down, boolean left){
		if (diff == 0)	mazePanel.updateEasy(up,right,down,left);
		if (diff == 1) mazePanel.updateMedium(up,right,down,left);
		if (diff == 2) mazePanel.updateHard(up,right,down,left);
		if (mazePanel.verifyFinal()) finalBack();
	}
	
	/**
	 * Method for setting the final panel.
	 * In the final panel the score is displayed and a 'Back' buttons is added to be able to go back to main page.
	 */
	public void finalBack(){
		this.remove(mazeBackgroundPanel);
		JPanel align = new JPanel();
		JLabel message = new JLabel("Score: ",JLabel.CENTER);
		message.setFont(new Font("Arial",Font.BOLD,20));
		message.setForeground(Color.white);
		align.add(new VoidPanel(300));
		align.add(message);
		align.add(finalScore);
		align.add(new VoidPanel(300));
		align.add(btnBack);
		align.setOpaque(false);
		align.setLayout(new BoxLayout(align,BoxLayout.PAGE_AXIS));
		finalPanel =new FinalBackground(path);
		finalScore.setEditable(false);
		finalScore.setText(model.getScore());
		finalPanel.setLayout(new BorderLayout());
		finalPanel.add(align,BorderLayout.EAST);
		finalPanel.repaint();
		this.setContentPane(finalPanel);
		this.pack();
		this.setSize(800,525);
	}
}
