package fr.progz.io.database.structure;

/**
 * A standart column for making a column for a table in plum DB
 * @author Meltwin
 * @version 1.0.0
 */
public class DBColumn {
	
	// Constants
	public static String AUTO_INC_S = "AUTOINCREMENT";
	public static String PRIMARY_K_S = "PRIMARY KEY";

	private String DEFAULT_STRING;
	private int DEFAULT_INT;
	
	// Column properties
	private String NAME = "";
	private DBType TYPE;
	private boolean PRIMARY_K = false;
	private boolean AUTO_INC = false;
	private boolean HAS_DEFAULT = false;
	
	/** 
	 * Set the name of the column
	 * @param n : the name
	 */
	public void setName(String n) {NAME=n;}
	/**
	 * Get the name of this column
	 * @return the name of the column
	 */
	public String getName() {return NAME;}
	
	/**
	 * Set the DBType to the column 
	 * @param t : the type
	 */
	public void setType(DBType t) {TYPE=t;}
	/**
	 * Get the type of the column
	 * @return the type of the column
	 */
	public DBType getType() {return TYPE;}
	
	/**
	 * Set whether this column is a primary key or not
	 * @param f true : make the column primary, false not 
	 */
	public void setPrimaryKey(boolean f) {PRIMARY_K=f;}
	/**
	 * Is the column a primary key ?
	 */
	public boolean isPrimaryKey() {return PRIMARY_K;}
	
	/**
	 * Set whether this column is auto incrementing or not
	 * @param f true : make the column auto increment, false not 
	 */
	public void setAutoIncrement(boolean f) {AUTO_INC=f;}
	/**
	 * Is the column auto incrementing ?
	 */
	public boolean isAutoIncrementing() {return AUTO_INC;}
	
	public void setDefaultValue(String def) {DEFAULT_STRING=def;HAS_DEFAULT=true;}
	public void setDefaultValue(int def) {DEFAULT_INT=def;HAS_DEFAULT=true;}

	/**
	 * Get the string ready to be used in sql request
	 * @return the part of sql request
	 */
	public String getFormatedColumn() {
		String output = String.format("`%1$s` %2$s", NAME,TYPE.getFormatedType());
		if (PRIMARY_K) output+=" "+DBColumn.PRIMARY_K_S;
		if (AUTO_INC) output+=" "+DBColumn.AUTO_INC_S;
		if (HAS_DEFAULT) {
			output+=" DEFAULT ";
			output+= (DEFAULT_STRING==null) ? DEFAULT_INT : DEFAULT_STRING;
		}
		return output;
	}
	
}
