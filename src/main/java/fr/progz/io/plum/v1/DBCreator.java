package fr.progz.io.plum.v1;

import fr.progz.io.ConfigFiles;
import fr.progz.io.database.structure.PlumDBCreator;

/**
 * Create v1.0 DB
 * @author Meltwin
 * @version 1.0.0
 */
public class DBCreator extends PlumDBCreator {	
	public DBCreator() {
		super();
		
		// SET Tables

		T_PlumProperties prop = new T_PlumProperties();
		prop.addLine("version", ConfigFiles.DATABASE_VERSION);
		TABLES.add(prop);
		
		T_Users users = new T_Users();
		TABLES.add(users);

		T_Perms perms = new T_Perms();
		TABLES.add(perms);

		T_Groups groups = new T_Groups();
		// ADD Base Groups
		groups.addLine("nogroup", -1);
		groups.addLine("default", 1);
		groups.addLine("admin", 2);

		TABLES.add(groups);
	}
}
