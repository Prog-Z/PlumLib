package fr.progz.io.plum.v1;

import fr.progz.io.database.structure.DBColumn;
import fr.progz.io.database.structure.DBTable;
import fr.progz.io.database.structure.DBType;
/**
 * Groups table
 * @author Meltwin
 * @version 1.0.0
 */
public class T_Groups extends DBTable {
    public static String TABLE_NAME = "plum_groups";
    public static String COL_ID = "id";
    public static String COL_NAME = "name";
    public static String COL_INHERIT = "inherit";

    public T_Groups() {
        super();

        // SET Global Props
        NAME = TABLE_NAME;
        COLUMN_NUMBER = 3;
        this.setColumnNumber(COLUMN_NUMBER);

        // SET Columns
        DBColumn id = new DBColumn();
        id.setName(COL_ID);
        id.setType(new DBType(DBType.INTEGER));
        id.setAutoIncrement(true);
        id.setPrimaryKey(true);
        setColumn(0,id);

        DBColumn name = new DBColumn();
        name.setName(COL_NAME);
        name.setType(new DBType(DBType.VARCHAR));
        setColumn(1,name);

        DBColumn inherit = new DBColumn();
        inherit.setName(COL_INHERIT);
        inherit.setType(new DBType(DBType.INTEGER));
        setColumn(2,inherit);
    }
    private String returnInsertRequest(String name, int  inherit) {
		return String.format("INSERT INTO %1$s(%4$s,%5$s) VALUES(\"%2$s\",%3$s)", TABLE_NAME,name,Integer.toString(inherit),COL_NAME,COL_INHERIT);
    }
    public void addLine(String name,int inherit) {LINE_REQUESTS.add(returnInsertRequest(name,inherit));}
}