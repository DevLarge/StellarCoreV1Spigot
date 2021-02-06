package com.stellar.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stellar.plugin.util.Utils;

public class MsgAdmin implements CommandExecutor {
	
	private Player p;
	private String staffPrefix;
	private boolean isStaffAvailable;
	private boolean isMessageSent;
	
	private String msg;
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) throws CommandException {
		
		// Set boolean
		isMessageSent = false;
		
		// Create msg
		msg = "";
		for (int i = 0; i < args.length; i++) {
			msg = msg + args[i] + " ";
		}
		
		// Command for players
		if (sender instanceof Player) {
			p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(Utils.c("&7Usage: /msgadmin (msg)"));
				return true;
			}
			
			
			// Args over 1
			for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
				
				if (allPlayers.hasPermission("stellarcore.msgadmin.isadmin")) {
					System.out.println("isAdmin true");
					isStaffAvailable = true;
					sendMessageToStaff();
					return true;
				} else {
					System.out.println("isAdmin false");
					isStaffAvailable = false;
					isMessageSent = true;
				}
			}
			
			if (isMessageSent) {
				p.sendMessage(Utils.c("&cNo staff available"));
			}
			
			
			
		} else {
			System.out.println("Console cannot run this command");
			return true;
		}
		return false;
	}


	private void sendMessageToStaff() {
		if (isStaffAvailable) {
			staffPrefix = Utils.c("&7[&6MsgStaff&7] " + p.getDisplayName() + "&7: ");
			for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
				if (staff.hasPermission("stellarcore.msgadmin.isadmin")) {
					staff.sendMessage(staffPrefix + ChatColor.GREEN + msg);
				}
			}
			p.sendMessage(Utils.c("&6Sent message to staff: " + ChatColor.GREEN + msg));
			return;
		}
		
	}

}
