package me.bartjazdz.AdvSurvival.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import me.bartjazdz.AdvSurvival.Main;
import net.md_5.bungee.api.ChatColor;

public class SpawnProtectEvents implements Listener{
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		int px = player.getLocation().getBlockX();
		int pz = player.getLocation().getBlockZ();
		
		int x1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.x");
		int z1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.z");
		int x2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.x");
		int z2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.z");
		
		if(plugin.getConfig().getBoolean("spawninfo.playersonspawn." + player.getName()) == false) {
			if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 1) {
				if(px>=x1 && px<=x2 && pz>=z1 && pz<=z2) {
					player.sendMessage(ChatColor.GOLD + "You entered the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), true);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 2) {
				if(px>=x1 && px<=x2 && pz<=z1 && pz>=z2) {
					player.sendMessage(ChatColor.GOLD + "You entered the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), true);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 3) {
				if(px<=x1 && px>=x2 && pz<=z1 && pz>=z2) {
					player.sendMessage(ChatColor.GOLD + "You entered the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), true);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 4) {
				if(px<=x1 && px>=x2 && pz>=z1 && pz<=z2) {
					player.sendMessage(ChatColor.GOLD + "You entered the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), true);
					plugin.saveConfig();
					return;
				}
			}
		}else if(plugin.getConfig().getBoolean("spawninfo.playersonspawn." + player.getName()) == true){
			if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 1) {
				if(px<x1 || px>x2 || pz<z1 || pz>z2) {
					player.sendMessage(ChatColor.GOLD + "You left the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), false);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 2) {
				if(px<x1 || px>x2 || pz>z1 || pz<z2) {
					player.sendMessage(ChatColor.GOLD + "You left the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), false);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 3) {
				if(px>x1 || px<x2 || pz>z1 || pz<z2) {
					player.sendMessage(ChatColor.GOLD + "You left the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), false);
					plugin.saveConfig();
					return;
				}
			}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 4) {
				if(px>x1 || px<x2 || pz<z1 || pz>z2) {
					player.sendMessage(ChatColor.GOLD + "You left the spawn");
					plugin.getConfig().set("spawninfo.playersonspawn." + player.getName(), false);
					plugin.saveConfig();
					return;
				}
			}
		}
	}
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof Player) {
			Player player = (Player) entity;
			if(plugin.getConfig().getBoolean("spawninfo.playersonspawn." + player.getName())) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.GREEN + "You can't take any damage on spawn!");
			}
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(player.isOp()) {
			return;
		}
		
		int bx = block.getX();
		int bz = block.getZ();
		
		int x1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.x");
		int z1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.z");
		int x2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.x");
		int z2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.z");
		
		if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 1) {
			if(bx>=x1 && bx<=x2 && bz>=z1 && bz<=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't break blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 2) {
			if(bx>=x1 && bx<=x2 && bz<=z1 && bz>=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't break blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 3) {
			if(bx<=x1 && bx>=x2 && bz<=z1 && bz>=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't break blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 4) {
			if(bx<=x1 && bx>=x2 && bz>=z1 && bz<=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't break blocks on spawn!!!");
				return;
			}
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(player.isOp()) {
			return;
		}
		
		int bx = block.getX();
		int bz = block.getZ();
		
		int x1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.x");
		int z1 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos1.z");
		int x2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.x");
		int z2 = plugin.getConfig().getInt("spawninfo.spawnprotect.pos2.z");
		
		if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 1) {
			if(bx>=x1 && bx<=x2 && bz>=z1 && bz<=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't place blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 2) {
			if(bx>=x1 && bx<=x2 && bz<=z1 && bz>=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't place blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 3) {
			if(bx<=x1 && bx>=x2 && bz<=z1 && bz>=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't place blocks on spawn!!!");
				return;
			}
		}else if(plugin.getConfig().getInt("spawninfo.spawnprotect.type") == 4) {
			if(bx<=x1 && bx>=x2 && bz>=z1 && bz<=z2) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can't place blocks on spawn!!!");
				return;
			}
		}
	}
}







