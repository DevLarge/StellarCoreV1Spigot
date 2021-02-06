package com.stellar.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.stellar.plugin.Main;
import com.stellar.plugin.util.Utils;

public class Clearchat implements CommandExecutor {
	
	public Main plugin;
	public Clearchat(Main pl) {
		this.plugin = pl;
		
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("stellarcore.clearchat.use")) {
			if (args.length == 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.hasPermission("stellarcore.clearchat.bypass")) {
						p.sendMessage(ChatColor.AQUA + "The chat was cleared by " + ChatColor.GRAY + sender.getName());
						System.out.println("Chat cleared");
					} else {
						for (int x = 0;x<100;x++) {
							p.sendMessage(" ");
						}
						p.sendMessage(ChatColor.AQUA + "The chat was cleared");
						
					}
				} return false;
			} 
			
			if (args [0].equalsIgnoreCase(args [0])) {
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("stellarcore.clearchat.others")) {
						Player target = Bukkit.getPlayerExact(args [0]);
						
						if (!(target instanceof Player)) {
							sender.sendMessage(ChatColor.RED + "Could not find player");
							return false;
						}
						
						for (int x = 0;x<100;x++) {
							target.sendMessage(" ");
						}
						
						if (target == sender) {
							target.sendMessage(ChatColor.AQUA + "Your chat was cleared");
							return false;
						}
						
						target.sendMessage(ChatColor.AQUA + "Your chat was cleared");
						sender.sendMessage(ChatColor.AQUA + "You cleared the chat for " + ChatColor.DARK_GREEN + target.getDisplayName());
						return false;
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission to clear other's chat");
					return false;
				}
				
			} else {
				sender.sendMessage("Error in syntax. Could not find [argument 0]");
				return true;
			}
			
			
		} else {
			sender.sendMessage(Utils.NoPerm);
		}

		return false;
	}

}
