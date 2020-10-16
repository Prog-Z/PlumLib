package fr.plum.plumlib;


import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.arch.PlumPlugin;
import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.io.IOModule;
/**
 * Plugin master class (init modules essentialy)
 * @author Meltwin
 * @since 1.1.0
 */
public class PLib extends PlumPlugin {

    final static IOModule IO = new IOModule();

    @Override
    public void onEnable() {
        super.onEnable();
        this.addModule(IO);
    }

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return PLibConfig.getInstance();
    }
}
