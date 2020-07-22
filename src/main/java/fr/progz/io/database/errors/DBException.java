package fr.progz.io.database.errors;

import fr.progz.kernel.Main;
import fr.progz.kernel.config.PlumConfig;
import net.md_5.bungee.api.ChatColor;
/**
 * Wrapper exception class for DB IO for Plum plugin
 * @author Meltwin
 * @version 1.0.0
 */
public class DBException extends Exception {
	
	private final int[] STACKTRACE_BLACKLIST  = {
													DBErrorType.DB_CLOSED_SUCCESS,
													DBErrorType.DB_FOUND,
													DBErrorType.REQ_SUCCESS,
													DBErrorType.RS_SUCCESS,
													DBErrorType.RS_EMPTY
												};

	private static final long serialVersionUID = 1L;
	private final int EXCEPTIONCODE;
	private Exception e;
	
	/**
	 * Create an exception with a code and the original exception 
	 * @param cote (DBErrorType) : the error code 
	 * @see DBErrorType
	 * @param exc (Exception) : the exception thrown by JDBC, can be null (no stacktrace will be print)
	 */
	public DBException(int code, Exception exc) {
		EXCEPTIONCODE = code;
		e = exc;
		if (isError()) printStackTrace();
	}
	@Override
	/**
	 * Print the stacktrace of the error in the cmd
	 */
	public void printStackTrace() {
		Main.sMCMD(PlumConfig.pluginPrefixError+"--- SQL Error ----");
		Main.sMCMD(ChatColor.DARK_RED+String.format("%1$s %2$d : %3$s","Erreur : ",EXCEPTIONCODE,DBErrorType.E_MESSAGE[EXCEPTIONCODE]));
		if (e != null) {
			e.printStackTrace();
		}
	}
	/**
	 * Check if the error is bad or good
	 * @return true if error is good, false if it's a bad error
	 */
	private boolean isErrorCodeBlacklisted() {
		boolean in = false;
		for(int code : STACKTRACE_BLACKLIST) {
			in = in || (code == EXCEPTIONCODE);
		}
		return in;
	}
	/**
	 * Say if the error should be reported (bad error)
	 * @return if the error is bad
	 */
	public boolean isError() {
		return !isErrorCodeBlacklisted();
	}
	/**
	 * Return the DBErroType exception code
	 * @return the code (int)
	 */
	public int getExceptionCode() {return EXCEPTIONCODE;}
}