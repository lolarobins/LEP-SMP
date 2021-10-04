package ca.loellenrobotics.mc.smp;

import java.util.UUID;

/**
 * Loads stuff. Preferred doing it here rather than repeating myself in other spots.
 * @author Liam Robins
 *
 */
public class Loader {

	public static void loadPlayer(Plugin instance, UUID player) {
		
		PlayerData data = PlayerData.get(instance, player, true);
		
		if(!data.hasPreviouslyJoined()) {
			
		}
		
	}
	
	public static void unloadPlayer(Plugin instance, UUID player) {
		
	}
	
}
