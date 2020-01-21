package si.zbe.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.bramhaag.example.Updater;
import si.zbe.SmallAdd.Main;
import si.zbe.SmallAdd.Messages;

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

		Updater updater = new Updater(plugin, "74452");
		Updater.UpdateResults result = updater.checkForUpdates();
		if (result.getResult() == Updater.UpdateResult.FAIL) {
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.UpdateFail") + result.getVersion());
		} else if (result.getResult() == Updater.UpdateResult.NO_UPDATE) {
			sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.NoUpdate"));

		} else if (result.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
			sender.sendMessage(ChatColor.GREEN + Messages.getString("SA.UpdateFound"));
		} else
			sender.sendMessage(ChatColor.RED + Messages.getString("SA.UpdateFail"));

		return true;
	}
}
