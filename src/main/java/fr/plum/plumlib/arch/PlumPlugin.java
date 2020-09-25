package fr.plum.plumlib.arch;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.sender.IMessageSender;

/**
 * Create a plugin for spigot with automation for commands & event listener of PlumModule
 * @author Meltwin
 * @since 1.1.0
 */
public abstract class PlumPlugin extends JavaPlugin implements IMessageSender {

    ICmdHandler _commands;
    ArrayList<PlumModule> modules;

    /**
     * Create an instance of a spigot plugin with the plum sublayer
     */
    public PlumPlugin() {
        // Init Plugin
        super();

        modules = new ArrayList<>();
    }

    protected void addModule(@NotNull PlumModule module) {
        modules.add(module);
    }

    // #####################################################
    // SECTION Loading
    // #####################################################
    /**
     * Launch the loading of the modules
     */
    protected final void init() {
        sendInitMsg();
        loadModules();
    }

    final void loadModules() {
        for (PlumModule module : modules) {
            module.getChatConfig().setBrackColor(this.getBrackColor());

            module.init();
            loadModule_LISTENER(module);
        }
    }

    final void loadModule_LISTENER(final @NotNull PlumModule module) {
        for (Listener l : module.getListerner())
            Bukkit.getPluginManager().registerEvents(l, this);
    }
    // !SECTION

    @Override
    public final boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        // For Plugin
        if (_commands != null && _commands.command(sender, command, label, args)) 
            return true;
        
        // For Modules
        for (PlumModule mod : modules)
            if (mod.getCommandHandler() != null && mod.getCommandHandler().command(sender, command, label, args)) 
                return true;
        
        // Else Super
        return super.onCommand(sender, command, label, args);
    }
}