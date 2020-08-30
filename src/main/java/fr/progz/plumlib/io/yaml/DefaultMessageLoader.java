package fr.progz.plumlib.io.yaml;

import javax.inject.Singleton;

import fr.progz.plumlib.io.config.PathConfig;

@Singleton
public class DefaultMessageLoader extends MessageLoader {

    private DefaultMessageLoader(Class<?> c, String configDir, String outFile, String defaultFile) {
        super(DefaultMessageLoader.class, PathConfig.getPathToPlumFolder(), "messages", null);
    }
}