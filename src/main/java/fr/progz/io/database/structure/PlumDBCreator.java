package fr.progz.io.database.structure;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import fr.progz.io.ConfigFiles;
import fr.progz.io.IOMessage;
import fr.progz.io.database.R_UniversalRequester;
/**
 * Interface used to create a version of the Plum DB
 * @author Meltwin
 * @version 1.0.0
 */
abstract public class PlumDBCreator implements DBCreatorI {
	
	protected ArrayList<DBTable> TABLES = new ArrayList<>();

	@Override
	public void createDB() {
		Bukkit.getConsoleSender().sendMessage(IOMessage.createTable);
		createTables();
		insertLines();
		Bukkit.getConsoleSender().sendMessage(IOMessage.createTableE);
	}

	@Override
	public void createTables() {
		R_CreateTable req = new R_CreateTable(ConfigFiles.getAbsoluteDatabasePath());
		for (DBTable table : TABLES) {
			req.createTable(table);
		}
	}

	@Override
	public void insertLines() {
		R_UniversalRequester req = new R_UniversalRequester(ConfigFiles.getAbsoluteDatabasePath());
		for (DBTable table : TABLES) {
			for (String request : table.returnLineRequests()) {
				req.executeRequest(request);
			}
		}
	}
	
}
