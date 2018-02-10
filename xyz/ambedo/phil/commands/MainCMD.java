package xyz.ambedo.phil.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.ambedo.phil.Registry;

public class MainCMD
extends JavaPlugin
{
    public Registry config;

    public MainCMD(Registry config) {
         this.config = config;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandL, String[] args) {

        Player p = (Player) commandSender;

        if (command.getName().equalsIgnoreCase("staffauth") && args.length == 0) {
            if (!commandSender.hasPermission("epiphany.staff")) {
                commandSender.sendMessage(Registry.plugin.color(this.config.getConfig().getString("messages.NoPerm")));
                return true;
            }
            for (String info : this.config.getConfig().getStringList("messages.EpiphanyInfo")) {
                p.sendMessage(Registry.plugin.color(info));
            }
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission("epiphany.admin")) {
                    p.sendMessage(Registry.plugin.color(this.config.getConfig().getString("messages.NoPerm")));
                    return true;
                }
                this.config.saveDefaultConfig();
                this.config.reloadConfig();
                p.sendMessage(Registry.plugin.color(this.config.getConfig().getString("messages.Reload")));
                return true;
            } else if (args[0].equalsIgnoreCase("version")) {
                if (!p.hasPermission("epiphany.staff")) {
                    p.sendMessage(Registry.plugin.color(this.config.getConfig().getString("messages.NoPerm")));
                    return true;
                }
                p.sendMessage(this.config.color("&4&lEpiphanyMC&8-&b&lStaffAuth"));
                p.sendMessage(" ");
                p.sendMessage(this.config.color("&6Version&8: &c&n1.0.1"));
                p.sendMessage(this.config.color("&6Author&8: &c&nPhil K. - Lead Developer"));
                p.sendMessage(this.config.color("&6Repo&8: &c&nhttps://github.com/DopeDealers/StaffAuth"));
            }
        }
        return false;
    }
}
