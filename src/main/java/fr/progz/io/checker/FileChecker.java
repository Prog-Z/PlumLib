package fr.progz.io.checker;

import java.io.File;
import java.io.IOException;

import fr.progz.io.ConfigFiles;
/**
 * Manage config files
 * @author Meltwin
 * @version 1.0.0      10/04/2020
 */
public class FileChecker {
	
	private final File file;
	
	/**
	 * Create an instance of FileChecker on the given file 
	 * @param fil (String) : the file to look on
	 */
	public FileChecker(String fil) {file = ConfigFiles.getFileInConfigDir(fil);}
	/**
	 * Return if the file exist
	 * @return boolean : true if file exist, false else
	 */
	public boolean fileExist() {return file.exists();}
	
	/**
	 * Create the file
	 * @throws IOException if an IO error occured
	 */
	public void createFile() throws IOException {file.createNewFile();}
}
