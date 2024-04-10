package io.papermc.deimoscm;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Objects;

public class PostDeath extends BukkitRunnable {

    final PlayerDeathEvent event;

    PostDeath(PlayerDeathEvent event) {
        this.event = event;
    }

    public void run() {
        event.getPlayer().getWorld().strikeLightningEffect(Objects.requireNonNull(event.getPlayer().getLastDeathLocation()));
        Bukkit.getServer().broadcastMessage(
                event.getPlayer().getName()+" died at "+event.getPlayer().getLastDeathLocation().getBlockX()+" "
                        +event.getPlayer().getLastDeathLocation().getBlockY()+" "+event.getPlayer().getLastDeathLocation().getBlockZ()
                        +" in "+getDimension(event)+", and has been banned for 24 hours!"
        );
        event.getPlayer().ban("Death-Banned! Come back tomorrow.", Duration.ofHours(24), "DeathBan", true);
    }

    private static @NotNull String getDimension(PlayerDeathEvent event) {
        String dimension = "an unknown dimension";
        if (Objects.requireNonNull(event.getPlayer().getLastDeathLocation()).getWorld().getEnvironment() == World.Environment.NORMAL) {
            dimension = "the overworld";
        } else if (event.getPlayer().getLastDeathLocation().getWorld().getEnvironment() == World.Environment.NETHER) {
            dimension = "the nether";
        } else if (event.getPlayer().getLastDeathLocation().getWorld().getEnvironment() == World.Environment.THE_END) {
            dimension = "the end";
        }
        return dimension;
    }
}
