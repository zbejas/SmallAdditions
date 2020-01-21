package si.zbe.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WorkbenchEvent implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("smalladd.workbench"))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            /*
             * TODO the player has a main and secondary hand
             *  p.getInventory().getItemInMainHand()
             *  p.getInventory().getItemInOffHand()
             */
			final ItemStack itemInHand = p.getItemInHand();
			if (itemInHand.getType() == Material.CRAFTING_TABLE) {
				//TODO ItemMeta could be null
                if (!itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase("Portable Workbench"))
                    return;
                p.openWorkbench(null, true);
                e.setCancelled(true);
            }
        }
    }
}
