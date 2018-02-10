package xyz.ambedo.phil.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import xyz.ambedo.phil.Registry;

public class Authblockdamage
    implements Listener
{
    public Registry config;

    public Authblockdamage(Registry config)
    {
        this.config = config;
    }

    public void blockdamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
            if (this.config.authlocked.contains(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }
