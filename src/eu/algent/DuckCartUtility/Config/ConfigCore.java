package eu.algent.DuckCartUtility.Config;

import java.io.File;

import org.bukkit.plugin.Plugin;

public class ConfigCore {
    private final Plugin plugin;

    public ConfigCore(final Plugin plugin) {
        this.plugin = plugin;
        if (!(new File(plugin.getDataFolder(), "config.yml").exists())) {
            plugin.saveDefaultConfig();
        }
    }

    public boolean isPluginEnabled() {
        return plugin.getConfig().getBoolean("plugin-enabled", true);
    }

    public boolean isDropOnExit() {
        return plugin.getConfig().getBoolean("drop-cart-on-exit", true);
    }

    public boolean isRemoveLivingEntityCollide() {
        return plugin.getConfig().getBoolean("remove-livingentity-on-cart-collide", true);
    }

}
