package eu.algent.DuckCartUtility;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.algent.DuckCartUtility.Config.ConfigCore;
import eu.algent.DuckCartUtility.Listeners.MinecartListener;
import eu.algent.DuckCartUtility.Signs.SignControl;

public class DuckCartUtility extends JavaPlugin {
    private ConfigCore configuration;
    public SignControl signControl;

    public void onEnable(){
        configuration = new ConfigCore(this);
        if (!(configuration.isPluginEnabled())) this.setEnabled(false);
        else {
            signControl = new SignControl(this);
            //Events
            final PluginManager pm = getServer().getPluginManager();
            pm.registerEvents(new MinecartListener(this), this);
            getLogger().info(getName() + " has been enabled");
        }
    }

    public void onDisable(){ 
        getLogger().info(getName() + " has been disabled");

    }

    public ConfigCore getPluginConfig() {
        return configuration;
    }

    public void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

}
