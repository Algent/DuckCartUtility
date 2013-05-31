package eu.algent.DuckCartUtility.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public class WandUtil {

    public static void wandSet(Minecart minecart, Player player) {
        int id, data;
        id = player.getItemInHand().getTypeId();
        data = player.getItemInHand().getData().getData();
        CartUtilCB.setBlock(minecart, id, data);
    }

    public static void wandData(Minecart minecart, Player player) {
        CartUtilCB.setData(minecart, PlayerUtil.getWandOption(player));
    }

    public static void wandOffset(Minecart minecart, Player player) {
        CartUtilCB.setBlockOffset(minecart, PlayerUtil.getWandOption(player));
    }

    public static void wandToggle(Minecart minecart, Player player) {
        CartUtilCB.toggleCustomBlock(minecart);
    }

    public static void wandReset(Minecart minecart, Player player) {
        CartUtilCB.resetCustomization(minecart);
    }

    public static void wandInfo(Minecart minecart, Player player) {
        String type = minecart.getType().toString();
        String custom = CartUtilCB.hasCustomBlock(minecart).toString();
        String block = Material.getMaterial(CartUtilCB.getBlockId(minecart)).toString();
        String data = CartUtilCB.getBlockData(minecart).toString();
        String offset = CartUtilCB.getBlockOffset(minecart).toString();
        player.sendMessage(ChatColor.BLUE + "CartType: " + type + " - hasCustomBlock: " + custom);
        player.sendMessage(ChatColor.BLUE + "Block: " + block + " - Data: " + data + " - Offset: " + offset);
    }
}
