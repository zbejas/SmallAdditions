package si.zbe.smalladd.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class VillagerDeathEvent implements Listener {
	@EventHandler
	public void onVillagerKill(EntityDeathEvent e) {
		if (e.getEntityType() != EntityType.VILLAGER)
			return;
		
		Villager v = (Villager) e.getEntity();
		Random rand = new Random();
		
		for (ItemStack is : v.getInventory()) {
			if (is != null) {
				v.getInventory().remove(is);
				e.getDrops().add(is);
			}
		}
		
		int dropchance = rand.nextInt(10);
		if (dropchance >= 0  && dropchance < 3)
			e.getDrops().add(new ItemStack(Material.EMERALD, 2));
		else if (dropchance >= 3 && dropchance < 7)
			e.getDrops().add(new ItemStack(Material.EMERALD, 1));
		else
			return;
		
	}
}
