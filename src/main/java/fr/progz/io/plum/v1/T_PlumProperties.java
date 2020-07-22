package fr.progz.io.plum.v1;

import fr.progz.io.database.structure.DBColumn;
import fr.progz.io.database.structure.DBTable;
import fr.progz.io.database.structure.DBType;
/**
 * DB Properties table
 * @author Meltwin
 * @version 1.0.0
 */
public class T_PlumProperties extends DBTable {
	
	public static String COL_KEY = "key";
	public static String COL_VALUE = "value";
	
	public T_PlumProperties() {
		super();
		
		NAME = "plum_properties";
		COLUMN_NUMBER = 2;
		setColumnNumber(COLUMN_NUMBER);
		
		// KEY
		DBColumn key = new DBColumn();
		key.setName(COL_KEY);
		key.setType(new DBType(DBType.VARCHAR,50));
		setColumn(0, key);
		
		// VALUE
		DBColumn value = new DBColumn();
		value.setName(COL_VALUE);
		value.setType(new DBType(DBType.VARCHAR,256));
		setColumn(1,value);
		
	}
	private String returnInsertRequest(String k, String v) {
		return String.format("INSERT INTO %1$s VALUES(\"%2$s\",\"%3$s\")", NAME,k,v);
	}
	public void addLine(String k,String v) {LINE_REQUESTS.add(returnInsertRequest(k,v));}
}
