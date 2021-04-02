package me.bartjazdz.AdvSurvival.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class SpawnEvents implements Listener{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("spawninfo.isSet")) {
			event.setRespawnLocation(plugin.getConfig().getLocation("spawninfo.location"));
			player.sendMessage(ChatColor.GOLD + "Teleported to spawn");
		}else {
			player.sendMessage(ChatColor.RED + "Spawn point is not already set!!!");
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore()) {
			event.setJoinMessage(ChatColor.GOLD + player.getName() + ChatColor.RESET + " is new on this server!");
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					if(plugin.getConfig().getBoolean("spawninfo.isSet")) {
						player.teleport(plugin.getConfig().getLocation("spawninfo.location"));
					}else {
						player.sendMessage(ChatColor.RED + "Spawn point is not already set!!!");
					}
				}
			}, 21);
		}else {
			event.setJoinMessage(ChatColor.GOLD + player.getName() + ChatColor.RESET + " joined the game!");
		}
	}
}
