package si.zbe.smalladd;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.commands.AutoFeedCommand;
import si.zbe.commands.UpdateCommand;
import si.zbe.commands.WorkbenchCommand;
import si.zbe.events.CropEvent;
import si.zbe.events.FoodEvent;
import si.zbe.events.TrampleEvent;
import si.zbe.events.UpdateEvent;
import si.zbe.events.WorkbenchEvent;

public class Main extends JavaPlugin {

	public static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;
		setConfig();
		updateCheck();
		registerCommands();
		registerEvents();
		getLogger().info(Messages.getString("SA.SmallAdditionsEnabled"));
	}

	@Override
	public void onDisable() {
		getLogger().info(Messages.getString("SA.SmallAdditionsDisabled"));
	}

	private void registerCommands() {
		// AUTOFEED
		if (getConfig().getBoolean("AutoFeed")) {
			final PluginCommand autofeed = getCommand("autofeed");
			autofeed.setExecutor(new AutoFeedCommand(this));
			autofeed.setTabCompleter(new AutoFeedCommand(this));
		} else {
			getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
		}
		
		// WORKBENCH
		if (getConfig().getBoolean("Workbench")) {
			getCommand("portableworkbench").setExecutor(new WorkbenchCommand(this));
		} else {
			getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
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
			getLogger().info(Messages.getString("SA.AutoFeedWarning"));
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
		if (getConfig().getBoolean("Workbench")) {
			getServer().getPluginManager().registerEvents(new WorkbenchEvent(), this);
		} else {
			getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
		}
		
		// UPDATE
		getServer().getPluginManager().registerEvents(new UpdateEvent(), this);
	}

	private void setConfig() {
		getConfig().addDefault("Crops", true);
		getConfig().addDefault("AutoFeed", true);
		getConfig().addDefault("NoTrample", true);
		getConfig().addDefault("Workbench", true);
		getConfig().addDefault("Language", "english");
		getConfig().options().copyDefaults(true);
		saveConfig();
		getConfig().options().header(Messages.getString("SA.ConfigLanguage"));
		saveConfig();
	}

	private void updateCheck() {
		new UpdateChecker(this, 74452).getVersion(version -> {
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				getLogger().info(Messages.getString("SA.NoUpdate"));
			} else {
				getLogger().info(Messages.getString("SA.UpdateFound"));
			}
		});
	}

}
