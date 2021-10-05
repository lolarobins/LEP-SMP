package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.Plugin;
import ca.loellenrobotics.mc.smp.TextReplacement;
import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;


/**
 * Censors chat messages and changes chat format.
 * @author Liam Robins
 */
public class Chat implements Listener {

	private final Plugin INSTANCE;
	
	public Chat(Plugin instance) {
		// Dependency injection of instance, as it is needed to get player's data.
		INSTANCE = instance;
	}
	
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e) {
		
		try {
			PlayerData data = PlayerData.get(INSTANCE, e.getPlayer().getUniqueId());
			
			e.setFormat(format(data, e));
			
		} catch (PlayerNotFoundException er) {
			// Print stack trace, if this is called, ever, it shouldn't be.
			er.printStackTrace();
		}
		
	}
	
	private String format(PlayerData data, AsyncPlayerChatEvent e) {
		return ChatColor.of(data.getColourHex()) + e.getPlayer().getName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + TextReplacement.parse(e.getMessage());
	}
	
}
