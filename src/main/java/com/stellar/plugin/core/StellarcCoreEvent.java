package com.stellar.plugin.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.stellar.plugin.Main;
import com.stellar.plugin.util.Utils;

public class StellarcCoreEvent implements Listener {
	
	public Main plugin; 
	public StellarcCoreEvent(Main pl) {
		this.plugin = pl;
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer(); 
		
		String staffPrefix = Utils.c("&7[&bSC&7]&6 "); 
		String adminPrefix = Utils.c("&7[&4&lAC&7]&c ");
		String msg = e.getMessage();
		
		if (StellarcCore.staffchat.contains(p)) {
			
			e.setCancelled(true);
			
			for (Player staff : Bukkit.getOnlinePlayers()) {
				
				if (staff.hasPermission("stellarcore.staffchat")) {
					
					staff.sendMessage(staffPrefix + p.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + msg);
				}
			}
			
		}
		
		if (StellarcCore.adminchat.contains(p)) {
			
			e.setCancelled(true);
			
			for (Player admins : Bukkit.getOnlinePlayers()) {
				
				if (admins.hasPermission("stellarcore.adminchat")) {
					
					admins.sendMessage(adminPrefix + p.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.DARK_GREEN + msg);
				}
			}
		}
	}
	
}
