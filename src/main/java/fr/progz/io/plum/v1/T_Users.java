package fr.progz.io.plum.v1;

import fr.progz.io.database.structure.DBColumn;
import fr.progz.io.database.structure.DBTable;
import fr.progz.io.database.structure.DBType;
/**
 * User table
 * @author Meltwin
 * @version 1.0.0
 */
public class T_Users extends DBTable {
	public static String TABLE_NAME = "plum_users";
	public static String COL_ID = "id";
	public static String COL_UUID = "uuid";
	public static String COL_PASSWORD = "password";
	public static String COL_GROUP = "group";
		
	public T_Users() {
		super();
		NAME = TABLE_NAME;
		COLUMN_NUMBER = 4;
		
		this.setColumnNumber(COLUMN_NUMBER);
		
		DBColumn id = new DBColumn();
		id.setName(COL_ID);
		id.setType(new DBType(DBType.INTEGER));
		id.setAutoIncrement(true);
		id.setPrimaryKey(true);
		setColumn(0,id);
		
		DBColumn uuid = new DBColumn();
		uuid.setName(COL_UUID);
		uuid.setType(new DBType(DBType.VARCHAR,100));
		setColumn(1,uuid);
		
		DBColumn password = new DBColumn();
		password.setName(COL_PASSWORD);
		password.setType(new DBType(DBType.VARCHAR,512));
		setColumn(2,password);

		DBColumn group = new DBColumn();
		group.setType(new DBType(DBType.INTEGER));
		group.setName(COL_GROUP);
		group.setDefaultValue(2);
		setColumn(3,group);
		
	}
	private String returnInsertRequest(String uuid, String password) {
		return String.format("INSERT INTO %1$s VALUES(%2$s,%3$s)", NAME,uuid,password);
	}
	public void addLine(String uuid,String password) {LINE_REQUESTS.add(returnInsertRequest(uuid,password));}
}
