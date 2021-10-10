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
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;


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
			
		//e.setFormat(format(data, e));
		e.setCancelled(true);
			
		TextComponent name = getNameComponent(INSTANCE, e.getPlayer());
			
		System.out.println(e.getPlayer().getName() + ": " + e.getMessage());
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.spigot().sendMessage(name, new TextComponent(ChatColor.DARK_GRAY + ": " + ChatColor.RESET + TextReplacement.parse(e.getMessage())));
		}
		
	}
	
	
	/**
	 * Gets a click to message, hoverable name.
	 * @param instance Instance of plugin.
	 * @param player Player to get for.
	 * @return The textcomponent.
	 */
	public TextComponent getNameComponent(SMPPlugin instance, Player player) {
		
		try {
			
			PlayerData data = PlayerData.get(instance, player.getUniqueId());
			
			TextComponent name = new TextComponent(player.getName());
			name.setColor(ChatColor.of(data.getColourHex()));
			// Click name to message
			name.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " "));
			
			// Sets up the text for hover
			String hoverTextStr = "";
			if(data.getName() != null) hoverTextStr += ChatColor.DARK_AQUA + "Name: " + ChatColor.WHITE + data.getName() + "\n";
			if(data.getGrade() != 0) hoverTextStr += ChatColor.DARK_AQUA + "Grade: " + ChatColor.WHITE + data.getGrade() + "\n";
			if(data.getSchool() != null) hoverTextStr += ChatColor.DARK_AQUA + "School: " + ChatColor.WHITE + data.getSchool() + "\n";
			hoverTextStr += ChatColor.GRAY + "Click to message";
			Text hoverText = new Text(hoverTextStr);
			
			// Hover itself
			name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText));
			
			return name;
			
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//private String format(PlayerData data, AsyncPlayerChatEvent e) {
	//	return ChatColor.of(data.getColourHex()) + e.getPlayer().getName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + TextReplacement.parse(e.getMessage());
	//}
	
}
