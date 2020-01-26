package si.zbe.smalladd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;

public class DisabledCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private final Main plugin;

	public DisabledCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(ChatColor.RED + Messages.getString("SA.CommandDisabled"));
		return true;
	}

}
