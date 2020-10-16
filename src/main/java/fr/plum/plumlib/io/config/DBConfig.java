package fr.plum.plumlib.io.config;
/**
 * Database Config
 * @author Meltwin
 * @since 1.1.0
 */
public interface DBConfig {
    /** The version of the database */
    default int getDBVersion() {return 1;}
    /** Do request on db are logged in console */
    default boolean doDBLog() {return true;}
    /** The name of the database file without folder & ".db"*/
    String getDBName();

    default String getDBFileName() {return String.format("%s.db",getDBName());}

    /**
	 * Get the paths to the database in a string format.
	 * @return the paths to the DB file (String).
	 */
    default String getDBPathString(String innerDir) {
        String formattedInDir = (innerDir.length()==0 || innerDir.substring(innerDir.length()-1).equals("/")) ? innerDir : String.format("%s/",innerDir);
        return String.format("%1$s%2$s%3$s",PathConfig.getPathToPlumFolder(),formattedInDir,getDBFileName());
    }
}