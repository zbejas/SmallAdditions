package si.zbe.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;

public class WorkbenchCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private final Main plugin;

	public WorkbenchCommand(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.NotAPlayer"));
			return true;
		}

		Player p = (Player) sender;
		
		if (!p.hasPermission("smalladd.workbench")) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
			return true;
		}
		
		if (args.length > 0) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}
		
		if (p.getItemInHand().getType() == Material.CRAFTING_TABLE) {
			ItemStack item = p.getItemInHand();
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName("Portable Workbench");
			item.setItemMeta(itemmeta);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.WorkbenchNameChanged"));
			return true;
		} else {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.WrongItem"));
			return true;
		}
	}
}