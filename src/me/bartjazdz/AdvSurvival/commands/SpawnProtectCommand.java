package me.bartjazdz.AdvSurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class SpawnProtectCommand implements CommandExecutor{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setcorner1")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
					return true;
				}
				
				plugin.getConfig().set("spawninfo.spawnprotect.pos1.x", player.getLocation().getBlockX());
				plugin.getConfig().set("spawninfo.spawnprotect.pos1.z", player.getLocation().getBlockZ());
				plugin.saveConfig();
				
				if(plugin.getConfig().get("spawninfo.spawnprotect.pos2") != null) {
					int x1 = player.getLocation().getBlockX();
					int z1 = player.getLocation().getBlockZ();
					int x2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.x");
					int z2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.z");
					
					if(x1<=x2 && z1<=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 1);
						player.sendMessage(ChatColor.GREEN + "combination type: 1");
					}else if(x1<=x2 && z1>=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 2);
						player.sendMessage(ChatColor.GREEN + "combination type: 2");
					}else if(x1>=x2 && z1>=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 3);
						player.sendMessage(ChatColor.GREEN + "combination type: 3");
					}else if(x1>=x2 && z1<=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 4);
						player.sendMessage(ChatColor.GREEN + "combination type: 4");
					}else {
						player.sendMessage(ChatColor.RED + "Something went wrong!!!");
						return false;
					}
					plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Corners set succesfully!");
					return true;
				}else {
					player.sendMessage(ChatColor.GREEN + "First Corner set succesfully!");
					return true;
				}
			}else {
				sender.sendMessage(ChatColor.RED + "You are not a player!!!");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("setcorner2")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
					return true;
				}
				
				plugin.getConfig().set("spawninfo.spawnprotect.pos2.x", player.getLocation().getBlockX());
				plugin.getConfig().set("spawninfo.spawnprotect.pos2.z", player.getLocation().getBlockZ());
				plugin.saveConfig();
				
				if(plugin.getConfig().get("spawninfo.spawnprotect.pos2") != null) {
					int x2 = player.getLocation().getBlockX();
					int z2 = player.getLocation().getBlockZ();
					int x1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.x");
					int z1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.z");
					
					if(x1<=x2 && z1<=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 1);
						player.sendMessage(ChatColor.GREEN + "combination type: 1");
					}else if(x1<=x2 && z1>=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 2);
						player.sendMessage(ChatColor.GREEN + "combination type: 2");
					}else if(x1>=x2 && z1>=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 3);
						player.sendMessage(ChatColor.GREEN + "combination type: 3");
					}else if(x1>=x2 && z1<=z2) {
						plugin.getConfig().set("spawninfo.spawnprotect.type", 4);
						player.sendMessage(ChatColor.GREEN + "combination type: 4");
					}else {
						player.sendMessage(ChatColor.RED + "Something went wrong!!!");
						return false;
					}
					plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Corners set succesfully!");
					return true;
				}else {
					player.sendMessage(ChatColor.GREEN + "Second Corner set succesfully!");
					return true;
				}
			}else {
				sender.sendMessage(ChatColor.RED + "You are not a player!!!");
				return true;
			}
		}
		return false;
	}

}
