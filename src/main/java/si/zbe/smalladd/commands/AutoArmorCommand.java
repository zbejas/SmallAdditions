package si.zbe.smalladd.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoArmorCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final Main plugin;
    public static ArrayList<Player> playerlist = new ArrayList <>();

    public AutoArmorCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + Messages.getString("SA.NotAPlayer"));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("smalladd.tool.armor")) {
            p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
            return true;
        }

        if (args.length > 0) {
            p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
        }

        if (playerlist.contains(p)) {
            playerlist.remove(p);
            p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoArmorDisabled"));
        } else {
            playerlist.add(p);
            p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoArmorEnabled"));
        }
        return true;
    }
}
