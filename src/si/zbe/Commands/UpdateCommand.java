package si.zbe.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import si.zbe.SmallAdd.Main;
import si.zbe.SmallAdd.Messages;
import si.zbe.SmallAdd.UpdateChecker;

public class UpdateCommand implements CommandExecutor {
	private final Main plugin;

	public UpdateCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("smalladd.admin"))
			return true;
		if (args.length > 0) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
			return true;
		}

		new UpdateChecker(plugin, 74452).getVersion(version -> {
            if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
            	sender.sendMessage(ChatColor.GREEN +  Messages.getString("SA.NoUpdate"));
            } else {
            	sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.UpdateFound"));
            }
        });

		return true;
	}
}
