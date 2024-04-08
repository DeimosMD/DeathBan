package io.papermc.deimoscm;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DeathBan extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
        Bukkit.getServer().broadcastMessage(
                event.getPlayer().getName()+" died at "+event.getPlayer().getLocation().getBlockX()+" "
                        +event.getPlayer().getLocation().getBlockY()+" "+event.getPlayer().getLocation().getBlockZ()
                        +" in "+getDimension(event)+", and has been banned for 24 hours!"
        );
        event.getPlayer().ban("Death-Banned! Come back tomorrow.", Duration.ofHours(24), "DeathBan", true);
    }

    private static @NotNull String getDimension(PlayerDeathEvent event) {
        String dimension = "an unknown dimension";
        if (event.getPlayer().getLocation().getWorld().getEnvironment() == World.Environment.NORMAL) {
            dimension = "the overworld";
        } else if (event.getPlayer().getLocation().getWorld().getEnvironment() == World.Environment.NETHER) {
            dimension = "the nether";
        } else if (event.getPlayer().getLocation().getWorld().getEnvironment() == World.Environment.THE_END) {
            dimension = "the end";
        }
        return dimension;
    }
}
