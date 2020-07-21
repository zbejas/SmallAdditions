package si.zbe.smalladd.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.smalladd.Main;
import si.zbe.smalladd.commands.AutoArmorCommand;

public class AutoArmorEvent implements Listener {
    @EventHandler
    public void onAutoArmor(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("smalladd.tool.armor"))
            return;

        if (!AutoArmorCommand.playerlist.contains(p))
            return;

        for (ItemStack i : p.getInventory()) {
            if (i != null) {
                if (isHelmet(i.getType())) {
                    Material helmet = Material.AIR;
                    if (p.getInventory().getHelmet() != null)
                        helmet = p.getInventory().getHelmet().getType();

                    if (getArmorPriority(helmet) < getArmorPriority(i.getType())) {
                        ItemStack oldhelmet = p.getInventory().getHelmet();

                        p.getInventory().remove(i);
                        p.getInventory().setHelmet(i);
                        if (oldhelmet != null)
                            p.getInventory().addItem(oldhelmet);
                    }
                }
                if (isChestplate(i.getType())) {
                    Material chestplate = Material.AIR;
                    if (p.getInventory().getChestplate() != null)
                        chestplate = p.getInventory().getChestplate().getType();

                    if (getArmorPriority(chestplate) < getArmorPriority(i.getType())) {
                        ItemStack oldplate = p.getInventory().getChestplate();

                        p.getInventory().remove(i);
                        p.getInventory().setChestplate(i);
                        if (oldplate != null)
                            p.getInventory().addItem(oldplate);
                    }
                }
                if (isLeggings(i.getType())) {
                    Material leggings = Material.AIR;
                    if (p.getInventory().getLeggings() != null)
                        leggings = p.getInventory().getLeggings().getType();

                    if (getArmorPriority(leggings) < getArmorPriority(i.getType())) {
                        ItemStack oldleggings = p.getInventory().getLeggings();

                        p.getInventory().remove(i);
                        p.getInventory().setLeggings(i);
                        if (oldleggings != null)
                            p.getInventory().addItem(oldleggings);
                    }
                }
                if (isBoots(i.getType())) {
                    Material boots = Material.AIR;
                    if (p.getInventory().getBoots() != null)
                        boots = p.getInventory().getBoots().getType();

                    if (getArmorPriority(boots) < getArmorPriority(i.getType())) {
                        ItemStack oldboots = p.getInventory().getBoots();

                        p.getInventory().remove(i);
                        p.getInventory().setBoots(i);
                        if (oldboots != null)
                            p.getInventory().addItem(oldboots);
                    }
                }
            }
        }
    }

    int getArmorPriority(Material m) {
        if (m == Material.LEATHER_HELMET || m == Material.LEATHER_CHESTPLATE || m == Material.LEATHER_LEGGINGS || m == Material.LEATHER_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Leather");
        else if (m == Material.GOLDEN_HELMET || m == Material.GOLDEN_CHESTPLATE || m == Material.GOLDEN_LEGGINGS || m == Material.GOLDEN_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Gold");
        else if (m == Material.CHAINMAIL_HELMET || m == Material.CHAINMAIL_CHESTPLATE || m == Material.CHAINMAIL_LEGGINGS || m == Material.CHAINMAIL_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Chain");
        else if (m == Material.IRON_HELMET || m == Material.IRON_CHESTPLATE || m == Material.IRON_LEGGINGS || m == Material.IRON_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Iron");
        else if (m == Material.DIAMOND_HELMET || m == Material.DIAMOND_CHESTPLATE || m == Material.DIAMOND_LEGGINGS || m == Material.DIAMOND_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Diamond");
        else if (m == Material.NETHERITE_HELMET || m == Material.NETHERITE_CHESTPLATE || m == Material.NETHERITE_LEGGINGS || m == Material.NETHERITE_BOOTS)
            return Main.plugin.getConfig().getInt("Tools.AutoArmor.Priorities.Netherite");
        else
            return 0;
    }

    boolean isHelmet(Material m) {
        return m == Material.LEATHER_HELMET || m == Material.IRON_HELMET || m == Material.CHAINMAIL_HELMET || m == Material.GOLDEN_HELMET || m == Material.DIAMOND_HELMET || m == Material.NETHERITE_HELMET;
    }

    boolean isChestplate(Material m) {
        return m == Material.LEATHER_CHESTPLATE || m == Material.IRON_CHESTPLATE || m == Material.CHAINMAIL_CHESTPLATE || m == Material.GOLDEN_CHESTPLATE || m == Material.DIAMOND_CHESTPLATE || m == Material.NETHERITE_CHESTPLATE;
    }

    boolean isLeggings(Material m) {
        return m == Material.LEATHER_LEGGINGS || m == Material.IRON_LEGGINGS || m == Material.CHAINMAIL_LEGGINGS || m == Material.GOLDEN_LEGGINGS || m == Material.DIAMOND_LEGGINGS || m == Material.NETHERITE_LEGGINGS;
    }

    boolean isBoots(Material m) {
        return m == Material.LEATHER_BOOTS || m == Material.IRON_BOOTS || m == Material.CHAINMAIL_BOOTS || m == Material.GOLDEN_BOOTS || m == Material.DIAMOND_BOOTS || m == Material.NETHERITE_BOOTS;
    }
}
