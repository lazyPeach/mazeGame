package model;

import java.io.*;

/**
 * GetPath class is used for finding the absolute path to the project.
 * This is useful for application portability.
 * It creates a instance of File and uses the built-in method getAbsolutePath() which will return the path to the project.
 * @author Lorenzo-Eusebio Patras
 *
 */
//class for getting the path to the current folder
public class GetPath{
	private File file;
	/**
	 * Constructor of GetPath in which the file is created.
	 */
	public GetPath(){
		file = new File("");
	}
	/**
	 * Method which calls the built-in method in File: getAbsolutePath().
	 * @return The absolute path to the project
	 */
	public String getPath(){
		return file.getAbsolutePath();
	}
}  