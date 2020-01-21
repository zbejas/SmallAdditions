package si.zbe.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WorkbenchEvent implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (!p.hasPermission("smalladd.workbench"))
			return;

		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.CRAFTING_TABLE) {
				if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Portable Workbench"))
					return;
				p.openWorkbench(null, true);
				e.setCancelled(true);
			}
		}
	}
}
