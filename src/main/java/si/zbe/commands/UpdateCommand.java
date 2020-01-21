package si.zbe.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
		if (!sender.hasPermission("smalladd.admin"))
			return true;
		if (args.length > 0) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}
		(new UpdateChecker((Plugin) this.plugin, 74452)).getVersion(version -> {
			if (this.plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
				sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.NoUpdate"));
			} else {
				sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.UpdateFound"));
			}
		});
		return true;
	}
}
