package eu.algent.DuckCartUtility.Utils;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class PlayerUtil {

    public static void setWandState(Player player, String value, Plugin plugin) {
        player.setMetadata("DuckCartWand", new FixedMetadataValue(plugin, value));
    }

    public static String getWandState(Player player) {
        List<MetadataValue> values = player.getMetadata("DuckCartWand");
        for (MetadataValue value : values) 
            if (isThisMine(value))
                return value.asString();
        return "off";
    }

    public static Boolean isWandEnabled(Player player) {
        if (getWandState(player) == "off")
            return false;
        else
            return true;
    }

    public static void setWandOption(Player player, int value, Plugin plugin) {
        player.setMetadata("DuckCartWandOption", new FixedMetadataValue(plugin, value));
    }

    public static Integer getWandOption(Player player) {
        List<MetadataValue> values = player.getMetadata("DuckCartWandOption");
        for (MetadataValue value : values) 
            if (isThisMine(value))
                return value.asInt();
        return 0;
    }

    private static Boolean isThisMine(MetadataValue value) {
        if (value.getOwningPlugin().getName().equals("DuckCartUtility"))
            return true;
        return false;
    }
}
