package ca.loellenrobotics.mc.smp;

import java.util.UUID;

import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;

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
			
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void unloadPlayer(Plugin instance, UUID player) {
		
	}
	
}
