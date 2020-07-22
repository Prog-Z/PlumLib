package fr.progz.io.database.errors;

public class DBErrorType {
	public static int DB_FOUND = 0;
	public static int DB_NOT_FOUND = 1;
	public static int DB_INVALID_ADRESS = 2;
	public static int DB_CLOSED_SUCCESS = 3;
	public static int DB_TIMEOUT = 4;
	public static int REQ_INVALID = 5;
	public static int REQ_SUCCESS = 6;
	public static int RS_CLOSED_OR_DB_ACCESS_ERROR = 7;
	public static int RS_INVALID_COLUMN = 8;
	public static int RS_SUCCESS = 9;
	public static int RS_EMPTY = 10;
	
	
	public static String[] E_MESSAGE = {
										"Database found successfuly.",
										"Database not found.",
										"Database adress is invalid.",
										"Database closed successfuly.",
										"Database has timed out.",
									    "Request Invalid.",
									    "Request successful.",
									    "Result set closed or database access error.",
										"Column name is invalid !",
										"Resultset read successfully."
									  };
}
