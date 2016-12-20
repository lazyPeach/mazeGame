package controller;
import view.*;
import model.*;

/**
 * Controller class which links the View and the Model and implements the functionality of the application by creating the 
 * neccessary listeners.
 */
public class Controller {
	/**
	 * Constructor of Controller class. When creating the controller we also add listeners to all instances of View.
	 * @param view - instance of View class
	 */
	public Controller(View view, Model model){
        new StartController(view, model);
        new PlayController(view, view.getPlayPanel(), model.getMaze());
	}
}
