package com.pixelgriffin.simplespawners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(cmd.getName().equalsIgnoreCase("ss")) {
			if(args.length == 4) {
				if(args[0].equalsIgnoreCase("give") && sender.hasPermission("SimpleSpawner.give")) {
					Player p = Bukkit.getPlayer(args[1]);
					
					if(p != null) {
						try {
							EntityType type = EntityType.valueOf(args[2].toUpperCase());
							if(type != null) {
								ItemStack item = getSpawnerItem(Integer.valueOf(args[3]), type);
								p.getInventory().addItem(item);
							} else {
								sender.sendMessage("Unknown entity type "+args[2]);
							}
						} catch(Exception e) {
							sender.sendMessage("Unknown entity type '" + args[2] + "'");
						}
					} else {
						sender.sendMessage("player does not exist.");
					}
				}
			} else {
				sender.sendMessage("/ss give [player] [type] [amount]");
			}
		}
		
		return true;
	}
	
	public static ItemStack getSpawnerItem(int amount, EntityType type) {
		ItemStack item = new ItemStack(Material.MOB_SPAWNER, amount);
		List<String> lore = new ArrayList<String>();
		
		String loreString = type.toString();
		loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
		loreString = loreString+" Spawner";
		lore.add(loreString);
		
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
}
