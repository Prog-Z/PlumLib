package fr.plum.plumlib.io.database.components.columns;
/**
 * Create an instance representating a database column
 * @author Meltwin
 * @since 1.1.0
 * 
 * @param <T> the class of the stored data (Integer, Float, String ...) used for default values
 */
public class DBColumn<T> {

    // SET
    private DBColType type;
    private String name;
    private int length = 0;
    private T defaultValue;
    private boolean autoIncrement = false;
    private boolean primaryKey = false;


    // #####################################################
    // SECTION Column Maker
    // #####################################################
    public DBColumn(DBColType type, String name) {
        this.type = type;
        this.name = name;
    }
    
    // ANCHOR Length
    public DBColumn<T> setLength(int length) {
        this.length = length;
        return this;
    }
    public DBColumn<T> setDefault(T deft) {
        this.defaultValue = deft;
        return this;
    }
    public DBColumn<T> setPrimary(boolean yes) {
        this.primaryKey = yes;
        return this;
    }
    public DBColumn<T> setAutoIncrement(boolean yes) {
        this.autoIncrement = yes;
        return this;
    }
    // !SECTION

    // #####################################################
    // SECTION Req Making
    // #####################################################
    public String makeRequest() {

        String output = String.format("`%1$s` %2$s",name,type.getType());
        output += (length>0) ? String.format("(%s) ",length) : " ";
        if (primaryKey) output += DBColOption.PRIMARY.get();
        if (autoIncrement) output += DBColOption.AUTO_INCREMENT.get();
        if (defaultValue != null) output += DBColOption.DEFAULT + String.format((defaultValue instanceof String) ? "\"%s\"" : "%s",defaultValue.toString()) ;

        return output;
    }
    // !SECTION
}