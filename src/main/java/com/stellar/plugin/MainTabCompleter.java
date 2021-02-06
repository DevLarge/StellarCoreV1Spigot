package com.stellar.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MainTabCompleter implements TabCompleter {
	
	
	/*
	 * REMEMBER TO REGISTER TABCOMPLETER IN MAIN CLASS
	 */
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender.hasPermission("stellarcore.arg")) {
			
			if (cmd.getName().equalsIgnoreCase("stellarc")) {
				if (args.length == 1) {
					List<String> arg = new ArrayList<>();
					arg.add("help");
					arg.add("version");
					arg.add("configreload");
					arg.add("autowhitelist");
					arg.add("staff");
					arg.add("admin");
					
					return arg;
				}
			}
			
			
			else if (cmd.getName().equalsIgnoreCase("clearchat")) {
				if (args.length == 1) {
					List<String> arg = new ArrayList<>();
					
					//Players
					
					Player[] players = new Player[Bukkit.getOnlinePlayers().size()];
					Bukkit.getServer().getOnlinePlayers().toArray(players);
					for (int x = 0; x < players.length; x++) {
						arg.add(players[x].getName());
					}
					
					return arg;
				}
			}
			
			else if (cmd.getName().equalsIgnoreCase("staffchatprefix")) {
				if (args.length == 1) {
					List<String> arg = new ArrayList<>();
					
					arg.add("info");
					
					return arg;
				}
			}
			
			else if (cmd.getName().equalsIgnoreCase("adminchatprefix")) {
				if (args.length == 1) {
					List<String> arg = new ArrayList<>();
					
					arg.add("info");
					
					return arg;
				}
			}
			

		}

		return null;
		
	}

}
