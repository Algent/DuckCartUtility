package eu.algent.DuckCartUtility;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.algent.DuckCartUtility.Listeners.MinecartListener;

public class DuckCartUtility extends JavaPlugin {
	
    public void onEnable(){ 
    	getLogger().info("Enabling DuckCartUtility");
    	//Event Listeners
    	final PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(new MinecartListener(), this);
    }
     
    public void onDisable(){ 
    	getLogger().info("Disabling DuckCartUtility");
     
    }

}
