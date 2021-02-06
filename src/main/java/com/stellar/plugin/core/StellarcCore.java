package com.stellar.plugin.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stellar.plugin.Main;
import com.stellar.plugin.util.Utils;

public class StellarcCore implements CommandExecutor {
	
	public Main main; 
	public StellarcCore(Main main) {
		this.main = main;
	}
	

	String StellarPrefix = Utils.c("&7[&b&lStellarCore&7]&f: ");
	
public static ArrayList<Player> staffchat = new ArrayList<>();
public static ArrayList<Player> adminchat = new ArrayList<>();
	
	String staffPrefix = Utils.c("&7[&bSC&7]&6 ");
	String staffEnabled = Utils.c(staffPrefix + "&bStaffChat &3has been &2Enabled");
	String staffDisabled = Utils.c(staffPrefix + "&bStaffChat &3has been &cDisabled");
	
	String adminPrefix = Utils.c("&7[&4&lAC&7]&c ");
	String adminEnabled = Utils.c(adminPrefix + "&cAdminChat &3has been &2Enabled");
	String adminDisabled = Utils.c(adminPrefix + "&cAdminChat &3has been &4Disabled");
	
	
	String usage = Utils.c(StellarPrefix + "&7Wrong usage. Try /scc help");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (args.length == 0) {
				p.sendMessage(usage);
				p.sendMessage(Utils.c(Utils.PLUGIN_VERSION));
				return true;
			} 
					
				if (args [0].equalsIgnoreCase("staff")) {
					if (p.hasPermission("stellarcore.staffchat")) {
						
						if (args.length > 1) {
							String msg = ""; 
							for(int i = 1; i < args.length; i ++) {
								msg = msg + " " + args[i];
							}
							
							for (Player staff : Bukkit.getOnlinePlayers()) {
								if (staff.hasPermission("stellarcore.staffchat")) {
									staff.sendMessage(staffPrefix + p.getDisplayName() + ChatColor.WHITE + ":" + ChatColor.YELLOW + msg);
								}
							}
							return false;
						}
						
						if (adminchat.contains(p)) {
							adminchat.remove(p);
						}
						if (staffchat.contains(p)) {
							
							staffchat.remove(p);
							p.sendMessage(staffDisabled);
							return true;
							
						} else {
							staffchat.add(p);
							p.sendMessage(staffEnabled);
							return true;
						} 
						
					} else {
						p.sendMessage(Utils.NoPerm);
					}
					
				} 
				
				if (args [0].equalsIgnoreCase("admin")) {
					if (p.hasPermission("stellarcore.adminchat")) {
						
						if (args.length > 1) {
							String msg = ""; 
							for(int i = 1; i < args.length; i ++) {
								msg = msg + " " + args[i];
							}
							
							for (Player admins : Bukkit.getOnlinePlayers()) {
								if (admins.hasPermission("stellarcore.adminchat")) {
									admins.sendMessage(adminPrefix + p.getDisplayName() + ChatColor.WHITE + ":" + ChatColor.DARK_GREEN + msg);
								}
							}
							return false;
						}
						
						if(staffchat.contains(p)) {
							staffchat.remove(p);
						}
						if (adminchat.contains(p)) {
							
							adminchat.remove(p);
							p.sendMessage(adminDisabled);
							return true;
							
						} else {
							adminchat.add(p);
							p.sendMessage(adminEnabled);
							return true;
						}
					} else {
						p.sendMessage(Utils.NoPerm);
						return true;
					}
					
				}
				
				
				if (args [0].equalsIgnoreCase("autowhitelist")) {
					if (p.hasPermission("stellarcore.autowhitelist")) {
						
						if (args.length == 1) {
							p.sendMessage(Utils.c("&bAutowhitelist is currently &f" + main.getConfig().getBoolean("WhitelistOnStart")));
							p.sendMessage(Utils.c("&7Usage: /scc autowhitelist <true/false>"));
							return true;
						}
						
						
						if (args [1].equalsIgnoreCase("true")) {
							p.sendMessage("WhitelistOnStart set to true");
							main.getConfig().set("WhitelistOnStart", true);
							main.saveConfig();
							return true;
						} else if (args [1].equalsIgnoreCase("false")) {
							p.sendMessage("WhitelistOnStart set to false");
							main.getConfig().set("WhitelistOnStart", false);
							main.saveConfig();
							return true;
						} else {
							p.sendMessage(Utils.c("&cNot a valid argument"));
							return true;
						}
						
					} else {
						p.sendMessage(Utils.NoPerm);
						return true;
					}
				}
				
				
				
				if (args [0].equalsIgnoreCase("configreload")) {
					if (p.hasPermission("stellarcore.configreload")) {
						p.sendMessage(Utils.c(StellarPrefix + "Config reloaded"));
						Bukkit.reload();
						return true;
					} else {
						p.sendMessage(Utils.NoPerm);
						return true;
					}
					
				}
				
				
				
				if (args [0].equalsIgnoreCase("version")) {
					p.sendMessage(Utils.PLUGIN_VERSION);
					return false;
				}
					
				
				if (args [0].equalsIgnoreCase("help")) {
					String configStaffbcEndsWith = main.getConfig().getString("StaffbroadcastSuffix");
					
					if (p.hasPermission("stellarcore.help")) {
						p.sendMessage(StellarPrefix);
						p.sendMessage(Utils.c("&f/scc staff [message] &7- Toggles StaffChat, or sends message. Check /staffchatprefix info"));
						p.sendMessage(Utils.c("&f/scc admin [message] &7- Toggles AdminChat, or sends message. Check /adminchatprefix info"));
						p.sendMessage(Utils.c("&f/clearchat [user] &7- Clears chat for everyone or user - [cc]"));
						p.sendMessage(Utils.c("&f/staffbroadcast [message] [-" + configStaffbcEndsWith + "] &7- Broadcasts to all online staff. Use -" + configStaffbcEndsWith + " to make it important - [scb, staffbc]"));
						p.sendMessage(Utils.c("&f/staffchatprefix [info] &7- Checks prefix for StaffChat. (prefix - config.yml) - [staffprefix, sprefix, scprefix]"));
						p.sendMessage(Utils.c("&f/adminchatprefix [info] &7- Checks prefix for AdminChat. (prefix - config.yml) - [adminprefix, aprefix, acprefix]"));
						return false;
					} else {
						p.sendMessage(Utils.NoPerm);
						return false;
					}
				}
				
				
	} else {
		System.out.println("Need to be a player to run command");
		return false;
	}
		
		
		return false;
	}
}
