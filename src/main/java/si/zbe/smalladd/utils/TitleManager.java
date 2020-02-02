package si.zbe.smalladd.utils;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleManager {

    /*
    *   This will probably break every few updates
    *   Most of these haven't even been tested
     */

    public void sendTitleActionBar(Player player, String message) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + message + "\"}"), 25, 50, 25);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    public void sendTitleActionBar(Player player, String message, int fadein, int duration, int fadeout) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + message + "\"}"), fadein, duration, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    public void sendTitle(Player player, String title) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + title + "\"}"), 25, 50, 25);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
    }

    public void sendTitle(Player player, String title, int fadein, int duration, int fadeout) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + title + "\"}"), fadein, duration, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
    }

    public void sendSubtitle(Player player, String subtitle) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + subtitle + "\"}"), 25, 50, 25);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
    }

    public void sendSubtitle(Player player, String subtitle, int fadein, int duration, int fadeout) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + subtitle + "\"}"), fadein, duration, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
    }

    public void sendTitleAndSubtitle(Player player, String title, String subtitle) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + title + "\"}"), 25, 50, 25);
        PacketPlayOutTitle psubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + subtitle + "\"}"), 25, 50, 25);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(psubtitle);
    }

    public void sendTitleAndSubtitle(Player player, String title, String subtitle, int fadein, int duration, int fadeout) {
        PacketPlayOutTitle ptitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + title + "\"}"), fadein, duration, fadeout);
        PacketPlayOutTitle psubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + subtitle + "\"}"), fadein, duration, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ptitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(psubtitle);
    }
}
