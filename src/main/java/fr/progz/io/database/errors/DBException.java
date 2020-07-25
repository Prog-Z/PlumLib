package fr.progz.io.database.errors;

import org.bukkit.ChatColor;

import fr.progz.io.IOMessage;
import fr.progz.plumlib.arch.IModuleChat;
import fr.progz.plumlib.chat.IMessageType;

/**
 * Wrapper exception class for DB IO for Plum plugin
 * 
 * @author Meltwin
 * @version 1.0.0
 */
public class DBException extends Exception implements IModuleChat {

	// #####################################################
	//
	// 					SECTION Implements
	//
	// #####################################################
	@Override
	public String getModuleName() {
		return IOMessage.moduleName;
	}

	@Override
	public byte moduleType() {
		return IModuleChat.TYPE_ERROR;
	}
	
	@Override
	public ChatColor getBracketColor() {
		return IOMessage.moduleBracket;
	}

	@Override
	public ChatColor getColor() {
		return IOMessage.moduleColor;
	}
	// !SECTION

	private final int[] STACKTRACE_BLACKLIST = { 
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
	 * 
	 * @param cote (DBErrorType) : the error code
	 * @see DBErrorType
	 * @param exc (Exception) : the exception thrown by JDBC, can be null (no
	 *            stacktrace will be print)
	 */
	public DBException(int code, Exception exc) {
		EXCEPTIONCODE = code;
		e = exc;
		if (isError())
			printStackTrace();
	}

	@Override
	/**
	 * Print the stacktrace of the error in the cmd
	 */
	public void printStackTrace() {
		sendMsg("--- SQL Error ----");
		sendMsg(String.format("%1$s %2$d : %3$s", "Erreur : ", EXCEPTIONCODE, DBErrorType.E_MESSAGE[EXCEPTIONCODE]),
				IMessageType.ERROR, false);
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
		for (int code : STACKTRACE_BLACKLIST) {
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
	public int getExceptionCode() {
		return EXCEPTIONCODE;
	}
}