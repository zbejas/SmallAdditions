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
        	final ItemStack itemInHand = p.getInventory().getItemInMainHand();
			final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();
			
			if (itemInHand.getType() == Material.CRAFTING_TABLE || itemInOffHand.getType() == Material.CRAFTING_TABLE) {
				//TODO ItemMeta could be null
                if (!itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase("Portable Workbench") && !itemInOffHand.getItemMeta().getDisplayName().equalsIgnoreCase("Portable Workbench"))
                    return;
                
                p.openWorkbench(null, true);
                e.setCancelled(true);
            }
        }
    }
}
