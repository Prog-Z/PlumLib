package fr.progz.plumlib.arch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.progz.plumlib.chat.ColoredMessage;
import fr.progz.plumlib.chat.MessageType;
import org.bukkit.ChatColor;
/**
 * Interface for module base command
 */
public interface ModuleConfig extends ColoredMessage {

    // #####################################################
	// 
	// 					ANCHOR Module Properties
	// 
    // #####################################################
    
    boolean isModule = true;
    String moduleName = "Module";
    ChatColor moduleBracket = ChatColor.GOLD;
    ChatColor moduleColor = ChatColor.RED;

    // GET

    default boolean isModule() {return isModule;}
    default String getModuleName() {return moduleName;}
    default ChatColor getColor() {return moduleColor;}
    default ChatColor getBracketColor() {return moduleBracket;}
    default String getPrefix() {return String.format("%1$s[%2$s%3$s%1$s]",getBracketColor(),getColor(),getModuleName());}


    // #####################################################
    // 
    //                  SECTION Messages
    // 
    // #####################################################

    // ANCHOR INFO Message
    /**Send a init message to the console */
    default void sendInitMsg() {
        sendMsg(String.format("Init %1$s %2$s",(isModule())? "module" : "plugin",getModuleName()));
    }
        
    // ANCHOR Console Properties
    /**
     * Send a message to the console with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     * @param prefix true if prefix is utilize
     */
    default void sendMsg(String msg, byte type, boolean prefix) {
        Bukkit.getConsoleSender().sendMessage(String.format("%1$s %2$s",(prefix) ? getPrefix()+" " : "", getMsgColor(type), msg));
    }
    /**
     * Send a message to the console with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     */
    default void sendMsg(String msg, byte type) {
        sendMsg(msg,type,true);
    }
    /**
     * Send a DEFAULT message in the console
     */
    default void sendMsg(String msg) {
        sendMsg(msg,MessageType.DEFAULT,true);
    }


    // ANCHOR Player
    /**
     * Send a message to the given player with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     * @param prefix true if prefix is utilize
     */
    default void sendMsg(Player p, String msg, byte type, boolean prefix) {
        p.sendMessage(String.format("%1$s%2$s %3$s",(prefix) ? getPrefix()+" " : "",getMsgColor(type),msg));
    }
    /**
     * Send a message to the given player with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     */
    default void sendMsg(Player p, String msg, byte type) {
        sendMsg(p,msg,type,true);
    }
    /**
     * Send a DEFAULT message to the given player
     * @param msg
     */
    default void sendMsg(Player p, String msg) {
        sendMsg(p,msg,MessageType.DEFAULT,true);
    }
    // !SECTION
}