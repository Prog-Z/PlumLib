package fr.progz.io.config;

public abstract class PathConfig {
    public static String getPluginFolder() {return "plugins";}
    public static String getPlumFolder() {return "Plum";}

    public static String getPathToPlumFolder() {return String.format("./%1$s/%2$s/",getPluginFolder(),getPlumFolder());}
}