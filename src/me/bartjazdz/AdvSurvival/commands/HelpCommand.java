package me.bartjazdz.AdvSurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class HelpCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.AQUA + "-?--HELP--?-");
			if(sender.hasPermission("AdvSurvival.help")) {
				sender.sendMessage(ChatColor.BOLD + "/help " + ChatColor.GOLD + " Show all avaiable commands");
			}
			if(sender.hasPermission("AdvSurvival.corners")) {
				sender.sendMessage(ChatColor.BOLD + "/setcorner1 " + ChatColor.GOLD + " Set first corner of spawn");
				sender.sendMessage(ChatColor.BOLD + "/setcorner2 " + ChatColor.GOLD + " Set second corner of spawn");
			}
			if(sender.hasPermission("AdvSurvival.homes")) {
				sender.sendMessage(ChatColor.BOLD + "/sethome " + ChatColor.GOLD + " Set the location of home");
				sender.sendMessage(ChatColor.BOLD + "/home " + ChatColor.GOLD + " teleport to home location");
			}
			if(sender.hasPermission("AdvSurvival.spawn")) {
				sender.sendMessage(ChatColor.BOLD + "/spawn " + ChatColor.GOLD + " teleport to spawn location");
			}
			if(sender.hasPermission("AdvSurvival.setspawn")) {
				sender.sendMessage(ChatColor.BOLD + "/setspawn " + ChatColor.GOLD + " set the spawn location");
			}
			sender.sendMessage(ChatColor.AQUA + "-=---==---=-");
			
			return true;
		}
		return false;
	}

}
