package fr.progz.io.plum.v1;

import fr.progz.io.database.structure.DBColumn;
import fr.progz.io.database.structure.DBTable;
import fr.progz.io.database.structure.DBType;

public class T_Perms extends DBTable {
    public static String TABLE_NAME = "plum_perms";
    public static String COL_ID = "id";
    public static String COL_GROUP= "rank";
    public static String COL_PERM = "perm";

    public T_Perms() {
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

        DBColumn groupe = new DBColumn();
        groupe.setName(COL_GROUP);
        groupe.setType(new DBType(DBType.INTEGER));
        setColumn(1,groupe);

        DBColumn perm = new DBColumn();
        perm.setName(COL_PERM);
        perm.setType(new DBType(DBType.VARCHAR));
        setColumn(2,perm);
    }
    private String returnInsertRequest(int group, String perm) {
		return String.format("INSERT INTO %1$s(%4$s,%5$s) VALUES(%2$s,%3$s)", NAME,Integer.toString(group),perm,COL_GROUP,COL_PERM);
    }
    public void addLine(int group,String perm) {LINE_REQUESTS.add(returnInsertRequest(group,perm));}
}