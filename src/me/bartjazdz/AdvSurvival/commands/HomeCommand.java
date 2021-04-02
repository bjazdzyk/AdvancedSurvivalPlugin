package me.bartjazdz.AdvSurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class HomeCommand implements CommandExecutor{
	
	private Plugin plugin = Main.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				plugin.getConfig().set("homeinfo." + player.getName(), player.getLocation());
				player.sendMessage(ChatColor.GREEN + "Home set!");
				return true;
			}else {
				sender.sendMessage(ChatColor.RED + "You are not a player!!!");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("home")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(plugin.getConfig().getLocation("homeinfo." + player.getName()) == null) {
					player.sendMessage(ChatColor.RED + "Home not found!!!");
					return true;
				}else {
					player.teleport(plugin.getConfig().getLocation("homeinfo." + player.getName()));
					player.sendMessage(ChatColor.GOLD + "Teleported to home");
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
