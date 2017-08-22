package com.pixelgriffin.simplespawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class SimpleListener implements Listener {
	
	@EventHandler
	public void onPlayerMineSpawner(BlockBreakEvent evt) {
		if(evt.isCancelled())
			return;
		
		if(!evt.getBlock().getType().equals(Material.MOB_SPAWNER))
			return;
		
		if(evt.getPlayer().hasPermission("SimpleSpawner.get")) {
			if(!evt.getPlayer().hasPermission("SimpleSpawner.get.nosilk")) {
				if(!evt.getPlayer().getItemInHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
					return;
				}
			}
		} else {
			return;
		}
		
		evt.setCancelled(true);
		
		CreatureSpawner s = (CreatureSpawner)evt.getBlock().getState();
		s.getWorld().dropItem(s.getLocation(), SimpleCommands.getSpawnerItem(1, s.getSpawnedType()));
		evt.getBlock().setType(Material.AIR);
	}
	
	@EventHandler
	public void onSpawnerPlaced(BlockPlaceEvent evt) {
		if(evt.isCancelled())
			return;
		
		ItemStack item = evt.getItemInHand();
		
		if(item == null)
			return;
		
		System.out.println("Item not null");
		
		if(item.getType().equals(Material.MOB_SPAWNER)) {			
			String sType;
			
			System.out.println("Mob spawner placed");
			
			if(item.getItemMeta().getLore() == null) {
				sType = "Pig Spawner";
			} else {
				sType = item.getItemMeta().getLore().get(0);
			}
			
			Block setBlock = evt.getBlock();
			setBlock.setType(Material.MOB_SPAWNER);
			CreatureSpawner s = (CreatureSpawner)setBlock.getState();
			s.setSpawnedType(EntityType.valueOf(sType.split(" ")[0].toUpperCase()));
		}
	}
}
