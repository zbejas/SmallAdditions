package si.zbe.smalladd.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperExplodeEvent implements Listener {
    @EventHandler
    public void onCreeper(EntityExplodeEvent e) {
        if (e.getEntity().getType() != EntityType.CREEPER)
            return;
        e.setCancelled(true);
    }
}
