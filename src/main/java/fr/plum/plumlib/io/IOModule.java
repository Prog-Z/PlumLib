package fr.plum.plumlib.io;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.arch.PlumModule;
import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.io.config.IOChatConfig;

public class IOModule extends PlumModule {

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return IOChatConfig.getInstance();
    }

    @Override
    public void init() {

    }
    
}
