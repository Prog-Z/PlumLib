package fr.progz.plumio;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.apache.commons.codec.digest.DigestUtils;

public class PlumIO extends JavaPlugin {

    @Override
    public void onEnable() {
		String s = DigestUtils.sha512Hex("data");
    }

}
