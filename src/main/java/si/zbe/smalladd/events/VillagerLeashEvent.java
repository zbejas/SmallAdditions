package si.zbe.smalladd.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import si.zbe.smalladd.Main;

public class VillagerLeashEvent implements Listener {
	@EventHandler
	public void onVillagerLeash(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() != EntityType.VILLAGER)
			return;
		Player p = (Player) e.getPlayer();
		
		if (!p.hasPermission("smalladd.villagerleash"))
			return;
		
		Villager v = (Villager) e.getRightClicked();
    	final ItemStack itemInHand = p.getInventory().getItemInMainHand();
		final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();
		
		/*if (v.isLeashed()) {
			v.setLeashHolder(null);
			return;
		}*/
		
		if (itemInHand.getType() != Material.LEAD && itemInOffHand.getType() != Material.LEAD)
			return;
		
		if (!v.isLeashed())
			Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
			    @Override
			    public void run() {
			        v.setLeashHolder(p);
			        p.getInventory().remove(new ItemStack(Material.LEAD, 1));
			    }
			}, 1L);
	}
}
