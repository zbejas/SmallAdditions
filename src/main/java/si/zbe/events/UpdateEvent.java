package si.zbe.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;
import si.zbe.smalladd.UpdateChecker;

public class UpdateEvent implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPermission("smalladd.admin"))
			return;
		(new UpdateChecker((Plugin) Main.plugin, 74452)).getVersion(version -> {
			if (!Main.plugin.getDescription().getVersion().equalsIgnoreCase(version))
				e.getPlayer().sendMessage(ChatColor.GREEN + "[SmallAdditions] " + Messages.getString("SA.UpdateFound"));
			else
				return;
		});
	}
}
