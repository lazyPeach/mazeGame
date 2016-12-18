package view;

import model.GetPath;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Abstract class which extends JPanel and which will be inherited by the elements of the maze.
 * It uses the card layout for its ability to change through its elements in this case labels.(see Wall, Path, Robot, Finish or Shadow)
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Entity extends JPanel {
	private BufferedImage robotImg, wallImg, shadowImg, pathImg, finishImg;
	private JLabel robotLbl, wallLbl, shadowLbl, pathLbl, finishLbl;

	private final String ROBOT = "robot";
	private final String WALL = "wall";
	private final String SHADOW = "shadow";
	private final String PATH = "path";
	private final String FINISH = "finish";


	public Entity(){
		setSize(25, 25);
		CardLayout layout = new CardLayout();
		setLayout(layout);

		loadFinishImage();
		loadPathImage();
		loadRobotImage();
		loadShadowImage();
		loadWallImage();

		add(robotLbl, ROBOT);
		add(wallLbl, WALL);
		add(shadowLbl, SHADOW);
		add(pathLbl, PATH);
		add(finishLbl, FINISH);
	}

	public void displayRobot() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, ROBOT);
	}

	public void displayWall() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, WALL);
	}

	public void displayShadow() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, SHADOW);
	}

	public void displayPath() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, PATH);
	}

	public void displayFinish() {
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this, FINISH);
	}

	private void loadRobotImage() {
		try {
			robotImg = ImageIO.read(new File("./filesResource/robot.gif"));
		} catch (IOException ex) {
			System.out.println("An exception occured while loading the robot image");
		}

		robotLbl = new JLabel(new ImageIcon(robotImg));
	}

	private void loadWallImage() {
		try {
			wallImg = ImageIO.read(new File("./filesResource/wall.gif"));
		} catch (IOException ex) {
			System.out.println("An exception occured while loading the wall image");
		}

		wallLbl = new JLabel(new ImageIcon(wallImg));
	}

	private void loadShadowImage() {
		try {
			shadowImg = ImageIO.read(new File("./filesResource/shadow.gif"));
		} catch (IOException ex) {
			System.out.println("An exception occured while loading the shadow image");
		}

		shadowLbl = new JLabel(new ImageIcon(shadowImg));
	}

	private void loadPathImage() {
		try {
			pathImg = ImageIO.read(new File("./filesResource/path.gif"));
		} catch (IOException ex) {
			System.out.println("An exception occured while loading the path image");
		}

		pathLbl = new JLabel(new ImageIcon(pathImg));
	}

	private void loadFinishImage() {
		try {
			finishImg = ImageIO.read(new File("./filesResource/finish.gif"));
		} catch (IOException ex) {
			System.out.println("An exception occured while loading the finish image");
		}

		finishLbl = new JLabel(new ImageIcon(finishImg));
	}

}
