package si.zbe.smalladd.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HoeEvent implements Listener {
	@EventHandler
	public void onHoeUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (!p.hasPermission("smalladd.tool.hoe"))
			return;

		ItemStack itemInHand = p.getInventory().getItemInMainHand();

		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		// TODO: CHANGE BASED ON PLAYERS DIRECTION

		if (itemInHand.getType() == Material.IRON_HOE || itemInHand.getType() == Material.GOLDEN_HOE) {
			ArrayList<Block> list = new ArrayList<Block>();
			Block block = e.getClickedBlock();
			Block block1 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ() + 1);
			Block block2 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ());
			Block block3 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ());
			list.add(block);
			list.add(block1);
			list.add(block2);
			list.add(block3);

			if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.DIRT
					|| block.getType() == Material.GRASS_PATH) {
				for (Block b : list) {
					if (b.getType() == Material.GRASS_BLOCK || b.getType() == Material.DIRT
							|| b.getType() == Material.GRASS_PATH)
						b.setType(Material.FARMLAND);
				}
			}
		} else if (itemInHand.getType() == Material.DIAMOND_HOE) {
			ArrayList<Block> list = new ArrayList<Block>();
			Block block = e.getClickedBlock();
			Block block1 = p.getWorld().getBlockAt(block.getX() - 1, block.getY(), block.getZ() - 1);
			Block block2 = p.getWorld().getBlockAt(block.getX() - 1, block.getY(), block.getZ());
			Block block3 = p.getWorld().getBlockAt(block.getX() - 1, block.getY(), block.getZ() + 1);
			Block block4 = p.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() + 1);
			Block block5 = p.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() - 1);
			Block block6 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ() - 1);
			Block block7 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ() + 1);
			Block block8 = p.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ());
			list.add(block);
			list.add(block1);
			list.add(block2);
			list.add(block3);
			list.add(block4);
			list.add(block5);
			list.add(block6);
			list.add(block7);
			list.add(block8);

			if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.DIRT
					|| block.getType() == Material.GRASS_PATH) {
				for (Block b : list) {
					if (b.getType() == Material.GRASS_BLOCK || b.getType() == Material.DIRT
							|| b.getType() == Material.GRASS_PATH)
						b.setType(Material.FARMLAND);
				}
			}
		}
	}
}
