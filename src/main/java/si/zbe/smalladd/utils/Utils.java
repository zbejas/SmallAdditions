package si.zbe.smalladd.utils;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {
    public void sendTitleActionBar(Player p, String message) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + message + "\"}"), 50, 80, 25);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
    }
}
