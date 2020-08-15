package fr.progz.io.database.config;

import org.bukkit.ChatColor;

public enum DBMessageType {
    NULL("","",ChatColor.WHITE,false),
    PREPARE("   >> ","Prepare request ",ChatColor.YELLOW,true),
    EXECUTE("   >> ","Execute request ",ChatColor.GREEN,true),
    INFO("","",ChatColor.DARK_PURPLE,false);

    private String prePrefix;
    private String prefix;
    private ChatColor color;
    private boolean resetColor;
    private DBMessageType(String prePrefix, String prefix, ChatColor color, boolean resetColor) {
        this.prePrefix = prePrefix;
        this.prefix = prefix;
        this.color = color;
        this.resetColor = resetColor;
    }
    public String get() {return String.format("%1$s%2$s%3$s%4$s%5$s",ChatColor.WHITE.toString(),prePrefix,color,prefix,(resetColor) ? ChatColor.GRAY.toString() : "");}
}