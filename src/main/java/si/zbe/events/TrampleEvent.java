 package si.zbe.events;
 
 import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
 
 public class TrampleEvent implements Listener {
   @EventHandler
   public void onTrample(PlayerInteractEvent e) {
     if (e.getAction() == Action.PHYSICAL) {
       if (e.getClickedBlock() == null)
         return; 
       if (e.getPlayer().hasPermission("smalladd.notrample") && 
         e.getClickedBlock().getType() == Material.FARMLAND)
         e.setCancelled(true); 
     } 
   }
 }


