package eu.algent.DuckCartUtility.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;
import org.bukkit.inventory.ItemStack;

public class MinecartListener implements Listener {
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event){
		Vehicle vehicle = event.getVehicle();
		if (!(vehicle instanceof Minecart)) return;
		Minecart minecart = (Minecart) vehicle;
		if (minecart.getDamage() > 40) return;
		vehicle.remove();
		Location location = vehicle.getLocation();
		location.getWorld().dropItem(location, new ItemStack(Material.MINECART, 1));
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleDestroy(VehicleDestroyEvent event){
		
	}
	
}
