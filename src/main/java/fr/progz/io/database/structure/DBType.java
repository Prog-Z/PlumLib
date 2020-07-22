package fr.progz.io.database.structure;
/**
 * Make a type for a column in SQL
 * @author Meltwin
 *
 */
public class DBType {
	public static final String INTEGER = "INTEGER";
	public static final String VARCHAR = "VARCHAR";
	
	private String TYPE = "";
	private int SIZE=0;
	
	private String[] SIZED = {DBType.VARCHAR};
	
	public void setType(String t) {TYPE=t;}
	public String getType() {return TYPE;}
	public void setTypeSize(int s) {SIZE = s;}
	public int getTypeSize() {return SIZE;}
	
	public String getFormatedType() {
		return (isSized()) ? String.format("%1$s(%2$d)", TYPE,SIZE): TYPE;
	}
	public boolean isSized() {
		boolean sized = false;
		for(String type : SIZED) {
			sized = sized || type.equals(TYPE);
		}
		return sized;
	}
	
	
	public DBType(String type) {TYPE=type;}
	public DBType(String type, int size) {TYPE=type;SIZE=size;}
	
}
