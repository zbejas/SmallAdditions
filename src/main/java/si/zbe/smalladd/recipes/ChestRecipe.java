package si.zbe.smalladd.recipes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import si.zbe.smalladd.Main;

public class ChestRecipe {
	public ShapedRecipe getRecipe() {
		ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "chest"), new ItemStack(Material.CHEST, 4));
		ArrayList<Material> logs = new ArrayList<Material>();
		logs.add(Material.ACACIA_LOG);
		logs.add(Material.BIRCH_LOG);
		logs.add(Material.DARK_OAK_LOG);
		logs.add(Material.JUNGLE_LOG);
		logs.add(Material.OAK_LOG);
		logs.add(Material.SPRUCE_LOG);
		logs.add(Material.STRIPPED_ACACIA_LOG);
		logs.add(Material.STRIPPED_BIRCH_LOG);
		logs.add(Material.STRIPPED_DARK_OAK_LOG);
		logs.add(Material.STRIPPED_JUNGLE_LOG);
		logs.add(Material.STRIPPED_OAK_LOG);
		logs.add(Material.STRIPPED_SPRUCE_LOG);
		
		r.shape("www","w w","www");
		r.setIngredient('w', new RecipeChoice.MaterialChoice(logs));
		
		return r;
	}
}
