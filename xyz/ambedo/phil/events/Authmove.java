package xyz.ambedo.phil.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.ambedo.phil.Registry;
import xyz.ambedo.phil.utils.C;

public class Authmove
implements Listener {
    public Registry config;


    public Authmove(Registry config) {
        this.config = config;
    }

       @EventHandler
       public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.config.authlocked.contains(player.getUniqueId())) {
            event.getPlayer().teleport(event.getPlayer());
            player.sendMessage(C.DGray + "[" + C.DRed + C.Bold + "EpiphanyMC-StaffAuth" + C.DGray + "]"
                    + C.Red + " You may not move before you enter your code");
        }
    }
}