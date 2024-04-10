package io.papermc.deimoscm;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathBan extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doImmediateRespawn true");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        new PostDeath(event).runTaskLaterAsynchronously(this, 2);
    }
}
