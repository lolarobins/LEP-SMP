package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.TextReplacement;
import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;


/**
 * Censors chat messages and changes chat format.
 * @author Liam Robins
 */
public class Chat implements Listener {

	private final SMPPlugin INSTANCE;
	
	public Chat(SMPPlugin instance) {
		// Dependency injection of instance, as it is needed to get player's data.
		INSTANCE = instance;
	}
	
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e) {
		
		try {
			PlayerData data = PlayerData.get(INSTANCE, e.getPlayer().getUniqueId());
			
			//e.setFormat(format(data, e));
			e.setCancelled(true);
			
			TextComponent name = new TextComponent(e.getPlayer().getName());
			name.setColor(ChatColor.of(data.getColourHex()));
			name.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + e.getPlayer().getName() + " "));
			
			System.out.println(e.getPlayer().getName() + ": " + e.getMessage());
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.spigot().sendMessage(name, new TextComponent(ChatColor.DARK_GRAY + ": " + ChatColor.RESET + TextReplacement.parse(e.getMessage())));
			}
			
		} catch (PlayerNotFoundException er) {
			// Print stack trace, if this is called, ever, it shouldn't be.
			er.printStackTrace();
		}
		
	}
	
	//private String format(PlayerData data, AsyncPlayerChatEvent e) {
	//	return ChatColor.of(data.getColourHex()) + e.getPlayer().getName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + TextReplacement.parse(e.getMessage());
	//}
	
}
