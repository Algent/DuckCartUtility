package eu.algent.DuckCartUtility.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class SignUtil {

    public static boolean isSign(Location location) {
        return isSign(location.getBlock().getType());
    }

    public static Boolean isSign(Block block) {
        return isSign(block.getType());
    }

    public static boolean isSign(Material material) {
        return (material == Material.SIGN_POST || material == Material.WALL_SIGN);
    }

    public static Sign getSignAt(Block block) {
        return (Sign) block.getState();
    }
}
