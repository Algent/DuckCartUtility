package eu.algent.DuckCartUtility.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.algent.DuckCartUtility.DuckCartUtility;

public class CommandsManager implements CommandExecutor{
    public CommandsManager(DuckCartUtility plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        // "wand" Command
        if (args[0].equalsIgnoreCase("wand")) {
            if (sender instanceof Player)
                Wand.execute((Player) sender, args);
            else
                sender.sendMessage(ChatColor.RED + "DuckCart's Wand is player only.");
        }
        return true;
    }
}
