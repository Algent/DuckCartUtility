package eu.algent.DuckCartUtility.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
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
        Location location = minecart.getLocation();

        if (minecart instanceof StorageMinecart) {
            StorageMinecart storageCart = (StorageMinecart) minecart;
            ItemStack cartContent[] = storageCart.getInventory().getContents();
            for (ItemStack itemStack : cartContent) {
                if(itemStack != null) {
                    location.getWorld().dropItemNaturally(location, itemStack);
                }
            }
            minecart.remove();
            location.getWorld().dropItem(location, new ItemStack(Material.STORAGE_MINECART, 1));
        }
        else if (minecart instanceof PoweredMinecart) {
            minecart.remove();
            location.getWorld().dropItem(location, new ItemStack(Material.POWERED_MINECART, 1));
        }
        else {
            minecart.remove();
            location.getWorld().dropItem(location, new ItemStack(Material.MINECART, 1));
        }
    }

    public static Boolean isTpSafe(Location destination) {
        return (destination.getBlock().isEmpty() && destination.getBlock().getRelative(0, 1, 0).isEmpty());
    }
}
