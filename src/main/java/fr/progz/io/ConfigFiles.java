package fr.progz.io;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
/**
 * Properties set for all the plugin
 * @author Meltwin
 * @version 1.0.0
 */
public abstract class ConfigFiles {
	
	private final static String PLUGIN_PATH = "plugins/";
	private final static String CONFIG_DIRECTORY = "Plum/";
	
	public static String getPluginDirPath() {return PLUGIN_PATH;}
	public static String getConfigPathString() {return String.format("%1$s%2$s",PLUGIN_PATH,CONFIG_DIRECTORY);}
	public static File getConfigDir() {return new File(getConfigPathString());}
	public static Path getConfirDirPath() {return FileSystems.getDefault().getPath(getConfigPathString());}
	
	/**
	 * Return the string path of the file
	 * @param file (String) : the name of the file
	 * @return String : the paths
	 */
	public static String getFilePathInConfigDir(String file) {return String.format("%1$s%2$s%3$s",PLUGIN_PATH,CONFIG_DIRECTORY,file);}
	/**
	 * Return a File instance of the file
	 * @param file (String) : the name of the file
	 * @return File : the paths 
	 */
	public static File getFileInConfigDir(String file) {return new File(getFilePathInConfigDir(file));}
	/*
	 * ###########################################################
	 * 								DB
	 * ###########################################################
	 */
	/**
	 * The version of the database (int)
	 */
	public final static String DATABASE_VERSION = "1";
	/** The name of the database file */
	public final static String DATABASE_FILE = "database.db";
	/** Is request on db logged in console */
	public final static boolean DB_REQ_LOG = true;
	/**
	 * Get the paths to the database in a string format.
	 * @return the paths to the DB file (String).
	 */
	public static String getDatabasePathString() {return getFilePathInConfigDir(DATABASE_FILE);}
	/**
	 * Get the absolute paths to the database in a string format.
	 * @return the absolute paths to the DB file (String).
	 */
	public static String getAbsoluteDatabasePath() {return FileSystems.getDefault().getPath(getDatabasePathString()).toAbsolutePath().toString();}
	/**
	 * Get a File instance of the DB.
	 * @return a File instance corresponding to the DB file.
	 */
	public static File getDatabaseFile() {return new File(getDatabasePathString());}
}
