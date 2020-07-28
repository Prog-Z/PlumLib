package fr.progz.plumlib.arch;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.progz.plumlib.chat.IColoredMessage;
import fr.progz.plumlib.chat.IMessageType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
/**
 * Interface for module base command
 */
public interface IModuleChat extends IColoredMessage {

    // #####################################################
	// 
	// 					ANCHOR Module Properties
	// 
    // #####################################################

    byte TYPE_MODULE = 0;
    byte TYPE_PLUGIN = 1;
    byte TYPE_ERROR  = 2;
    
    String moduleName = "Module";
    ChatColor moduleBracket = ChatColor.GOLD;
    ChatColor moduleColor = ChatColor.RED;

    // GET

    default byte moduleType() {return TYPE_MODULE;}
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
        sendMsg(String.format("Init %1$s %2$s",getModuleType(moduleType()),getModuleName()));
    }
        
    // ANCHOR Console Properties
    /**
     * Send a message to the console with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     * @param prefix true if prefix is utilize
     */
    default void sendMsg(String msg, byte type, boolean prefix) {
        Bukkit.getConsoleSender().sendMessage(String.format("%1$s %2$s%3$s",(prefix) ? getPrefix()+" " : "", getMsgColor(type), msg));
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
        sendMsg(msg,IMessageType.DEFAULT,true);
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
     * @see IMessageType
     */
    default void sendMsg(Player p, String msg, byte type) {
        sendMsg(p,msg,type,true);
    }
    /**
     * Send a DEFAULT message to the given player
     * @param msg
     */
    default void sendMsg(Player p, String msg) {
        sendMsg(p,msg,IMessageType.DEFAULT,true);
    }

    // ANCHOR CommandSender
    /**
     * Send a message to the given player with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     * @param prefix true if prefix is utilize
     */
    default void sendMsg(CommandSender p, String msg, byte type, boolean prefix) {
        p.sendMessage(String.format("%1$s%2$s %3$s",(prefix) ? getPrefix()+" " : "",getMsgColor(type),msg));
    }
    /**
     * Send a message to the given player with the color associated to the type
     * @param msg
     * @param type (MessageType property)
     * @see IMessageType
     */
    default void sendMsg(CommandSender p, String msg, byte type) {
        sendMsg(p,msg,type,true);
    }
    /**
     * Send a DEFAULT message to the given player
     * @param msg
     */
    default void sendMsg(CommandSender p, String msg) {
        sendMsg(p,msg,IMessageType.DEFAULT,true);
    }
    // !SECTION
    // #####################################################
    // 
    //                  SECTION Methods
    // 
    // #####################################################
    default String getModuleType(byte type) {
        switch(type) {
            case TYPE_PLUGIN:
                return "plugin";
            case TYPE_ERROR:
                return "error";
            default:
                return "module";
        }
    }
    // !SECTION
}