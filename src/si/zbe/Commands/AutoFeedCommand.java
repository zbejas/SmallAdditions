package si.zbe.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import si.zbe.SmallAdd.Main;
import si.zbe.SmallAdd.Messages;

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

		if (!p.hasPermission("smalladd.autofeed")) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
			return true;
		}

		if (map.containsKey(p)) {
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
				byte b;
				int i;
				Material[] arrayOfMaterial;
				for (i = (arrayOfMaterial = Material.values()).length, b = 0; b < i;) {
					Material type = arrayOfMaterial[b];
					if (type.isEdible() && type.name().toLowerCase().startsWith(args[0].toLowerCase())
							&& checkFood(type))
						MaterialTypes.add(type.name());
					b++;
				}

			} else {
				byte b;
				int i;
				Material[] arrayOfMaterial;
				for (i = (arrayOfMaterial = Material.values()).length, b = 0; b < i;) {
					Material type = arrayOfMaterial[b];
					if (type.isEdible() && checkFood(type)) {
						MaterialTypes.add(type.name());
					}
					b++;
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
		if (m == Material.CAKE)
			return false;
		if (m == Material.POISONOUS_POTATO)
			return false;
		if (m == Material.CHICKEN)
			return false;
		if (m == Material.SPIDER_EYE)
			return false;
		if (m == Material.CHORUS_FRUIT)
			return false;
		if (m == Material.ROTTEN_FLESH)
			return false;
		if (m == Material.GOLDEN_APPLE)
			return false;
		if (m == Material.ENCHANTED_GOLDEN_APPLE)
			return false;
		if (m == Material.BEETROOT_SOUP)
			return false;
		if (m == Material.HONEY_BOTTLE)
			return false;
		if (m == Material.SUSPICIOUS_STEW)
			return false;
		if (m == Material.RABBIT_STEW) {
			return false;
		}
		return true;
	}
}
