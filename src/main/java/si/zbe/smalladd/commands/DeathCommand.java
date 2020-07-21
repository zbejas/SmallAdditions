package si.zbe.smalladd.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import si.zbe.smalladd.Main;
import si.zbe.smalladd.Messages;
import si.zbe.smalladd.utils.DeathManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeathCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private final Main plugin;

    public DeathCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.getString("SA.NotAPlayer"));
        }
        Player p = (Player) sender;

        if (args.length == 0) {

            if (!p.hasPermission("smalladd.deathbook")) {
                p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
                return true;
            }

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();

            DeathManager dm = new DeathManager();
            String deaths = "\n\n";

            ArrayList <Location> list = dm.getDeaths(p);

            Collections.reverse(list);

            boolean first = true;
            for (Location loc : list) {
                if (first) {
                    deaths += ChatColor.BLACK + " - "
                            + ChatColor.DARK_GREEN + "" + (int) loc.getX() + ChatColor.BLACK + ":"
                            + ChatColor.DARK_GREEN + "" + (int) loc.getY() + ChatColor.BLACK + ":"
                            + ChatColor.DARK_GREEN + "" + (int) loc.getZ() + "\n";
                    first = false;
                } else {
                    deaths += ChatColor.BLACK + " - "
                            + ChatColor.DARK_RED + "" + (int) loc.getX() + ChatColor.BLACK + ":"
                            + ChatColor.DARK_RED + "" + (int) loc.getY() + ChatColor.BLACK + ":"
                            + ChatColor.DARK_RED + "" + (int) loc.getZ() + "\n";
                }
            }

            BaseComponent[] lastdeath = new ComponentBuilder(ChatColor.BLACK + "" + ChatColor.BOLD + "     Last death    ")
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sad teleport"))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "Teleport to last death location.").create()))
                    .append(deaths, ComponentBuilder.FormatRetention.NONE)
                    .create();

            bookMeta.spigot().addPage(lastdeath);

            bookMeta.setTitle(ChatColor.BLACK + "" + ChatColor.BOLD + "Death Note");
            bookMeta.setAuthor(ChatColor.WHITE + "God of Death");

            book.setItemMeta(bookMeta);

            p.getInventory().addItem(book);
            return true;
        } else if (args.length == 1) {
            DeathManager dm = new DeathManager();

            if (args[0].equalsIgnoreCase("teleport")) {

                if (!p.hasPermission("smalladd.deathbook.teleport")) {
                    p.sendMessage(ChatColor.RED + Messages.getString("SA.NoPerm"));
                    return true;
                }

                try {
                    Location loc = dm.getDeaths(p).get(dm.getDeaths(p).size() - 1);

                    List <String> worlds = Main.plugin.getConfig().getStringList("DeathBook.BannedWorlds");

                    for (String s : worlds) {
                        if (s.equalsIgnoreCase(loc.getWorld().getName())) {
                            p.sendMessage(ChatColor.RED + Messages.getString("SA.WorldDisabled"));
                            return true;
                        }
                    }

                    p.teleport(loc);
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + Messages.getString("SA.NoSavedDeaths"));
                }
            }

            return true;
        } else {
            p.sendMessage(ChatColor.RED + Messages.getString("SA.InvalidInput"));
        }

        return true;
    }
}
