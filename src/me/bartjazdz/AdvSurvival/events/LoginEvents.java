package me.bartjazdz.AdvSurvival.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class LoginEvents implements Listener{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("logininfo.loggedPlayers." + player.getName()) != true) {
			player.sendMessage(ChatColor.GOLD + "Please register using command " + ChatColor.BOLD + "/register <password> <password>");
			player.sendTitle("Hello, " + player.getName(), ChatColor.GOLD + "Register using command " + ChatColor.BOLD + "/register <password> <password>", 10, 600, 20);
		}else {
			player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>");
			player.sendTitle("Hello again, " + player.getName(), ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>", 10, 600, 20);
		}
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName()) && player !=null) {
					player.kickPlayer(ChatColor.RED + "Time for login is out!");
				}
			}
			
		}, 600);
		
	}
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName()) != true){
			event.setCancelled(true);
			if(plugin.getConfig().getString("logininfo.loggedPlayers") == null) {
				return;
			}else {
				return;
			}
		}
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		plugin.getConfig().set("logininfo.activePlayers." + player.getName(), false);
		plugin.saveConfig();
	}
	@EventHandler
	public void onChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
			event.setCancelled(true);
			if(plugin.getConfig().getString("logininfo.loggedPlayers") == null) {
				player.sendMessage(ChatColor.GOLD + "Please register using command " + ChatColor.BOLD + "/register <password> <password>");
				return;
			}else {
				player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>");
				return;
			}
		}
	}
	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
			event.setCancelled(true);
			if(plugin.getConfig().getString("logininfo.loggedPlayers") == null) {
				player.sendMessage(ChatColor.GOLD + "Please register using command " + ChatColor.BOLD + "/register <password> <password>");
				return;
			}else {
				player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>");
				return;
			}
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
			event.setCancelled(true);
			if(plugin.getConfig().getString("logininfo.loggedPlayers") == null) {
				player.sendMessage(ChatColor.GOLD + "Please register using command " + ChatColor.BOLD + "/register <password> <password>");
				return;
			}else {
				player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>");
				return;
			}
		}
	}
}
