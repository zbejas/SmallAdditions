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

public class AutoFeedCommand implements CommandExecutor, TabCompleter {
	@SuppressWarnings("unused")
	private final Main plugin;
	public static HashMap<Player, Material> map = new HashMap<>();

	public AutoFeedCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Material type;
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.NotAPlayer"));
			return true;
		}

		Player p = (Player) sender;

		if (args.length > 1) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
		}

		if (!p.hasPermission("smalladd.autofeed")) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
			return true;
		}

		if (map.containsKey(p) && args.length == 0) {
			map.remove(p);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoFeedDisabled"));
			return true;
		}

		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.AutoFeedFood"));
			return true;
		}

		try {
			type = Material.valueOf(args[0].toUpperCase());
			if (!checkFood(type)) {
				p.sendMessage(ChatColor.RED + Messages.getString("SA.AutoFeedBlockedFood"));
				return true;
			}
		} catch (Exception e) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}

		if (args.length == 1 && map.containsKey(p) && map.get(p) != type) {
			map.remove(p);
			map.put(p, type);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoFeedChanged"));
			return true;
		}

		if (map.containsKey(p)) {
			map.remove(p);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoFeedDisabled"));
		} else {
			map.put(p, type);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.PlayerAutoFeedEnabled"));
		}

		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		ArrayList<String> MaterialTypes = new ArrayList<>();
		if (args.length == 1) {
			if (!args[0].equals("")) {
				Material[] arrayOfMaterial = Material.values();
				for (Material type : arrayOfMaterial) {
					if (type.isEdible() && type.name().toLowerCase().startsWith(args[0].toLowerCase())
							&& checkFood(type))
						MaterialTypes.add(type.name());
				}
			} else {
				Material[] arrayOfMaterial = Material.values();
				for (Material type : arrayOfMaterial) {
					if (type.isEdible() && checkFood(type)) {
						MaterialTypes.add(type.name());
					}
				}
			}
			Collections.sort(MaterialTypes);
			return MaterialTypes;
		}
		return null;
	}

	public static boolean checkFood(Material m) {
		if (m == Material.PUFFERFISH)
			return false;
		else if (m == Material.CAKE)
			return false;
		else if (m == Material.POISONOUS_POTATO)
			return false;
		else if (m == Material.CHICKEN)
			return false;
		else if (m == Material.SPIDER_EYE)
			return false;
		else if (m == Material.CHORUS_FRUIT)
			return false;
		else if (m == Material.ROTTEN_FLESH)
			return false;
		else if (m == Material.GOLDEN_APPLE)
			return false;
		else if (m == Material.ENCHANTED_GOLDEN_APPLE)
			return false;
		else if (m == Material.BEETROOT_SOUP)
			return false;
		else if (m == Material.HONEY_BOTTLE)
			return false;
		else if (m == Material.SUSPICIOUS_STEW)
			return false;
		else return m != Material.RABBIT_STEW;
	}
}
