package si.zbe.SmallAdd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import si.zbe.Commands.AutoFeedCommand;
import si.zbe.Events.CropEvent;
import si.zbe.Events.FoodEvent;
import si.zbe.Events.TrampleEvent;

public class Main extends JavaPlugin {
	public FileConfiguration config = getConfig();

	public static Main plugin;

	public void onEnable() {
		plugin = this;
		setConfig();
		registerCommands();
		registerEvents();
		getLogger().info(Messages.getString("SA.SmallAdditionsEnabled"));
	}

	public void onDisable() {
		getLogger().info(Messages.getString("SA.SmallAdditionsDisabled"));
	}

	public void registerCommands() {
		getCommand("autofeed").setExecutor((CommandExecutor) new AutoFeedCommand(this));
		getCommand("autofeed").setTabCompleter((TabCompleter) new AutoFeedCommand(this));
	}

	public void registerEvents() {
		if (this.config.getBoolean("Crops")) {
			getServer().getPluginManager().registerEvents((Listener) new CropEvent(), (Plugin) this);
		} else {
			getLogger().info(Messages.getString("SA.CropsDisabled"));
		}
		if (this.config.getBoolean("AutoFeed")) {
			getServer().getPluginManager().registerEvents((Listener) new FoodEvent(), (Plugin) this);
			getLogger().info(Messages.getString("SA.AutoFeedWarning"));
		} else {
			getLogger().info(Messages.getString("SA.AutoFeedDisabled"));
		}
		if (this.config.getBoolean("NoTrample")) {
			getServer().getPluginManager().registerEvents((Listener) new TrampleEvent(), (Plugin) this);
		} else {
			getLogger().info(Messages.getString("SA.NoTrampleDisabled"));
		}
	}

	public void setConfig() {
		this.config.addDefault("Crops", Boolean.valueOf(true));
		this.config.addDefault("AutoFeed", Boolean.valueOf(true));
		this.config.addDefault("NoTrample", Boolean.valueOf(true));
		this.config.addDefault("Language", "english");
		this.config.options().copyDefaults(true);
		saveConfig();
		this.config.options().header(Messages.getString("SA.ConfigLanguage"));
		saveConfig();
	}
}
