package si.zbe.SmallAdd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.bramhaag.example.Updater;
import si.zbe.Commands.AutoFeedCommand;
import si.zbe.Commands.UpdateCommand;
import si.zbe.Commands.WorkbenchCommand;
import si.zbe.Events.CropEvent;
import si.zbe.Events.FoodEvent;
import si.zbe.Events.TrampleEvent;
import si.zbe.Events.WorkbenchEvent;

public class Main extends JavaPlugin {
	public FileConfiguration config = getConfig();

	public static Main plugin;

	public void onEnable() {
		plugin = this;
		updateCheck();
		setConfig();
		registerCommands();
		registerEvents();
		getLogger().info(Messages.getString("SA.SmallAdditionsEnabled"));
	}

	public void onDisable() {
		getLogger().info(Messages.getString("SA.SmallAdditionsDisabled"));
	}

	public void registerCommands() {
		// AUTOFEED
		if (this.config.getBoolean("AutoFeed")) {
			getCommand("autofeed").setExecutor((CommandExecutor) new AutoFeedCommand(this));
			getCommand("autofeed").setTabCompleter((TabCompleter) new AutoFeedCommand(this));
		} else {
			getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
		}

		// WORKBENCH
		if (this.config.getBoolean("Workbench")) {
			getCommand("portableworkbench").setExecutor((CommandExecutor) new WorkbenchCommand(this));
		} else {
			getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
		}
		
		//UPDATER
		getCommand("saupdate").setExecutor((CommandExecutor) new UpdateCommand(this));
	}

	public void registerEvents() {
		// CROPS
		if (this.config.getBoolean("Crops")) {
			getServer().getPluginManager().registerEvents((Listener) new CropEvent(), (Plugin) this);
		} else {
			getLogger().info(Messages.getString("SA.CropsDisabled"));
		}

		// AUTOFEED
		if (this.config.getBoolean("AutoFeed")) {
			getServer().getPluginManager().registerEvents((Listener) new FoodEvent(), (Plugin) this);
			getLogger().info(Messages.getString("SA.AutoFeedWarning"));
		} else {
			getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
		}

		// NOTRAMPLE
		if (this.config.getBoolean("NoTrample")) {
			getServer().getPluginManager().registerEvents((Listener) new TrampleEvent(), (Plugin) this);
		} else {
			getLogger().info(Messages.getString("SA.NoTrampleDisabled"));
		}

		// WORKBENCH
		if (this.config.getBoolean("Workbench")) {
			getServer().getPluginManager().registerEvents((Listener) new WorkbenchEvent(), (Plugin) this);
		} else {
			getLogger().info(Messages.getString("SA.WorkbenchDisabled"));
		}
	}

	public void setConfig() {
		this.config.addDefault("Crops", Boolean.valueOf(true));
		this.config.addDefault("AutoFeed", Boolean.valueOf(true));
		this.config.addDefault("NoTrample", Boolean.valueOf(true));
		this.config.addDefault("Workbench", Boolean.valueOf(true));
		this.config.addDefault("Language", "english");
		this.config.options().copyDefaults(true);
		saveConfig();
		this.config.options().header(Messages.getString("SA.ConfigLanguage"));
		saveConfig();
	}

	public void updateCheck() {
		Updater updater = new Updater(this, "74452");
		Updater.UpdateResults result = updater.checkForUpdates();
		getLogger().info(Messages.getString("SA.UpdateCheck"));
		if (result.getResult() == Updater.UpdateResult.FAIL) {
			getLogger().info(Messages.getString("SA.UpdateFail") + result.getVersion());
		} else if (result.getResult() == Updater.UpdateResult.NO_UPDATE) {
			getLogger().info(Messages.getString("SA.NoUpdate"));

		} else if (result.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
			getLogger().info(Messages.getString("SA.UpdateFound"));
		}
	}
}
