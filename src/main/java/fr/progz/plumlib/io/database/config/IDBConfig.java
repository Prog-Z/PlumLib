package fr.progz.plumlib.io.database.config;

import java.nio.file.FileSystems;

import fr.progz.plumlib.io.config.PathConfig;

public interface IDBConfig {

    /**
	 * The version of the database (int)
	 */
    int getDBVersion();
    /** Do request on db logged in console */
    boolean doDBLog();
    /** The name of the database file */
    String getDBName();
    /** The path of the subfolder (below plugins/plum/) */
    String getInnerDir();


    
    default String getDBFileName() {return String.format("%s.db",getDBName());}
    /**
	 * Get the paths to the database in a string format.
	 * @return the paths to the DB file (String).
	 */
    default String getDBPathString() {
        String formattedInDir = (getInnerDir().length()==0 || getInnerDir().substring(getInnerDir().length()-1).equals("/")) 
                ? getInnerDir() : String.format("%s/",getInnerDir());
        return String.format("%1$s%2$s%3$s",PathConfig.getPathToPlumFolder(),formattedInDir,getDBFileName());
    }
    default String getAbsoluteDBPathString() { return FileSystems.getDefault().getPath(getDBPathString()).toAbsolutePath().toString(); }
    default String getJDBCUrl() {return String.format("jdbc:sqlite:%s",getAbsoluteDBPathString());}
}