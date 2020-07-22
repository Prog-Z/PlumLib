package fr.progz.io.checker;

import java.io.IOException;
import java.nio.file.Files;

import fr.progz.io.ConfigFiles;

/**
 * Manage config files
 * @author Meltwin
 * @version 1.0.0      10/04/2020
 */
public class ConfigDirChecker {	
	/**
	 * Create an instance of ConfigDirChecker on the given file 
	 * @param fil (String) : the file to look on
	 */
	public ConfigDirChecker() throws IOException {
		if (!fileExist()) {
			createFolder();
		}
	}
	/**
	 * Return if the config directory exist
	 * @return boolean : true if dir exist, false else
	 */
	public boolean fileExist() {return ConfigFiles.getConfigDir().exists();}
	/**
	 * Create the config directory folder
	 * @throws IOException if an IO error occured
	 */
	private void createFolder() throws IOException {
		Files.createDirectory(ConfigFiles.getConfirDirPath());
	}
}

