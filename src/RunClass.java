import model.*;
import view.*;
import controller.*;

/**
 * The Run class which contains the main() method for assembling the three main classes of the project: Model, View, Controller.
 * Each of these three classes are in a separate package together with their specific classes.  
 * @author Lorenzo-Eusebio Patras
 *
 */

public class RunClass {
	public static void main(String[] args){
        Model model = new Model();
        View view = new View(model);
        Controller cont = new Controller(view, model);
	}
}
