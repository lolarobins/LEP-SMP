package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Calls when player leaves.
 * @author Liam Robins
 *
 */
public class Leave implements Listener {

	final SMPPlugin INSTANCE;
	
	public Leave(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		
		try {
			e.setQuitMessage(ChatColor.of(PlayerData.get(INSTANCE, e.getPlayer().getUniqueId()).getColourHex()) + 
					e.getPlayer().getName() + ChatColor.RESET + "" + ChatColor.GRAY + " left the game");
		} catch (PlayerException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
