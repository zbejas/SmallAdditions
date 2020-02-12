package si.zbe.smalladd.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import si.zbe.smalladd.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DeathManager {
    File deathYml = new File(Main.plugin.getDataFolder() + "/data/death_data.yml");
    FileConfiguration deathConfig = YamlConfiguration.loadConfiguration(deathYml);

    public void saveDeathData() {
        try {
            deathConfig.save(deathYml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeath(Player player, Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        String world = player.getWorld().getName();
        UUID uuid = player.getUniqueId();

        String location = x + ":" + y + ":" + z + ":" + yaw + ":" + pitch + ":" + world;

        ArrayList <String> list = new ArrayList <String>(deathConfig.getStringList(uuid.toString()));
        list.add(location);


        deathConfig.set(uuid.toString(), list);
    }

    public void removeDeath(Player player, Location loc) {
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        String world = player.getWorld().getName();
        UUID uuid = player.getUniqueId();

        String location = x + ":" + y + ":" + z + ":" + yaw + ":" + pitch + ":" + world;

        ArrayList <String> list = new ArrayList <String>(deathConfig.getStringList(uuid.toString()));
        list.remove(location);

        deathConfig.set(uuid.toString(), list);
    }

    public ArrayList <Location> getDeaths(Player p) {
        ArrayList <Location> list = new ArrayList <>();
        for (String s : deathConfig.getStringList(p.getUniqueId().toString())) {
            
            try {
                String[] parts = s.split(":");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);
                float yaw = Float.parseFloat(parts[3]);
                float pitch = Float.parseFloat(parts[4]);
                World world = Bukkit.getWorld(parts[5]);

                Location loc = new Location(world, x, y, z, yaw, pitch);

                list.add(loc);
            } catch (Exception e) {
                p.sendMessage(ChatColor.RED + "No known death locations.");
            }
        }
        return list;
    }


}
