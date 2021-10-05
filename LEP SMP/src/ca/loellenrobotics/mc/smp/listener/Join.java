package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ca.loellenrobotics.mc.smp.Loader;
import ca.loellenrobotics.mc.smp.Plugin;

/**
 * Handles player stuff when they join.
 * @author Liam Robins
 *
 */
public class Join implements Listener {
	
	private final Plugin INSTANCE;
	
	public Join(Plugin instance) {
		INSTANCE = instance;
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent e) {
		
		Loader.loadPlayer(INSTANCE, e.getPlayer().getUniqueId());
		
	}
	

}
