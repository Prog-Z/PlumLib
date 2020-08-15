package fr.progz.io;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.progz.io.checker.ConfigDirChecker;
import fr.progz.io.checker.FileChecker;
import fr.progz.plumlib.arch.IModuleChat;
/**
 *  Plugin class for IO Lib
 * @author Meltwin
 * @version 1.0.0
 */
public class Main extends JavaPlugin implements IModuleChat {

	@Override
	public void onEnable() {
		super.onEnable();
		//checkFiles();

	}
	/**
	 * Check if all the config files are here
	 */
	public void checkFiles() {
		// ./plugins/ ConfigFiles::CONFIG_DIRECTORY
		try {new ConfigDirChecker();} catch (IOException e) {e.printStackTrace();disablePlugin();}	
		
		// ./plugins/Plum/ ConfigFiles::DATABASE_FILE
		this.checkDB();
		
	}
	/**
	 * Check if the db is ready to be use
	 */
	private void checkDB() {
		FileChecker dbFile = new FileChecker(ConfigFiles.DATABASE_FILE);
		if (!dbFile.fileExist()) {
			try {
				dbFile.createFile(); // Create the database
				while (!dbFile.fileExist()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//this.checkDB();
			} catch (IOException e) {e.printStackTrace();disablePlugin();} 
		}
	}
	/**
	 * Disable the plugin on any fatal error encountered
	 */
	public void disablePlugin() {Bukkit.getPluginManager().disablePlugin(this);}
}
