package ca.loellenrobotics.mc.smp;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;

/**
 * Loads stuff. Preferred doing it here rather than repeating myself in other spots.
 * @author Liam Robins
 *
 */
public class Loader {

	/**
	 * Loads the player, to be called on join, reload.
	 * @param instance The instance of the plugin.
	 * @param player The player.
	 */
	public static void loadPlayer(Plugin instance, UUID player) {
		
		try {
			
			PlayerData data = PlayerData.get(instance, player, true);
			
			if(data.firstLoaded()) {
				
			}
			
			if(Bukkit.getPlayer(player) != null) {
				Player p = Bukkit.getPlayer(player);
				p.setPlayerListName(ChatColor.of(data.getColourHex()) + p.getName());
			}
			
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void unloadPlayer(Plugin instance, UUID player) {
		
	}
	
}
