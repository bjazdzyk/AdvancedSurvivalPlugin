package me.bartjazdz.AdvSurvival;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.bartjazdz.AdvSurvival.commands.HomeCommand;
import me.bartjazdz.AdvSurvival.commands.SpawnCommand;
import me.bartjazdz.AdvSurvival.events.SpawnEvents;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("Advanced Survival" +  ChatColor.GREEN + " ENABLED");
		
		this.getCommand("spawn").setExecutor(new SpawnCommand());
		this.getCommand("setspawn").setExecutor(new SpawnCommand());
		getServer().getPluginManager().registerEvents(new SpawnEvents(), this);
		
		this.getCommand("home").setExecutor(new HomeCommand());
		this.getCommand("sethome").setExecutor(new HomeCommand());
		
		loadConfig();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("Advanced Survival" +  ChatColor.RED + " DISABLED");
	}
	
	public void loadConfig() {
		getConfig().addDefault("spawninfo.isSet", false);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
