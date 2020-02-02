package si.zbe.smalladd;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import si.zbe.smalladd.commands.AutoFeedCommand;
import si.zbe.smalladd.commands.DisabledCommand;
import si.zbe.smalladd.commands.TorchCommand;
import si.zbe.smalladd.commands.UpdateCommand;
import si.zbe.smalladd.commands.WorkbenchCommand;
import si.zbe.smalladd.events.CropEvent;
import si.zbe.smalladd.events.DeathEvent;
import si.zbe.smalladd.events.FoodEvent;
import si.zbe.smalladd.events.HoeEvent;
import si.zbe.smalladd.events.SpawnerEvent;
import si.zbe.smalladd.events.TorchEvent;
import si.zbe.smalladd.events.TrampleEvent;
import si.zbe.smalladd.events.UpdateEvent;
import si.zbe.smalladd.events.VillagerDeathEvent;
import si.zbe.smalladd.events.VillagerLeashEvent;
import si.zbe.smalladd.events.WorkbenchEvent;
import si.zbe.smalladd.recipes.ChestRecipe;

public class Main extends JavaPlugin {

	public static Main plugin;

	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		plugin = this;
		int pluginId = 6335;
		Metrics metrics = new Metrics(this, pluginId);
		setConfig();
		updateCheck();
		registerCommands();
		registerEvents();
		registerRecipes();
		getLogger().info(Messages.getString("SA.SmallAdditionsEnabled"));
	}

	@Override
	public void onDisable() {
		getLogger().info(Messages.getString("SA.SmallAdditionsDisabled"));
		plugin.getServer().resetRecipes();
	}

	private void registerCommands() {
		// AUTOFEED
		if (getConfig().getBoolean("AutoFeed")) {
			getCommand("autofeed").setExecutor(new AutoFeedCommand(this));
			getCommand("autofeed").setTabCompleter(new AutoFeedCommand(this));
		} else {
			//getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
			getCommand("autofeed").setExecutor(new DisabledCommand(this));
		}

		// WORKBENCH
		if (getConfig().getBoolean("Tools.Workbench")) {
			getCommand("portableworkbench").setExecutor(new WorkbenchCommand(this));
		} else {
			//getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
			getCommand("portableworkbench").setExecutor(new DisabledCommand(this));
		}

		// TORCH
		if (getConfig().getBoolean("Tools.InfiniteTorch")) {
			getCommand("infinitetorch").setExecutor(new TorchCommand(this));
		} else {
			//getLogger().info(Messages.getString("SA.TorchDisabled"));
			getCommand("infinitetorch").setExecutor(new DisabledCommand(this));
		}

		// UPDATE
		getCommand("saupdate").setExecutor(new UpdateCommand(this));
	}

	private void registerEvents() {
		// CROPS
		if (getConfig().getBoolean("Crops")) {
			getServer().getPluginManager().registerEvents(new CropEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.CropsDisabled"));
		}

		// AUTOFEED
		if (getConfig().getBoolean("AutoFeed")) {
			getServer().getPluginManager().registerEvents(new FoodEvent(), this);
			//getLogger().info(Messages.getString("SA.AutoFeedWarning"));
		} else {
			getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
		}

		// NOTRAMPLE
		if (getConfig().getBoolean("NoTrample")) {
			getServer().getPluginManager().registerEvents(new TrampleEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.NoTrampleDisabled"));
		}

		// WORKBENCH
		if (getConfig().getBoolean("Tools.Workbench")) {
			getServer().getPluginManager().registerEvents(new WorkbenchEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
		}

		// VILLAGER DROPS
		if (getConfig().getBoolean("VillagerAdditions.Drops")) {
			getServer().getPluginManager().registerEvents(new VillagerDeathEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.VillagerDropsDisabled"));
		}

		// VILLAGER LEASH
		if (getConfig().getBoolean("VillagerAdditions.Leash")) {
			getServer().getPluginManager().registerEvents(new VillagerLeashEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.VillagerLeashDisabled"));
		}

		// TORCH
		if (getConfig().getBoolean("Tools.InfiniteTorch")) {
			getServer().getPluginManager().registerEvents(new TorchEvent(), this);
			getLogger().info(Messages.getString("SA.TorchWarning"));
		} else {
			getLogger().info(Messages.getString("SA.TorchDisabled"));
		}

		// HOE
		if (getConfig().getBoolean("Tools.BetterHoes")) {
			getServer().getPluginManager().registerEvents(new HoeEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.HoesDisabled"));
		}
		
		// SPAWNER
		if (getConfig().getBoolean("MineSpawners")) {
			getServer().getPluginManager().registerEvents(new SpawnerEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.SpawnerDisabled"));
		}
		
		// TOTEM
		if (getConfig().getBoolean("Tools.TotemInInv")) {
			getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.TotemDisabled"));
		}
		
		// UPDATE
		getServer().getPluginManager().registerEvents(new UpdateEvent(), this);
	}

	private void setConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void updateCheck() {
		new UpdateChecker(this, 74452).getVersion(version -> {
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				getLogger().info(Messages.getString("SA.NoUpdate"));
			} else {
				getLogger().info(Messages.getString("SA.UpdateFound") + " [" + version + "]");
			}
		});
	}

	private void registerRecipes() {
		if (getConfig().getBoolean("CustomRecipes.Chest")) {
			ChestRecipe chest = new ChestRecipe();
			plugin.getServer().addRecipe(chest.createRecipe());
		} else {
			getLogger().info(Messages.getString("SA.CustomRecipesDisabled"));
		}
	}
}
