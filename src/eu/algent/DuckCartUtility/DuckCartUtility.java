package eu.algent.DuckCartUtility;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.algent.DuckCartUtility.Config.ConfigCore;
import eu.algent.DuckCartUtility.Listeners.MinecartListener;

public class DuckCartUtility extends JavaPlugin {
	
	private ConfigCore configuration;

	public void onEnable(){
		configuration = new ConfigCore(this);
		if (!(configuration.isPluginEnabled())) this.setEnabled(false);
		else {
			//Events
			final PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new MinecartListener(this), this);
			getLogger().info(getName() + " has been enabled");
		}
	}

	public void onDisable(){ 
		getLogger().info(getName() + " has been disabled");

	}
	
	public ConfigCore getPuginConfig() {
		return configuration;
	}

}
