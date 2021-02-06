package com.stellar.plugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.stellar.plugin.Main;
import com.stellar.plugin.util.Utils;

public class StaffAdminChatPrefixEvent implements Listener {
	
	public Main main;
	public StaffAdminChatPrefixEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer();
		
		String configStaffChatPrefix = main.getConfig().getString("StaffChatPrefix");
		String configAdminChatPrefix = main.getConfig().getString("AdminChatPrefix");
		String staffPrefix = Utils.c("&7[&bSC&7]&6 ");
		String adminPrefix = Utils.c("&7[&4&lAC&7]&c ");
		String staffMsg = e.getMessage().replace(configStaffChatPrefix, "");
		String adminMsg = e.getMessage().replace(configAdminChatPrefix, "");
		
		if (p.hasPermission("stellarcore.staffchat")) {
			if (e.getMessage().startsWith(configStaffChatPrefix)) {
				e.setCancelled(true);
				
				for (Player staff : Bukkit.getOnlinePlayers()) {
					if (staff.hasPermission("stellarcore.staffchat")) {
						staff.sendMessage(staffPrefix + p.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.YELLOW + staffMsg.trim());
					}
				}
				
			}
		
		}
		
		if (p.hasPermission("stellarcore.adminchat")) {
			if (e.getMessage().startsWith(configAdminChatPrefix)) {
				e.setCancelled(true);
				
				for (Player admins : Bukkit.getOnlinePlayers()) {
					if (admins.hasPermission("stellarcore.adminchat")) {
						admins.sendMessage(adminPrefix + p.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.DARK_GREEN + adminMsg.trim());
					}
				}
				
			}
		
		}

  }
}
