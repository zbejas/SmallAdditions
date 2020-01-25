package si.zbe.smalladd.commands;

import java.util.ArrayList;

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

public class TorchCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private final Main plugin;

	public TorchCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.NotAPlayer"));
			return true;
		}

		Player p = (Player) sender;
		
		if (!p.hasPermission("smalladd.tool.torch")) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
			return true;
		}
		
		if (args.length > 0) {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}
		
		if (p.getInventory().getItemInMainHand().getType() == Material.TORCH) {
			if (p.getInventory().getItemInMainHand().getAmount() > 1)
				p.getInventory().getItemInMainHand().setAmount(1);
			ItemStack item = p.getInventory().getItemInMainHand();
			ItemMeta itemmeta = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.RED + "Right-click anywhere to place " + ChatColor.GOLD + "Infinite Torch");
			lore.add(ChatColor.GOLD + "Owner: " + ChatColor.GREEN + p.getName());
			itemmeta.setDisplayName(ChatColor.GOLD + "Infinite Torch");
			itemmeta.setLore(lore);
			item.setItemMeta(itemmeta);
			p.sendMessage(ChatColor.GREEN + Messages.getString("SA.TorchNameChanged"));
			return true;
		} else {
			p.sendMessage(ChatColor.RED + Messages.getString("SA.WrongItem"));
			return true;
		}
	}
}