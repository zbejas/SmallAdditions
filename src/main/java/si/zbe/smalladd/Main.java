package si.zbe.smalladd;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.smalladd.commands.*;
import si.zbe.smalladd.events.*;
import si.zbe.smalladd.recipes.ChestRecipe;
import si.zbe.smalladd.utils.DeathManager;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Metrics metrics = new Metrics(this, 6335);
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
        DeathManager dm = new DeathManager();
        dm.saveDeathData();
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

        // AUTOARMOR
        if (getConfig().getBoolean("Tools.AutoArmor.Enabled")) {
            getCommand("autoarmor").setExecutor(new AutoArmorCommand(this));
        } else {
            //getLogger().info(Messages.getString("SA.TorchDisabled"));
            getCommand("autoarmor").setExecutor(new DisabledCommand(this));
        }

        // DEATH COMMAND
        if (getConfig().getBoolean("DeathBook.Enabled")) {
            getCommand("sadeath").setExecutor(new DeathCommand(this));
        } else {
            getCommand("autoarmor").setExecutor(new DisabledCommand(this));
        }

        // UPDATE
        getCommand("saupdate").setExecutor(new UpdateCommand(this));
    }

    private void registerEvents() {
        // CROPS
        if (getConfig().getBoolean("Crops")) {
            getServer().getPluginManager().registerEvents(new CropEvent(), this);
        } else {
            getLogger().info("Crop harvest " + Messages.getString("SA.OptionDisabled"));
        }

        // AUTOFEED
        if (getConfig().getBoolean("AutoFeed")) {
            getServer().getPluginManager().registerEvents(new FoodEvent(), this);
            //getLogger().info(Messages.getString("SA.AutoFeedWarning"));
        } else {
            getLogger().info("AutoFeed " + Messages.getString("SA.OptionDisabled"));
        }

        // NOTRAMPLE
        if (getConfig().getBoolean("NoTrample")) {
            getServer().getPluginManager().registerEvents(new TrampleEvent(), this);
        } else {
            getLogger().info("NoTrample " + Messages.getString("SA.OptionDisabled"));
        }

        // WORKBENCH
        if (getConfig().getBoolean("Tools.Workbench")) {
            getServer().getPluginManager().registerEvents(new WorkbenchEvent(), this);
        } else {
            getLogger().info("Workbench " + Messages.getString("SA.OptionDisabled"));
        }

        // VILLAGER DROPS
        if (getConfig().getBoolean("VillagerAdditions.Drops")) {
            getServer().getPluginManager().registerEvents(new VillagerDeathEvent(), this);
        } else {
            getLogger().info("VillagerAdditions Drops " + Messages.getString("SA.OptionDisabled"));
        }

        // VILLAGER LEASH
        if (getConfig().getBoolean("VillagerAdditions.Leash")) {
            getServer().getPluginManager().registerEvents(new VillagerLeashEvent(), this);
        } else {
            getLogger().info("VillagerAdditions Leash " + Messages.getString("SA.OptionDisabled"));
        }

        // TORCH
        if (getConfig().getBoolean("Tools.InfiniteTorch")) {
            getServer().getPluginManager().registerEvents(new TorchEvent(), this);
            getLogger().info(Messages.getString("SA.TorchWarning"));
        } else {
            getLogger().info("InfiniteTorch " + Messages.getString("SA.OptionDisabled"));
        }

        // HOE
        if (getConfig().getBoolean("Tools.BetterHoes")) {
            getServer().getPluginManager().registerEvents(new HoeEvent(), this);
        } else {
            getLogger().info("BetterHoe " + Messages.getString("SA.OptionDisabled"));
        }

        // SPAWNER
        if (getConfig().getBoolean("MineSpawners")) {
            getServer().getPluginManager().registerEvents(new SpawnerEvent(), this);
        } else {
            getLogger().info("MineSpawners " + Messages.getString("SA.OptionDisabled"));
        }

        // TOTEM
        if (getConfig().getBoolean("Tools.TotemInInv")) {
            getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        } else {
            getLogger().info("TotemToInv " + Messages.getString("SA.OptionDisabled"));
        }

        // ARMOR
        if (getConfig().getBoolean("Tools.SwitchArmor")) {
            getServer().getPluginManager().registerEvents(new ArmorEvent(), this);
        } else {
            getLogger().info("SwitchArmor " + Messages.getString("SA.OptionDisabled"));
        }

        // AUTOARMOR
        if (getConfig().getBoolean("Tools.AutoArmor.Enabled")) {
            getServer().getPluginManager().registerEvents(new AutoArmorEvent(), this);
        } else {
            getLogger().info("AutoArmor " + Messages.getString("SA.OptionDisabled"));
        }

        // FULLINV
        getServer().getPluginManager().registerEvents(new FullInvEvent(), this);

        // DEATHBOOK
        if (getConfig().getBoolean("DeathBook.Enabled")) {
            getServer().getPluginManager().registerEvents(new PlayerDeadEvent(), this);
        } else {
            getLogger().info("DeathBook " + Messages.getString("SA.OptionDisabled"));
        }

        // UPDATE
        getServer().getPluginManager().registerEvents(new UpdateEvent(), this);

    }

    private void updateCheck() {
        getLogger().info(Messages.getString("SA.UpdateCheck"));
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
            plugin.getServer().addRecipe(chest.getRecipe());
        } else {
            getLogger().info(Messages.getString("SA.CustomRecipesDisabled"));
        }
    }

    public void setConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
