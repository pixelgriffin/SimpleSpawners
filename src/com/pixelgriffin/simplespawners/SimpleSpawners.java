package com.pixelgriffin.simplespawners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;


public class SimpleSpawners extends JavaPlugin {
	
	private SimpleListener listener = new SimpleListener();
	private SimpleCommands exec = new SimpleCommands();
	
	@Override
	public void onEnable() {
		//create listener.
		
		Bukkit.getPluginManager().registerEvents(listener, this);
		
		getCommand("ss").setExecutor(exec);
	}
}
