package si.zbe.smalladd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import si.zbe.smalladd.utils.TitleManager;

public class FullInvEvent implements Listener {
    @EventHandler
    public void onPickUp(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();

        if (p.getInventory().firstEmpty() == -1) {
            TitleManager tm = new TitleManager();
            tm.sendTitleActionBar(p, ChatColor.RED + "Your inventory is full!", 50, 100, 50);
        }
    }
}
