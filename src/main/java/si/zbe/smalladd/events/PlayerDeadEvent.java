package si.zbe.smalladd.events;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import si.zbe.smalladd.Main;
import si.zbe.smalladd.utils.DeathManager;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerDeadEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        DeathManager dm = new DeathManager();
        Player p = e.getEntity().getPlayer();

        if (p == null)
            return;

        if (dm.getDeaths(p).size() > 12) {
            int counter = dm.getDeaths(p).size() - 12;
            for (int i = 0; i <= counter; i++) {
                dm.removeDeath(p, dm.getDeaths(p).get(0));
            }
        }

        dm.addDeath(p, p.getLocation());
        dm.saveDeathData();
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        if (!Main.plugin.getConfig().getBoolean("DeathBook.BookOnRespawn"))
            return;

        if (!e.getPlayer().hasPermission("smalladd.deathbook"))
            return;

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        DeathManager dm = new DeathManager();
        String deaths = "\n\n";

        ArrayList<Location> list = dm.getDeaths(e.getPlayer());

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

        BaseComponent[] lastdeath = new ComponentBuilder( ChatColor.BLACK + "" + ChatColor.BOLD + "     Last death    ")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sad teleport"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "Teleport to last death location.").create()))
                .append(deaths, ComponentBuilder.FormatRetention.NONE)
                .create();

        bookMeta.spigot().addPage(lastdeath);

        bookMeta.setTitle(ChatColor.BLACK + "" + ChatColor.BOLD + "Death Note");
        bookMeta.setAuthor(ChatColor.WHITE + "God of Death");

        book.setItemMeta(bookMeta);

        e.getPlayer().getInventory().addItem(book);
    }
}
