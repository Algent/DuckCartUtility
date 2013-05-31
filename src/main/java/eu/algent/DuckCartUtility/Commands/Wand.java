package eu.algent.DuckCartUtility.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import eu.algent.DuckCartUtility.Utils.PlayerUtil;
import eu.algent.DuckCartUtility.Utils.StringUtil;

public class Wand {

    // Wand command executor
    public static void execute(Player player, String[] args) {

        //TODO PERMISSIONS + CONF

        // 0 arguments
        if (args.length == 1 || args.length > 3) {
            usage(player);
            return;
        }
        // 1 argument
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("set")) setOn(player);
            else if (args[1].equalsIgnoreCase("offset")) setOffset(player, 0);
            else if (args[1].equalsIgnoreCase("toggle")) setToggle(player);
            else if (args[1].equalsIgnoreCase("reset")) setReset(player);
            else if (args[1].equalsIgnoreCase("info")) setInfo(player);
            else if (args[1].equalsIgnoreCase("off")) setOff(player);
        }
        // 2 Arguments
        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("offset")) {
                Integer offset = StringUtil.parseIntSafe(args[2], 0);
                setOffset(player, offset);
            }
        }
    }

    // Set wand in block "set" mode
    public static void setOn(Player player) {
        PlayerUtil.setWandState(player, "set", getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand mode: Block set");
    }

    // Set wand in block "offset" mode (max +/-250)
    public static void setOffset(Player player, Integer offset) {
        PlayerUtil.setWandState(player, "offset", getPlugin());
        if (offset > 250) offset = 250;
        else if (offset < -250) offset = -250;
        PlayerUtil.setWandOption(player, offset, getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand mode : offset = " + offset);
    }

    // Set wand in "toggle" mode : Toggle custom block on or off
    public static void setToggle(Player player) {
        PlayerUtil.setWandState(player, "toggle", getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand mode: Toggle custom block");
    }

    // Set wand in cart "reset" mode
    public static void setReset(Player player) {
        PlayerUtil.setWandState(player, "reset", getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand mode: Reset Cart");
    }

    // Set wand in "info" mode
    public static void setInfo(Player player) {
        PlayerUtil.setWandState(player, "info", getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand mode : Info");
    }

    // Disable wand
    public static void setOff(Player player) {
        PlayerUtil.setWandState(player, "off", getPlugin());
        player.sendMessage(ChatColor.BLUE + "Wand disabled");
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("DuckCartUtility");
    }

    public static void usage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Usage: /duckcart wand " +
                "<set | offset | toggle | reset | info | off>");
    }
}
