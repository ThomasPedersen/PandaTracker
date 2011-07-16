package net.pandafox.PandaTracker;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MyPlayerListener extends PlayerListener {
	
	public static PandaTracker plugin;
	
	public MyPlayerListener(PandaTracker instance) {
		plugin = instance;
	}

	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location playerLoc = player.getLocation();
		plugin.getClient().sendLocation(player.getName(), playerLoc.getX(), playerLoc.getY(), playerLoc.getZ());
	}
}
