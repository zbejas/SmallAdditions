package si.zbe.smalladd.events;

import java.util.ArrayList;

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

public class TorchEvent implements Listener {
	@EventHandler
	public void onTorchPlace(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		final ItemStack itemInHand = p.getInventory().getItemInMainHand();
		final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();
		if (!p.hasPermission("smalladd.torch"))
			return;
		
		if (itemInHand.getType() != Material.TORCH && itemInOffHand.getType() != Material.TORCH)
			return;
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemStack item = new ItemStack(Material.TORCH, 1);
			ItemMeta itemmeta = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.RED + "Right-click anywhere to place " + ChatColor.GOLD + "Infinite Torch");
			lore.add(ChatColor.GOLD + "Owner: " + ChatColor.GREEN + p.getName());
			itemmeta.setDisplayName(ChatColor.GOLD + "Infinite Torch");
			itemmeta.setLore(lore);
			item.setItemMeta(itemmeta);

			if (itemInHand.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Infinite Torch")) {
				Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
					@Override
					public void run() {
						p.getInventory().setItemInMainHand(item);
						p.updateInventory();
					}
				}, 1L);
				return;
			} else if (itemInOffHand.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Infinite Torch")) {
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
