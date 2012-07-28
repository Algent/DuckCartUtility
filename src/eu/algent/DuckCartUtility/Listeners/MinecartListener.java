package eu.algent.DuckCartUtility.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import eu.algent.DuckCartUtility.DuckCartUtility;

public class MinecartListener implements Listener {
	private final DuckCartUtility plugin;
	
	public MinecartListener(final DuckCartUtility plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event){
		if (!(event.getVehicle() instanceof Minecart)) return;
		Minecart minecart = (Minecart) event.getVehicle();
		if (minecart.getDamage() > 40) return;
		if (plugin.getPuginConfig().isDropOnExit()) {
			minecart.remove();
			Location location = minecart.getLocation();
			location.getWorld().dropItem(location, new ItemStack(Material.MINECART, 1));
		}
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleEntityCollision(VehicleEntityCollisionEvent event){
		if (event.getVehicle() instanceof Minecart){
			Minecart minecart = (Minecart) event.getVehicle();
			if (event.getEntity() instanceof LivingEntity){
				LivingEntity livingentity = (LivingEntity) event.getEntity();
				if (!(livingentity instanceof Player) && !(livingentity instanceof Tameable)){
					if (plugin.getPuginConfig().isRemoveLivingEntityCollide()) {
						final Vector velocity = minecart.getVelocity(); 
						if (velocity.getX() != 0d || velocity.getY() != 0d || velocity.getZ() != 0d){
							livingentity.remove();
							event.setCancelled(true);
							event.setCollisionCancelled(true);
						}
					}
				}
			}
		}
		
	}
	
}
