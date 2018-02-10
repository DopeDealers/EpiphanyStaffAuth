package xyz.ambedo.phil.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.ambedo.phil.Registry;
import xyz.ambedo.phil.utils.C;

public class Authplace
implements Listener
{
    public Registry config;

    public Authplace(Registry config) {
        this.config = config;
    }

    public void place(PlayerMoveEvent event) {
        Player player = event.getPlayer();
            if (this.config.authlocked.contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(C.Red + "Please enter the " + C.Aqua + "Google Auth Staff Code " + C.Red + "From the App");
            }
        }
    }
