package net.pandafox.PandaTracker;
 
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.Event;

public class PandaTracker extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	private final MyPlayerListener playerListener = new MyPlayerListener(this);
	
	private Thread clientThread;
	private Client client = new Client();

	public void onEnable(){			
		log.info("Loading PandaTracker.");
 
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		
		clientThread = new Thread(client);
		clientThread.start();

	}
	
	public void onDisable(){
		log.info("PandaTracker Disabled.");
	}
	
	public Client getClient() {
		return client;
	}
}
