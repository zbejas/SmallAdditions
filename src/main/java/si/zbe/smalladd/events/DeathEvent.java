package si.zbe.smalladd.events;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityStatus;

public class DeathEvent implements Listener {
	@EventHandler
	public void onDeath(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (!p.getInventory().containsAtLeast(new ItemStack(Material.TOTEM_OF_UNDYING), 1))
			return;
		
		if (p.getHealth() - e.getDamage() < 1) {
			p.getInventory().remove(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
			p.setHealth(20);
			EntityPlayer ep = ((CraftPlayer)p).getHandle();

			PacketPlayOutEntityStatus status = new PacketPlayOutEntityStatus(ep, (byte) 35);
			ep.playerConnection.sendPacket(status);
		}
		
	}
}
