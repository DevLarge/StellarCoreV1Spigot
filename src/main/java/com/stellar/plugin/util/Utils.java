package com.stellar.plugin.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Utils {
	
	public static String c(String colorTextHere) {
		return ChatColor.translateAlternateColorCodes('&', colorTextHere);
	}
	
	public static void console(String consoleMessage) {
		ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
		consoleCommandSender.sendMessage(Utils.c(consoleMessage));
	}
	
	
	public static final String PLUGIN_VERSION = c(
			"&fVersion: &f&l 1.2" + "\n" + 
			"&7Tested on: &f[1.15.2, 1.12.2, 17.10]" + "\n" +
			"&aAuthor:&b Large/Oscar" + "\n" +
			"&aIf you find any bugs with the plugin, make sure to tell &cDiscord: Oscar#8373");
	
	public static final String NoPerm = c("&cYou don't have permission to use this command");
	

}
