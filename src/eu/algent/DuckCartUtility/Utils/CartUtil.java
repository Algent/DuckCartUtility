package eu.algent.DuckCartUtility.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.ItemStack;

public class CartUtil {

    public static Boolean hasMovedBlock(Location from, Location to){
        if (to.getBlockX() != from.getBlockX() 
                ||to.getBlockY() != from.getBlockY() 
                || to.getBlockZ() != from.getBlockZ()) {
            return true;
        }
        return false;
    }
    
    public static Boolean isTrack(Material material) {
        return (material == Material.RAILS 
                || material == Material.POWERED_RAIL 
                || material == Material.DETECTOR_RAIL);
    }
    
    public static Boolean isTrack(Location location) {
        return isTrack(location.getBlock().getType());
    }
    
    public static Boolean isOnTrack(Minecart minecart) {
        return isTrack(minecart.getLocation().getBlock().getType());
    }
    
    public static void doDropCart(Minecart minecart) {
        minecart.remove();
        Location location = minecart.getLocation();
        location.getWorld().dropItem(location, new ItemStack(Material.MINECART, 1));
    }
}
