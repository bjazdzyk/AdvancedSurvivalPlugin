package me.bartjazdz.AdvSurvival.other;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class ConfigManager {
	private Plugin plugin = Main.getPlugin(Main.class);
	
	public FileConfiguration SettingsConfig;
	public File SettingsFile;
	
	public void setup() {
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		SettingsFile = new File(plugin.getDataFolder(), "SETTINGS.yml");
		
		if(!SettingsFile.exists()) {
			try {
				SettingsFile.createNewFile();
			}catch(IOException e){
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the SETTINGS.yml file!!!");
			}
		}
		SettingsConfig = YamlConfiguration.loadConfiguration(SettingsFile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SETTINGS.yml file has been created!!!");
	}
	
	public FileConfiguration getSettings() {
		return SettingsConfig;
	}
	
	public void saveSettings() {
		try {
			SettingsConfig.save(SettingsFile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SETTINGS.yml file has been saved!!!");
		}catch(IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the SETTINGS.yml file!!!");
		}
	}
	
	public void reloadSettings() {
		SettingsConfig = YamlConfiguration.loadConfiguration(SettingsFile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SETTINGS.yml file has been reloaded!!!");
	}
}
