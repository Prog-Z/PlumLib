package fr.plum.plumlib.io.config;
/**
 * Compiling default paths used by PlumLib
 * @author Meltwin
 * @since 1.1.0
 */
public abstract class PathConfig {
    public static String getPluginFolder() {return "plugins";}
    public static String getPlumFolder() {return "Plum";}

    public static String getPathToPlumFolder() {return String.format("./%1$s/%2$s/",getPluginFolder(),getPlumFolder());}
}