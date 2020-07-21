package si.zbe.smalladd.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WorkbenchEvent implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("smalladd.tool.workbench"))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final ItemStack itemInHand = p.getInventory().getItemInMainHand();
            final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();

            if (itemInHand.getType() == Material.CRAFTING_TABLE) {

                if (!itemInHand.hasItemMeta())
                    return;

                if (!itemInHand.getItemMeta().hasDisplayName())
                    return;

                if (!itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Portable Workbench"))
                    return;

                p.openWorkbench(null, true);
                p.updateInventory();
                e.setCancelled(true);
            } else if (itemInOffHand.getType() == Material.CRAFTING_TABLE) {
                if (!itemInOffHand.hasItemMeta())
                    return;

                if (!itemInOffHand.getItemMeta().hasDisplayName())
                    return;

                if (!itemInOffHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Portable Workbench"))
                    return;

                p.openWorkbench(null, true);
                p.updateInventory();
                e.setCancelled(true);
            }
        }
    }
}
