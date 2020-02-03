package si.zbe.smalladd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;
import si.zbe.smalladd.UpdateChecker;

public class UpdateCommand implements CommandExecutor {
	private final Main plugin;

	public UpdateCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("smalladd.admin")) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
			return true;
		}
		if (args.length > 0) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}
		(new UpdateChecker(this.plugin, 74452)).getVersion(version -> {
			if (this.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
				sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.NoUpdate"));
			} else {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.UpdateFound"));
					sender.sendMessage(ChatColor.RED + this.plugin.getDescription().getVersion() + ChatColor.WHITE + " -> " + ChatColor.GREEN + version);
				} else {
					sender.sendMessage(Messages.getString("SA.UpdateFound"));
					sender.sendMessage(this.plugin.getDescription().getVersion() + " -> " + version);
				}
			}
		});
		return true;
	}
}
