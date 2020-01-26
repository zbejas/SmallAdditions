package si.zbe.smalladd.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerEvent implements Listener {
	@EventHandler
	public void onSpwnerBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (block.getType() != Material.SPAWNER)
			return;

		if (!e.getPlayer().hasPermission("smalladd.spawner"))
			return;

		if (!e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH))
			return;

		CreatureSpawner brokenspawner = (CreatureSpawner) block.getState();

		ItemStack item = new ItemStack(Material.SPAWNER, 1);
		BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
		CreatureSpawner newspawner = (CreatureSpawner) meta.getBlockState();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Spawner type: " + ChatColor.RED + brokenspawner.getSpawnedType());

		newspawner.setSpawnedType(brokenspawner.getSpawnedType());
		meta.setBlockState(newspawner);
		meta.setLore(lore);
		item.setItemMeta(meta);

		block.getWorld().dropItemNaturally(block.getLocation(), item);
	}
}
