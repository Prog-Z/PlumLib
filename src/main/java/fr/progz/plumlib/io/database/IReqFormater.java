package fr.progz.plumlib.io.database;

public interface IReqFormater {
    
    public static String insertReq(String table_name, String[] columns, Object[] values) {
        boolean isFirst;
        // Init req
        String out = "INSERT INTO "+table_name+"(";

        // Add columns
        isFirst = true;
        for (String col : columns) {
            if (isFirst) {
                isFirst = false;
                out+=getColumnFormatted(col);
            }
            else 
                out+=","+getColumnFormatted(col);
        }

        // Add values
        out += ") VALUES(";
        isFirst = true;
        for (Object val : values) {
            if (isFirst) {
                isFirst = false;
                out+=getValueFormatted(val);
            }
            else 
                out+=","+getValueFormatted(val);
        }
        return out+")";
    }
    static String replaceReq(String table_name, String[] columns, Object[] values) {
        boolean isFirst;
        // Init req
        String out = "REPLACE INTO "+table_name+"(";

        // Add columns
        isFirst = true;
        for (String col : columns) {
            if (isFirst) {
                isFirst = false;
                out+=getColumnFormatted(col);
            }
            else 
                out+=","+getColumnFormatted(col);
        }

        // Add values
        out += ") VALUES(";
        isFirst = true;
        for (Object val : values) {
            if (isFirst) {
                isFirst = false;
                out+=getValueFormatted(val);
            }
            else 
                out+=","+getValueFormatted(val);
        }
        return out+")";
    }

    static String getValueFormatted(Object val) {
        if (val instanceof String) return "\""+((String) val)+"\"";
        return val.toString();
    }
    static String getColumnFormatted(String col) {
        return "`"+col+"`";
    }
}