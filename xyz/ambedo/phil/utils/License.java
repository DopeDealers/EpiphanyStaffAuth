package xyz.ambedo.phil.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.ambedo.phil.Registry;

import java.io.File;
import java.io.IOException;

public class License
{
    private File file;
    private FileConfiguration config;

    public License() {
        this.file = new File(Registry.plugin.getDataFolder(), "enable.txt");
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Registry.plugin.saveResource("enable.txt", false);
    }
    public boolean accepted() {
        return this.config.getBoolean("plugin-on") && this.config.contains("plugin-on") && this.config.getBoolean("plugin-on");
    }
    public void save(YamlConfiguration config) {
        try {
            config.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
