package com.stellar.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stellar.plugin.commands.Clearchat;
import com.stellar.plugin.commands.MsgAdmin;
import com.stellar.plugin.commands.MutechatCommand;
import com.stellar.plugin.commands.chatprefix.AdminChatPrefix;
import com.stellar.plugin.commands.chatprefix.StaffChatPrefix;
import com.stellar.plugin.core.StellarcCore;
import com.stellar.plugin.core.StellarcCoreEvent;
import com.stellar.plugin.events.MutechatEvent;
import com.stellar.plugin.events.StaffAdminChatPrefixEvent;
import com.stellar.plugin.util.Utils;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		
		
		Utils.console("&bStellarCore enabled :D");
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		initWhitelist();
		registerCommands();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new StellarcCoreEvent(this), this);
		pm.registerEvents(new StaffAdminChatPrefixEvent(this), this);
		pm.registerEvents(new MutechatEvent(), this);
	}
	
	private void registerCommands() {
		getCommand("stellarc").setExecutor(new StellarcCore(this));
		getCommand("stellarc").setTabCompleter(new MainTabCompleter());
		
		getCommand("clearchat").setExecutor(new Clearchat(this));
		getCommand("clearchat").setTabCompleter(new MainTabCompleter());
		
		getCommand("mutechat").setExecutor(new MutechatCommand());
		
		getCommand("msgadmin").setExecutor(new MsgAdmin());
		
		getCommand("staffchatprefix").setExecutor(new StaffChatPrefix(this));
		getCommand("adminchatprefix").setExecutor(new AdminChatPrefix(this));
		getCommand("staffchatprefix").setTabCompleter(new MainTabCompleter());
		getCommand("adminchatprefix").setTabCompleter(new MainTabCompleter());
	}
	
	
	public void initWhitelist() {
		if (getConfig().getBoolean("WhitelistOnStart"))  {
			System.out.println("Whitelist enabled");
			Bukkit.getServer().setWhitelist(true);
		} else {
			System.out.println("Whitelist disabled");
			Bukkit.getServer().setWhitelist(false);
		}
	}
	

}
