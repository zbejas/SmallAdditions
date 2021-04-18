package si.zbe.smalladd.events;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.PacketPlayOutAnimation;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CropEvent implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!isCrop(block.getType()))
                return;

            harvestCrop(block.getType(), e);
        }
    }

    public boolean isCrop(Material m) {
        return m == Material.WHEAT || m == Material.POTATOES || m == Material.CARROTS || m == Material.BEETROOTS
                || m == Material.NETHER_WART;
    }

    public Material getSeed(Material m) {
        if (m == Material.WHEAT)
            return Material.WHEAT_SEEDS;
        else if (m == Material.POTATOES)
            return Material.POTATO;
        else if (m == Material.CARROTS)
            return Material.CARROT;
        else if (m == Material.BEETROOTS)
            return Material.BEETROOT_SEEDS;
        else if (m == Material.NETHER_WART)
            return Material.NETHER_WART;
        else
            return Material.AIR;
    }

    public int getMaxGrowth(Material m) {
        if (m == Material.WHEAT || m == Material.CARROTS || m == Material.POTATOES)
            return 7;
        else if (m == Material.BEETROOTS || m == Material.NETHER_WART)
            return 3;
        else
            return 0;
    }

    public String getCropPerm(Material m) {
        if (m == Material.WHEAT)
            return "wheat";
        else if (m == Material.POTATOES)
            return "potato";
        else if (m == Material.CARROTS)
            return "carrot";
        else if (m == Material.BEETROOTS)
            return "beetroot";
        else if (m == Material.NETHER_WART)
            return "netherwart";
        else
            return null;
    }

    public void harvestCrop(Material m, PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (isCrop(m)) {
            if (!e.getPlayer().hasPermission("smalladd.crop." + getCropPerm(m))) {
                return;
            }

            if (block.getData() != getMaxGrowth(m)) {
                return;
            }

            boolean SeedInDrop = false;
            for (ItemStack is : block.getDrops()) {
                if (is.getType() == getSeed(m)) {
                    SeedInDrop = true;
                }
            }


            PacketPlayOutAnimation animation = new PacketPlayOutAnimation(((CraftPlayer) e.getPlayer()).getHandle(), 0);
            ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(animation);

            if (SeedInDrop) {
                block.getDrops().remove(new ItemStack(getSeed(m), 1));
                ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(block.getX(), block.getY(), block.getZ()));
                block.breakNaturally();
                if (block.getType() == Material.NETHER_WART) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NETHER_WART_BREAK, 10, 1);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_NETHER_WART_PLANT, 8, 1);
                } else {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CROP_BREAK, 10, 1);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_CROP_PLANT, 8, 1);
                }
                block.setType(m);
            } else if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(getSeed(m)), 1)) {
                e.getPlayer().getInventory().remove(new ItemStack(getSeed(m), 1));
                ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(block.getX(), block.getY(), block.getZ()));
                block.breakNaturally();
                if (block.getType() == Material.NETHER_WART) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NETHER_WART_BREAK, 10, 1);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_NETHER_WART_PLANT, 8, 1);
                } else {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CROP_BREAK, 10, 1);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_CROP_PLANT, 8, 1);
                }
                block.setType(m);
            } else {
                ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(block.getX(), block.getY(), block.getZ()));
                block.breakNaturally();
                if (block.getType() == Material.NETHER_WART) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NETHER_WART_BREAK, 10, 1);
                } else {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CROP_BREAK, 10, 1);
                }
            }
        }
    }
}