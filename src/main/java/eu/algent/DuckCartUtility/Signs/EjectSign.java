package eu.algent.DuckCartUtility.Signs;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.util.Vector;

import eu.algent.DuckCartUtility.DuckCartUtility;
import eu.algent.DuckCartUtility.Utils.CartUtil;
import eu.algent.DuckCartUtility.Utils.StringUtil;

/**
 *  Eject Sign:
 *  1st Line: [Eject]
 *  2nd Line:  x,y,z            (teleport destination in offset from cart)
 *  3rd Line: yaw,pitch or yaw  (optional: default yaw is ingame north)
 *  4rd Line: ph:itemid         (optional: Only eject passenger if he have a specificic item in hand)
 */
public class EjectSign {
    DuckCartUtility plugin;

    public EjectSign(DuckCartUtility plugin) {
        this.plugin = plugin;
    }

    public void execute(Sign sign, Minecart minecart) {
        if (!plugin.getPluginConfig().isEjectSignEnabled()) return;
        Boolean empty = minecart.isEmpty();
        if (empty && !plugin.getPluginConfig().isEjectSignEjectEmptyCart()) return;
        if (minecart instanceof StorageMinecart) {
            if (!plugin.getPluginConfig().isEjectSignEjectStorageCart()) return;
        }

        // Reading Optional Condition
        String[] conditionLine = sign.getLine(3).split(":");
        int idWanted = 0, idInHand = 0;
        if (conditionLine.length == 2 && conditionLine[0].equalsIgnoreCase("ph")) {
            idWanted = StringUtil.parseIntSafe(conditionLine[1], 0);
            if (!empty && minecart.getPassenger() instanceof Player) {
                idInHand = ((Player) minecart.getPassenger()).getItemInHand().getTypeId();
                if (idWanted != idInHand) return;
            }
        }

        // Reading Offset from sign
        String[] offsetLine = sign.getLine(1).split(",");
        Vector offset = new Vector();
        if (offsetLine.length == 3) {
            offset.setX(StringUtil.parseIntSafe(offsetLine[0], 0));
            offset.setY(StringUtil.parseIntSafe(offsetLine[1], 0));
            offset.setZ(StringUtil.parseIntSafe(offsetLine[2], 0));
        }
        double maxDistance = plugin.getPluginConfig().getMaxEjectDistance();
        if (offset.length() > maxDistance)
        {
            offset.normalize().multiply(maxDistance);
        }

        // Reading Orientation from sign
        String[] orientationLine = sign.getLine(2).split(",");
        float yaw = 0F, pitch = 0F;
        if (orientationLine.length == 2) {
            yaw = (float) StringUtil.parseIntSafe(orientationLine[0], 0);
            pitch = (float) StringUtil.parseIntSafe(orientationLine[1], 0);
        } else if (orientationLine.length == 1) {
            yaw = (float) StringUtil.parseIntSafe(orientationLine[0], 0);
        }

        // Readying eject destination
        Location destination = minecart.getLocation().getBlock().getLocation();
        destination = destination.add(0.5, 0.7, 0.5).add(offset);
        destination.setYaw(yaw + 180);
        destination.setPitch(pitch);

        if(!CartUtil.isTpSafe(destination)) destination = minecart.getLocation();

        // Ejecting
        minecart.teleport(destination);
        if (!empty) {
            Entity entity = minecart.getPassenger();
            entity.teleport(destination);
            minecart.eject();
        }
        else CartUtil.doDropCart(minecart);
    }
}
