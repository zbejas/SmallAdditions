package si.zbe.smalladd.utils;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleManager {

    /*
    *   This will probably break every few updates
    *   Lol titles are now in spigot, nevermind
     */

    public void sendTitleActionBar(Player player, String message) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + message + "\"}"), 25, 50, 25);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    public void sendTitleActionBar(Player player, String message, int fadein, int duration, int fadeout) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + message + "\"}"), fadein, duration, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }
}
