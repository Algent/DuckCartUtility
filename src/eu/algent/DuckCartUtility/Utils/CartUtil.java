package eu.algent.DuckCartUtility.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Minecart;

public class CartUtil {

    public static Boolean hasMovedBlock(Location from, Location to){
        if (to.getBlockX() != from.getBlockX() 
                ||to.getBlockY() != from.getBlockY() 
                || to.getBlockZ() != from.getBlockZ()) {
            return true;
        }
        return false;
    }
    
    public static Boolean isOnTrack(Minecart minecart) {
        return true;
    }
}
