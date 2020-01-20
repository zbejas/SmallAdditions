package si.zbe.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CropEvent implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWheat(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block.getType() == Material.WHEAT) {
				if (!e.getPlayer().hasPermission("smalladd.wheat")) {
					return;
				}

				if (block.getData() != 7) {
					return;
				}

				boolean SeedInDrop = false;
				for (ItemStack is : block.getDrops()) {
					if (is.getType() == Material.WHEAT_SEEDS) {
						SeedInDrop = true;
					}
				}
				if (SeedInDrop) {
					block.getDrops().remove(new ItemStack(Material.WHEAT_SEEDS, 1));
					block.breakNaturally();
					block.setType(Material.WHEAT);
					return;
				}
				if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.WHEAT_SEEDS), 1)) {
					e.getPlayer().getInventory().remove(new ItemStack(Material.WHEAT_SEEDS, 1));
					block.breakNaturally();
					block.setType(Material.WHEAT);
					return;
				}
				block.breakNaturally();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPotato(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block.getType() == Material.POTATOES) {
				if (!e.getPlayer().hasPermission("smalladd.potato")) {
					return;
				}

				if (block.getData() != 7) {
					return;
				}

				boolean SeedInDrop = false;
				for (ItemStack is : block.getDrops()) {
					if (is.getType() == Material.POTATO) {
						SeedInDrop = true;
					}
				}
				if (SeedInDrop) {
					block.getDrops().remove(new ItemStack(Material.POTATO, 1));
					block.breakNaturally();
					block.setType(Material.POTATOES);
				} else if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.POTATO), 0)) {
					e.getPlayer().getInventory().remove(new ItemStack(Material.POTATO, 1));
					block.breakNaturally();
					block.setType(Material.POTATOES);
				} else {
					block.breakNaturally();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCarrot(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block.getType() == Material.CARROTS) {
				if (!e.getPlayer().hasPermission("smalladd.carrot")) {
					return;
				}

				if (block.getData() != 7) {
					return;
				}

				boolean SeedInDrop = false;
				for (ItemStack is : block.getDrops()) {
					if (is.getType() == Material.CARROT) {
						SeedInDrop = true;
					}
				}
				if (SeedInDrop) {
					block.getDrops().remove(new ItemStack(Material.CARROT, 1));
					block.breakNaturally();
					block.setType(Material.CARROTS);
				} else if (e.getPlayer().getInventory().contains(new ItemStack(Material.CARROT))) {
					e.getPlayer().getInventory().remove(new ItemStack(Material.CARROT, 1));
					block.breakNaturally();
					block.setType(Material.CARROTS);
				} else {
					block.breakNaturally();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBeetroot(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block.getType() == Material.BEETROOTS) {
				if (!e.getPlayer().hasPermission("smalladd.beetroot")) {
					return;
				}

				if (block.getData() != 3) {
					return;
				}

				boolean SeedInDrop = false;
				for (ItemStack is : block.getDrops()) {
					if (is.getType() == Material.BEETROOT_SEEDS) {
						SeedInDrop = true;
					}
				}
				if (SeedInDrop) {
					block.getDrops().remove(new ItemStack(Material.BEETROOT, 1));
					block.breakNaturally();
					block.setType(Material.BEETROOTS);
				} else if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.BEETROOT_SEEDS), 1)) {
					e.getPlayer().getInventory().remove(new ItemStack(Material.BEETROOT, 1));
					block.breakNaturally();
					block.setType(Material.BEETROOTS);
				} else {
					block.breakNaturally();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWart(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if (block.getType() == Material.NETHER_WART) {
				if (!e.getPlayer().hasPermission("smalladd.netherwart")) {
					return;
				}

				if (block.getData() != 3) {
					return;
				}

				boolean SeedInDrop = false;
				for (ItemStack is : block.getDrops()) {
					if (is.getType() == Material.NETHER_WART) {
						SeedInDrop = true;
					}
				}
				if (SeedInDrop) {
					block.getDrops().remove(new ItemStack(Material.NETHER_WART, 1));
					block.breakNaturally();
					block.setType(Material.NETHER_WART);
				} else if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(Material.NETHER_WART), 1)) {
					e.getPlayer().getInventory().remove(new ItemStack(Material.NETHER_WART, 1));
					block.breakNaturally();
					block.setType(Material.NETHER_WART);
				} else {
					block.breakNaturally();
				}
			}
		}
	}
}
