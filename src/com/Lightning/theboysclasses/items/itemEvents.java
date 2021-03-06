package com.Lightning.theboysclasses.items;

import com.Lightning.theboysclasses.Main;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class itemEvents implements Listener {

    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
        if (plugin.cdtime.containsKey(uuid)) {
            p.sendMessage(ChatMessageType.ACTION_BAR + "" + ChatColor.RED +  "You can't use that for another " + ChatColor.YELLOW + plugin.cdtime.get(uuid) + ChatColor.RED + " seconds.");
        } else {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (Objects.requireNonNull(event.getItem().getItemMeta()).equals(itemManager.BlazeWand.getItemMeta())) {
                        plugin.cdtime.put(uuid, plugin.mastercd);
                        Player player = event.getPlayer();
                        player.launchProjectile(Fireball.class);
                        player.launchProjectile(Fireball.class);
                        player.launchProjectile(Fireball.class);
                        Location location = p.getEyeLocation();
                        int radius = 2;

                        final double[] locationY = {0};
                        final double[] locationX = {0};

                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                p.spawnParticle(Particle.FLAME, location.add(0, 0, 0), 100);
                                locationX[0] = locationX[0] + 0.1;
                            }
                        }.runTaskTimerAsynchronously(Main.getPlugin(), 0, 5); //every half a second
                    }
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if (Objects.equals(e.getItemDrop().getItemStack().getItemMeta(), itemManager.BlazeWand.getItemMeta())) {
            e.setCancelled(true);
        }
    }


}
