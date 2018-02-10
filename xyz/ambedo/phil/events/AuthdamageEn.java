package xyz.ambedo.phil.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.ambedo.phil.Registry;
import xyz.ambedo.phil.utils.C;

public class AuthdamageEn
implements Listener {

    public Registry config;

    public AuthdamageEn(Registry config) {
        this.config = config;
    }

    public void damage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (this.config.authlocked.contains(damager.getUniqueId().toString())) {
            event.setCancelled(true);
            damager.sendMessage(C.Red + "You may not hit this Staff Member at this time");
        }
    }
}
