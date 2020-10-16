package fr.plum.plumlib.io.yaml;

import javax.inject.Singleton;

import fr.plum.plumlib.io.config.PathConfig;

@Singleton
public final class DefaultMessageLoader extends MessageLoader {

    private DefaultMessageLoader(Class<?> c, String configDir, String outFile, String defaultFile) {
        super(DefaultMessageLoader.class, PathConfig.getPathToPlumFolder(), "messages", null);
    }
}