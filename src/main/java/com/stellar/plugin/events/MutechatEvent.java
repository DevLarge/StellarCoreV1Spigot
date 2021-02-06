package com.stellar.plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.stellar.plugin.commands.MutechatCommand;
import com.stellar.plugin.util.Utils;

public class MutechatEvent implements Listener {
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		
		if (MutechatCommand.isChatMuted == true) {
			if (!p.hasPermission("stellarcore.mutechat.bypass")) {
				event.setCancelled(true);
				p.sendMessage(Utils.c("&cChat is muted"));
				return;
			}
			
		}
	}

}
