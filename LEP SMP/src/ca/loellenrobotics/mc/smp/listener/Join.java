package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ca.loellenrobotics.mc.smp.Loader;
import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Handles player stuff when they join.
 * @author Liam Robins
 *
 */
public class Join implements Listener {
	
	private final SMPPlugin INSTANCE;
	
	public Join(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent e) {
		
		Loader.loadPlayer(INSTANCE, e.getPlayer().getUniqueId());
		
		try {
			e.setJoinMessage(ChatColor.of(PlayerData.get(INSTANCE, e.getPlayer().getUniqueId()).getColourHex()) + 
					e.getPlayer().getName() + ChatColor.RESET + "" + ChatColor.GRAY + " joined the game");
		} catch (PlayerException e1) {
			e1.printStackTrace();
		}
		
	}
	

}
