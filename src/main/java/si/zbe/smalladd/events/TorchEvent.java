package si.zbe.smalladd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;

import java.util.ArrayList;

public class TorchEvent implements Listener {
    @EventHandler
    public void onTorchPlace(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        final ItemStack itemInHand = p.getInventory().getItemInMainHand();
        final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();
        if (!p.hasPermission("smalladd.tool.torch"))
            return;

        if (itemInHand.getType() != Material.TORCH && itemInOffHand.getType() != Material.TORCH)
            return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = new ItemStack(Material.TORCH, 1);
            ItemMeta itemmeta = item.getItemMeta();
            ArrayList <String> lore = new ArrayList <String>();
            lore.add(ChatColor.RED + Messages.getString("SA.TorchLore") + " " + ChatColor.GOLD + Messages.getString("SA.TorchName"));
            lore.add(ChatColor.GOLD + Messages.getString("SA.Owner") + ": " + ChatColor.GREEN + p.getName());
            itemmeta.setDisplayName(ChatColor.GOLD + Messages.getString("SA.TorchName"));
            itemmeta.setLore(lore);
            item.setItemMeta(itemmeta);

            if (itemInHand.isSimilar(item)) {
                Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItemInMainHand(item);
                        p.updateInventory();
                    }
                }, 1L);
                return;
            } else if (itemInOffHand.isSimilar(item)) {
                Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItemInOffHand(item);
                        p.updateInventory();
                    }
                }, 1L);
                return;
            }
        }
    }
}
