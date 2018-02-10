package xyz.ambedo.phil.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import xyz.ambedo.phil.Registry;
import xyz.ambedo.phil.utils.C;

public class Authtp
implements Listener {

    public Registry config;

    public Authtp(Registry config) {
        this.config = config;
    }


    @EventHandler
    public void tp(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
            if (this.config.authlocked.contains(player.getUniqueId())) {
                if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
                    event.setCancelled(true);
                    event.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ENDER_PEARL, 1)
                    });
                    player.sendMessage(C.DGray + "[" + C.DRed + C.Bold + "EpiphanyMC-StaffAuth" + C.DGray + "]"
                            + C.Red + " You may not teleport before you enter your code");
                }
            }
        }
    }
