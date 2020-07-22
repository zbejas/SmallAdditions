package si.zbe.smalladd.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.smalladd.Main;

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
		
		if (v.getKiller() != null)
			e.setDroppedExp(v.getVillagerExperience());
		
		int dropchance = rand.nextInt(100) + 1;
		int drop = Main.plugin.getConfig().getInt("VillagerAdditions.EmeraldDropChance.Drop");
		int minusdrop = rand.nextInt(drop);

		if (dropchance <= Main.plugin.getConfig().getInt("VillagerAdditions.EmeraldDropChance.DropChance"))
			if (Main.plugin.getConfig().getBoolean("VillagerAdditions.EmeraldDropChance.Randomize"))
				e.getDrops().add(new ItemStack(Material.EMERALD, drop - minusdrop));
			else
				e.getDrops().add(new ItemStack(Material.EMERALD, drop));
	}
}
