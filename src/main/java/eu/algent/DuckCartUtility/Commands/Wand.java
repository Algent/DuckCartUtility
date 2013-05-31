package eu.algent.DuckCartUtility.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import eu.algent.DuckCartUtility.DuckCartUtility;
import eu.algent.DuckCartUtility.Utils.PlayerUtil;
import eu.algent.DuckCartUtility.Utils.StringUtil;

public class Wand {
    DuckCartUtility plugin;

    public Wand(DuckCartUtility plugin) {
        this.plugin = plugin;
    }

    // Wand command executor
    public void execute(Player player, String[] args) {

        // not enough or too much arguments
        if (args.length == 1 || args.length > 3) {
            usage(player);
            return;
        }
        // 1 argument
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("set") && plugin.getPluginConfig().isWandSetEnabled())
                setSet(player);
            else if (args[1].equalsIgnoreCase("data") && plugin.getPluginConfig().isWandDataEnabled())
                player.sendMessage(ChatColor.DARK_AQUA + "Usage: /duckcart wand  data <Value>");
            else if (args[1].equalsIgnoreCase("offset") && plugin.getPluginConfig().isWandOffsetEnabled())
                player.sendMessage(ChatColor.DARK_AQUA + "Usage: /duckcart wand offset <Value>");
            else if (args[1].equalsIgnoreCase("toggle") && plugin.getPluginConfig().isWandToggleEnabled())
                setToggle(player);
            else if (args[1].equalsIgnoreCase("reset") && plugin.getPluginConfig().isWandResetEnabled())
                setReset(player);
            else if (args[1].equalsIgnoreCase("info") && plugin.getPluginConfig().isWandInfoEnabled())
                setInfo(player);
            else if (args[1].equalsIgnoreCase("off"))
                setOff(player);
            else
                usage(player);
        }
        // 2 Arguments
        else if (args.length == 3) {
            if(args[1].equalsIgnoreCase("data") && plugin.getPluginConfig().isWandDataEnabled()) {
                Integer data = StringUtil.parseIntSafe(args[2], 0);
                setData(player, data);
            }
            else if (args[1].equalsIgnoreCase("offset") && plugin.getPluginConfig().isWandOffsetEnabled()) {
                Integer offset = StringUtil.parseIntSafe(args[2], 0);
                setOffset(player, offset);
            }

        }
    }

    // Set wand in block "set" mode
    public void setSet(Player player) {
        if (!player.hasPermission("duckcartutility.wand.set")) {
            noPerm(player, "duckcartutility.wand.set");
            return;
        }
        PlayerUtil.setWandState(player, "set", plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode: Block set");
    }

    // Set wand in block "data" mode (max 15)
    public void setData(Player player, Integer data) {
        if (!player.hasPermission("duckcartutility.wand.data")) {
            noPerm(player, "duckcartutility.wand.data");
            return;
        }
        PlayerUtil.setWandState(player, "data", plugin);
        if (data > 15) data = 15;
        else if (data < 0) data = 0;
        PlayerUtil.setWandOption(player, data, plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode : data = " + data);
    }

    // Set wand in block "offset" mode (max +/-250)
    public void setOffset(Player player, Integer offset) {
        if (!player.hasPermission("duckcartutility.wand.offset")) {
            noPerm(player, "duckcartutility.wand.offset");
            return;
        }
        PlayerUtil.setWandState(player, "offset", plugin);
        if (offset > 250) offset = 250;
        else if (offset < -250) offset = -250;
        PlayerUtil.setWandOption(player, offset, plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode : offset = " + offset);
    }

    // Set wand in "toggle" mode : Toggle custom block on or off
    public void setToggle(Player player) {
        if (!player.hasPermission("duckcartutility.wand.toggle")) {
            noPerm(player, "duckcartutility.wand.toggle");
            return;
        }
        PlayerUtil.setWandState(player, "toggle", plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode: Toggle custom block");
    }

    // Set wand in cart "reset" mode
    public void setReset(Player player) {
        if (!player.hasPermission("duckcartutility.wand.reset")) {
            noPerm(player, "duckcartutility.wand.reset");
            return;
        }
        PlayerUtil.setWandState(player, "reset", plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode: Reset Cart");
    }

    // Set wand in "info" mode
    public void setInfo(Player player) {
        if (!player.hasPermission("duckcartutility.wand.info")) {
            noPerm(player, "duckcartutility.wand.info");
            return;
        }
        PlayerUtil.setWandState(player, "info", plugin);
        player.sendMessage(ChatColor.BLUE + "Wand mode : Info");
    }

    // Disable wand
    public void setOff(Player player) {
        PlayerUtil.setWandState(player, "off", plugin);
        player.sendMessage(ChatColor.BLUE + "Wand disabled");
    }

    public static void usage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Usage: /duckcart wand " +
                "<set | data | offset | toggle | reset | info | off>");
    }

    public static void noPerm(Player player, String string) {
        player.sendMessage(ChatColor.RED + "You don't have " + string + " permission node.");
    }
}
