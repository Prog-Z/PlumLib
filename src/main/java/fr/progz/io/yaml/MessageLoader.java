package fr.progz.io.yaml;

import javax.inject.Singleton;
import javax.persistence.Inheritance;

import org.jetbrains.annotations.Nullable;

@Singleton
@Inheritance
public abstract class MessageLoader extends ConfigFile {

    protected static MessageLoader loader;

    /**
     * @param c the getClass() of the calling class
     * @param dir the path of the config dir of the plugin (from the dir of the server.jar)
     * @param name the path of the out file without the extension (ex "test" will resut in ./configDir/innerDir/test.yml)
     * @param defaultFile the path of the default file within the jar 'ressources/defaultFile' (without the extension)
     */
    public MessageLoader(Class<?> c, String configDir, String outFile, String defaultFile) {
        super(c, configDir, outFile, defaultFile);
        loader = this;
    }

    @Nullable
    public static MessageLoader getInstance() {
        return loader;
    }

    @Nullable
    public static String getMessage(String path) {
        return loader.getConfig().getString(path);
    }
    
}