package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ca.loellenrobotics.mc.smp.Plugin;
import ca.loellenrobotics.mc.smp.TextReplacement;


/**
 * Censors chat messages and changes chat format.
 * @author Liam Robins
 */
public class Chat implements Listener {

	private final Plugin INSTANCE;
	
	public Chat(Plugin instance) {
		INSTANCE = instance;
	}
	
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e) {
		
		e.setFormat(TextReplacement.parse(e.getMessage()));
		
	}
	
}
