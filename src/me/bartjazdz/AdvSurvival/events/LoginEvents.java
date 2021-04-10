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
			return;
		}else {
			player.sendMessage(ChatColor.GOLD + "Please log in using command " + ChatColor.BOLD + "/login <password>");
			return;
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName()) != true){
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
			return;
		}
	}
	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
			event.setCancelled(true);
			return;
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if(!plugin.getConfig().getBoolean("logininfo.activePlayers." + player.getName())) {
			event.setCancelled(true);
			return;
		}
	}
}
