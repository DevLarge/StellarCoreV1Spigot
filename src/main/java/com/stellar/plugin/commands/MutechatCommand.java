package com.stellar.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stellar.plugin.util.Utils;

public class MutechatCommand implements CommandExecutor {
	
	public static boolean isChatMuted = false;

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("stellarcore.mutechat")) {
				if (this.isChatMuted == false) {
					this.isChatMuted = true;
					Bukkit.getServer().broadcastMessage(Utils.c("&6Mutechat &aenabled"));
					return true;
				} else {
					this.isChatMuted = false;
					Bukkit.getServer().broadcastMessage(Utils.c("&6Mutechat &cdisabled"));
					return false;
				}
			} else {
				p.sendMessage(Utils.NoPerm);
				return true;
			}
		} else {
			
			if (this.isChatMuted == false) {
				this.isChatMuted = true;
				Utils.console("&6Mutechat &5enabled");
				return true;
			} else {
				this.isChatMuted = false;
				Utils.console("&6Mutechat &cdisabled");
				return false;
			}
			
		}
		
	}

}
