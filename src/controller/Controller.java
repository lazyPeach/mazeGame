package controller;
import view.*;
import model.*;
import java.awt.event.*;

/**
 * Controller class which links the View and the Model and implements the functionality of the application by creating the 
 * neccessary listeners.
 * @author Lorenzo-Eusebio Patras
 *
 */
public class Controller {
	private View view;
	private Model model;

	/**
	 * Constructor of Controller class. When creating the controller we also add listeners to all instances of View.
	 * @param view - instance of View class
	 */
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;

        StartController startController = new StartController(view, model);
        PlayController playController = new PlayController(view, view.getPlayPanel(), model.getMaze());
	}
}
