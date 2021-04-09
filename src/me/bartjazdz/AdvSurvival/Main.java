package me.bartjazdz.AdvSurvival;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.bartjazdz.AdvSurvival.commands.HelpCommand;
import me.bartjazdz.AdvSurvival.commands.HomeCommand;
import me.bartjazdz.AdvSurvival.commands.LoginCommands;
import me.bartjazdz.AdvSurvival.commands.SpawnCommand;
import me.bartjazdz.AdvSurvival.commands.SpawnProtectCommand;
import me.bartjazdz.AdvSurvival.events.LoginEvents;
import me.bartjazdz.AdvSurvival.events.SpawnEvents;
import me.bartjazdz.AdvSurvival.events.SpawnProtectEvents;
import me.bartjazdz.AdvSurvival.other.ConfigManager;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	public ConfigManager cfgm;
	
	@Override
	public void onEnable() {
		
		loadConfigManager();
		loadConfig();
		
		Bukkit.getLogger().info("Advanced Survival" +  ChatColor.GREEN + " ENABLED");
		
		if(cfgm.getSettings().getBoolean("SpawnCommand")) {
			this.getCommand("spawn").setExecutor(new SpawnCommand());
			this.getCommand("setspawn").setExecutor(new SpawnCommand());
			getServer().getPluginManager().registerEvents(new SpawnEvents(), this);
		}
		
		if(cfgm.getSettings().getBoolean("Homes")) {
			this.getCommand("home").setExecutor(new HomeCommand());
			this.getCommand("sethome").setExecutor(new HomeCommand());
		}
		
		if(cfgm.getSettings().getBoolean("SpawnProtect")) {
			this.getCommand("setcorner1").setExecutor(new SpawnProtectCommand());
			this.getCommand("setcorner2").setExecutor(new SpawnProtectCommand());
			getServer().getPluginManager().registerEvents(new SpawnProtectEvents(), this);
		}
		
		if(cfgm.getSettings().getBoolean("LoginSecurity")) {
			this.getCommand("register").setExecutor(new LoginCommands());
			this.getCommand("login").setExecutor(new LoginCommands());
			getServer().getPluginManager().registerEvents(new LoginEvents(), this);
		}
		
		if(cfgm.getSettings().getBoolean("AdvancedHelp")) {
			this.getCommand("help").setExecutor(new HelpCommand());
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("Advanced Survival" +  ChatColor.RED + " DISABLED");
	}
	
	public FileConfiguration getSettings() {
		return cfgm.getSettings();
	}
	
	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
		
		cfgm.getSettings().addDefault("AdvancedHelp", true);
		cfgm.getSettings().addDefault("SpawnProtect", true);
		cfgm.getSettings().addDefault("Homes", true);
		cfgm.getSettings().addDefault("LoginSecurity", true);
		cfgm.getSettings().addDefault("SpawnCommand", true);
		
		cfgm.getSettings().options().copyDefaults(true);
		cfgm.saveSettings();
		
	}
	
	public void loadConfig() {
		getConfig().addDefault("spawninfo.isSet", false);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
