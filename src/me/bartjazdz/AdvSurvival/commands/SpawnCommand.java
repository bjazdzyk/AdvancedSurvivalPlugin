package me.bartjazdz.AdvSurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class SpawnCommand implements CommandExecutor{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				plugin.getConfig().set("spawninfo.location", player.getLocation());
				plugin.getConfig().set("spawninfo.isSet", true);
				plugin.saveConfig();
				player.sendMessage(ChatColor.GREEN + "Spawn point succesfully set!");
				return true;
			}else {
				sender.sendMessage(ChatColor.RED + "Only player can set the spawn point!!!");
				return true;
			}
			
		}
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(plugin.getConfig().getBoolean("spawninfo.isSet")) {
					player.teleport(plugin.getConfig().getLocation("spawninfo.location"));
					player.sendMessage(ChatColor.GOLD + "Teleported to spawn");
					return true;
				}else {
					player.sendMessage(ChatColor.RED + "Spawn point is not already set!!!");
					return true;
				}
			}else {
				sender.sendMessage(ChatColor.DARK_RED + "You are not a player!!!");
				return true;
			}
		}
		return false;
	}

}
