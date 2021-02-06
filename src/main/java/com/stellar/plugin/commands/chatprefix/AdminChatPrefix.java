package com.stellar.plugin.commands.chatprefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stellar.plugin.Main;
import com.stellar.plugin.util.Utils;

public class AdminChatPrefix implements CommandExecutor {
	
	public Main main; 
	public AdminChatPrefix(Main main) {
		this.main = main; 
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		Player p = (Player) sender;
		
		String info = main.getConfig().getString("AdminChatPrefix");
		
		if (sender instanceof Player) {
			if (p.hasPermission("stellarcore.adminchatprefix")) {
				
				if (args.length == 0) {
					p.sendMessage(Utils.c("&7Wrong usage: Try to add args."));
					return true;
				}
				
				if (args [0].equalsIgnoreCase("info")) {
					if (args.length == 1) {
						if (p.hasPermission("stellarcore.adminchatprefix.info")) {
							p.sendMessage(Utils.c("&3Current prefix for &cAdminChat&3 is :&a " + info));
							return true;
						} else {
							p.sendMessage(Utils.NoPerm);
							return true;
						}
					} else {
						p.sendMessage(Utils.c("&7Too many arguments"));
						return true;
					}
				}
				if (args.length > 1) {
					p.sendMessage(Utils.c("&7Too many arguments"));
					return true;
				}
				
			} else {
				p.sendMessage(Utils.NoPerm);
			}
		} else {
			System.out.println("Need to be player to use this command" + "----- Note: Add console command -----");
		}
		
		return false;
	}
	

}