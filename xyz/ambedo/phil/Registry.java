package xyz.ambedo.phil;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.ambedo.phil.events.*;
import xyz.ambedo.phil.utils.C;
import xyz.ambedo.phil.utils.License;

import java.util.ArrayList;
import java.util.UUID;

public class Registry
extends JavaPlugin
implements Listener {
    /*
       Created by AmbedoServices CEO Phil K.
     */
    public ArrayList<UUID> authlocked;
    public static Registry plugin;

    public License license;


    public static Registry getPlugin() {
        return Registry.plugin;
    }

    static {
        Registry.plugin = null;
    }


    public void onEnable() {


        Registry.plugin = this;
        authlocked = new ArrayList<UUID>();
        this.license = new License();
        registerListeners();

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.saveDefaultConfig();


        if (!this.license.accepted()) {
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("----------------------------------");
            System.out.println("");
            System.out.println("       EpiphanyStaffAuth");
            System.out.println("      Enable the plugin in");
            System.out.println("");
            System.out.println("File: [enable.txt]");
            System.out.println("");
            System.out.println("Version: 1.0.0");
            System.out.println("");
            System.out.println("----------------------------------");
            System.out.println("");
        }
    }


    @Override
    public void onDisable() {


    }

    public void registerListeners() {


        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new Authmove(this), this);
        this.getServer().getPluginManager().registerEvents(new Authmobhit(this), this);
        this.getServer().getPluginManager().registerEvents(new Authblockdamage(this), this);
        this.getServer().getPluginManager().registerEvents(new Authbreak(this), this);
        this.getServer().getPluginManager().registerEvents(new Authtp(this), this);
        this.getServer().getPluginManager().registerEvents(new Authplace(this), this);
        this.getServer().getPluginManager().registerEvents(new AuthdamageEn(this), this);
        this.getServer().getPluginManager().registerEvents(new Authdrop(this), this);

        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("");
        System.out.println("       EpiphanyStaffAuth");
        System.out.println("      Listeners !~Loaded~!");
        System.out.println("");
        System.out.println("Version: 1.0.0");
        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("");

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

            if (player.hasPermission("epiphany.staff") && !this.getConfig().contains("authcodes." + player.getUniqueId())) {
                GoogleAuthenticator gAuth = new GoogleAuthenticator();
                GoogleAuthenticatorKey key = gAuth.createCredentials();

                player.sendMessage(C.Red + "You need to enter this code on the Google Auth Application before leaving the server");

                this.getConfig().set("authcodes." + player.getUniqueId(), key.getKey());
                this.saveConfig();
            } else if (player.hasPermission("epiphany.staff")) {
                authlocked.add(player.getUniqueId());
                player.sendMessage(C.Red + "Please open the Google Authenticator App and provide the six digit code.");
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 5));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2000));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 5));
            }
        }

        private boolean playerInputCode (Player player,int code){
            String secretkey = this.getConfig().getString("authcodes." + player.getUniqueId());

            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            boolean codeisvalid = gAuth.authorize(secretkey, code);


            if (codeisvalid) {
                authlocked.remove(player.getUniqueId());
                return codeisvalid;
            }

            return codeisvalid;
        }


        @EventHandler
        public void chat (AsyncPlayerChatEvent event){
            Player player = event.getPlayer();
            String message = event.getMessage();

            if (authlocked.contains(player.getUniqueId())) {
                try {
                    Integer code = Integer.parseInt(message);
                    if (playerInputCode(player, code)) {
                        authlocked.remove(player.getUniqueId());
                        event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
                        event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
                        event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
                        player.sendMessage(C.DGray + "[" + C.DRed + C.Bold + "EpiphanyMC-StaffAuth" + C.DGray + "]"
                                + C.Green + " Access Granted");
                    } else {
                        player.sendMessage(C.Red + "Invalid or Expired Code");

                    }
                } catch (Exception e) {
                    player.sendMessage(C.Red + "Invalid or Expired Code");
                }
                event.setCancelled(true);
            }
        }
        public String color (String msg){
            return ChatColor.translateAlternateColorCodes('&', msg);
        }
    }