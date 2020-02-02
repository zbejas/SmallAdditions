package si.zbe.smalladd.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import si.zbe.smalladd.Messages;
import si.zbe.smalladd.commands.AutoFeedCommand;
import si.zbe.smalladd.utils.Utils;

public class FoodEvent implements Listener {
	public void eatFood(Material mat, Player p, FoodLevelChangeEvent e) {
		if (!p.getInventory().containsAtLeast(new ItemStack(mat), 16)) {
			//p.sendMessage(ChatColor.DARK_GREEN + "[AutoFeed] " + Messages.getString("SA.AutoFeedLowFood"));
			Utils u = new Utils();
			u.sendTitleActionBar(p, ChatColor.DARK_RED + Messages.getString("SA.AutoFeedLowFood"));
		}
		if (e.getFoodLevel() < 15 && getNutritionValue(mat) < 6) {
			if (p.getInventory().containsAtLeast(new ItemStack(mat), 1)) {
				if (e.getFoodLevel() + getNutritionValue(mat) < 20) {
					e.setFoodLevel(e.getFoodLevel() + getNutritionValue(mat));
				} else {
					e.setFoodLevel(20);
				}
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 10, 1);
				p.setSaturation((float) getSaturationValue(mat));
				p.getInventory().removeItem(new ItemStack(mat, 1));
			}
		} else if (e.getFoodLevel() < 20 && p.getHealth() < 20.0D) {
			if (p.getInventory().containsAtLeast(new ItemStack(mat), 1)) {
				int counter = 0;
				while (e.getFoodLevel() < 20) {
					if (e.getFoodLevel() + getNutritionValue(mat) < 20) {
						e.setFoodLevel(e.getFoodLevel() + getNutritionValue(mat));
						p.setSaturation((float) getSaturationValue(mat));
					} else {
						e.setFoodLevel(20);
						p.setSaturation((float) getSaturationValue(mat));
					}
					p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 10, 1);
					p.getInventory().removeItem(new ItemStack(mat, 1));
					counter++;
				}
				if (counter > 1)
					p.sendMessage(ChatColor.GREEN + "[AutoFeed] " + Messages.getString("SA.RapidFeed1") + " " + counter
							+ " " + Messages.getString("SA.RapidFeed2"));
			}
		} else if (e.getFoodLevel() + getNutritionValue(mat) == 20) {
			e.setFoodLevel(e.getFoodLevel() + getNutritionValue(mat));
			p.setSaturation((float) getSaturationValue(mat));
			p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 10, 1);
			p.getInventory().removeItem(new ItemStack(mat, 1));
		}
	}

	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();

		if (!(e.getEntity() instanceof Player))
			return;
		if (!e.getEntity().hasPermission("smalladd.autofeed"))
			return;
		if (!AutoFeedCommand.map.containsKey(p)) {
			return;
		}

		if (p.getInventory().containsAtLeast(new ItemStack(AutoFeedCommand.map.get(p)), 1)) {
			eatFood(AutoFeedCommand.map.get(p), p, e);
		}
	}

	public int getNutritionValue(Material m) {
		if (m == Material.BEETROOT || m == Material.DRIED_KELP || m == Material.POTATO || m == Material.TROPICAL_FISH)
			return 1;
		if (m == Material.COOKIE || m == Material.MELON_SLICE || m == Material.COD || m == Material.MUTTON
				|| m == Material.SALMON || m == Material.SWEET_BERRIES)
			return 2;
		if (m == Material.CARROT || m == Material.BEEF || m == Material.PORKCHOP || m == Material.RABBIT)
			return 3;
		if (m == Material.APPLE)
			return 4;
		if (m == Material.BAKED_POTATO || m == Material.BREAD || m == Material.COOKED_COD
				|| m == Material.COOKED_RABBIT)
			return 5;
		if (m == Material.COOKED_CHICKEN || m == Material.COOKED_MUTTON || m == Material.COOKED_SALMON
				|| m == Material.GOLDEN_CARROT)
			return 6;
		if (m == Material.COOKED_PORKCHOP || m == Material.COOKED_BEEF || m == Material.PUMPKIN_PIE) {
			return 8;
		}
		return 0;
	}

	public double getSaturationValue(Material m) {
		if (m == Material.GOLDEN_CARROT)
			return 14.4D;
		if (m == Material.COOKED_PORKCHOP || m == Material.COOKED_BEEF)
			return 12.8D;
		if (m == Material.COOKED_MUTTON || m == Material.COOKED_SALMON)
			return 9.6D;
		if (m == Material.COOKED_CHICKEN)
			return 7.2D;
		if (m == Material.BAKED_POTATO || m == Material.BREAD || m == Material.COOKED_COD
				|| m == Material.COOKED_RABBIT)
			return 6.0D;
		if (m == Material.PUMPKIN_PIE)
			return 4.8D;
		if (m == Material.APPLE)
			return 4.0D;
		if (m == Material.CARROT)
			return 3.6D;
		if (m == Material.BEEF || m == Material.PORKCHOP || m == Material.RABBIT)
			return 1.8D;
		if (m == Material.BEETROOT || m == Material.MELON_SLICE || m == Material.MUTTON)
			return 1.2D;
		if (m == Material.DRIED_KELP)
			return 1.0D;
		if (m == Material.POTATO)
			return 0.6D;
		if (m == Material.COOKIE || m == Material.COD || m == Material.SALMON || m == Material.SWEET_BERRIES)
			return 0.4D;
		if (m == Material.TROPICAL_FISH) {
			return 0.2D;
		}
		return 0.0D;
	}
}
