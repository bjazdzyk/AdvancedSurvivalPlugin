package me.bartjazdz.AdvSurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class LoginCommands implements CommandExecutor{

	private Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("register")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
					player.sendMessage(ChatColor.RED + "You are already logged in!");
					return true;
				}
				if(plugin.getConfig().getBoolean("logininfo.loggedPlayers." + player.getName())) {
					player.sendMessage(ChatColor.RED + "You are already registered!");
					return true;
				}
				if(args.length > 1) {
					if(args[0].equals(args[1])) {
						plugin.getConfig().set("logininfo.loggedPlayers." + player.getName(), true);
						plugin.getConfig().set("logininfo.activePlayers." + player.getName(), true);
						plugin.getConfig().set("logininfo.passwords." + player.getName(), args[0]);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GREEN + "Succesfully registered!");
						player.sendTitle(ChatColor.GREEN + "Succesfully registered!", "Enjoy the server!", 1, 100, 10);
						return true;
					}
				}
				player.sendMessage(ChatColor.GOLD + "Please register using command " + ChatColor.RED +  "/register <password> <password>");
				return true;
			}else {
				sender.sendMessage(ChatColor.RED + "You are not a player!!!");
				return false;
			}
		}
		if(cmd.getName().equalsIgnoreCase("login")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(!plugin.getConfig().getBoolean("logininfo.loggedPlayers." + player.getName())) {
					player.sendMessage(ChatColor.RED + "You are not registered yet!");
					return true;
				}
				if(plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
					player.sendMessage(ChatColor.RED + "You are already logged in!");
					return true;
				}
				if(args.length > 0) {
					if(args[0].equals(plugin.getConfig().getString("logininfo.passwords." + player.getName()))) {
						plugin.getConfig().set("logininfo.activePlayers." + player.getName(), true);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GREEN + "Succesfully logged in!");
						player.sendTitle(ChatColor.GREEN + "Succesfully logged in!", "Enjoy the server!", 1, 100, 10);
						return true;
					}else {
						player.kickPlayer(ChatColor.RED + "INVALID PASSWORD!!!");
						return true;
					}
				}
				player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.RED +  "/login <password>");
				return true;
			}else {
				sender.sendMessage(ChatColor.RED + "You are not a player!!!");
				return false;
			}
		}
		return false;
	}

}
