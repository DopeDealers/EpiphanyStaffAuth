package xyz.ambedo.phil.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import xyz.ambedo.phil.Registry;
import xyz.ambedo.phil.utils.C;

public class Authdrop
        implements Listener
{
    public Registry config;

    public Authdrop(Registry config) {
        this.config = config;
    }

    public void move(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
            if (this.config.authlocked.contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(C.Red + "Please enter the " + C.Aqua + "Google Auth Staff Code " + C.Red + "From the App");
            }
        }
    }
