package xyz.ambedo.phil.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.ambedo.phil.Registry;

public class Authmobhit
implements Listener {

    public Registry config;

    public Authmobhit(Registry config) {
        this.config = config;
    }

    public void endamage(EntityDamageEvent event) {
        Entity damager2 = event.getEntity();
            if (this.config.authlocked.contains(damager2.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }