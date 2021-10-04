package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;

/**
 * Deals with player data.
 * @author Liam Robins
 *
 */
public class PlayerData {

	private DataFile data;
	private final UUID PUID;
	private final boolean EXISTS;
	
	private static HashMap<UUID, PlayerData> cached = new HashMap<UUID, PlayerData>();
	
	PlayerData(Plugin instance, UUID uuid, boolean create) {
		
		boolean failed = false;
		
		try {
			data = new DataFile(instance, "player/"+uuid+".yml", create);
		} catch (IOException | InvalidConfigurationException e) {
			failed = true;
		}
		
		if(failed) {
			EXISTS = false;
			PUID = null;
			return;
		}
		
		else EXISTS = true;
		
		PUID = uuid;
				
	}
	
	
	/**
	 * Gets the player data of player with specified UUID.
	 * @param instance Plugin instance (needed for internal use).
	 * @param uuid UUID of target player.
	 * @return The playerdata of that player.
	 */
	public static PlayerData get(Plugin instance, UUID uuid) {
		return get(instance, uuid, false);
	}
	
	
	/**
	 * Gets the player data of a player with specified UUID, or creates it (when specified).
	 * @param instance Plugin instance (needed for internal use).
	 * @param uuid UUID of target player.
	 * @param create Create file if doesn't exist.
	 * @return The playerdata of that player.
	 */
	public static PlayerData get(Plugin instance, UUID uuid, boolean create) {
		if(!cached.containsKey(uuid)) {
			return new PlayerData(instance, uuid, create);
		} else {
			return cached.get(uuid);
		}
	}

	
	/**
	 * Removes player from cache (Call on reload, disconnect).
	 */
	public void removeFromCache() {
		cached.remove(PUID);
	}
	
	
	/**
	 * Adds player to cache (Call on enable, connect).
	 */
	public void addToCache() {
		cached.put(PUID, this);
	}
	
	
	/**
	 * Checks if the playerfile exists and has loaded properly.
	 * @return State of existence.
	 */
	public boolean exists() {
		return EXISTS;
	}
	
	
	/**
	 * Checks if the player is new to the server.
	 * @return True if the player has joined before.
	 */
	public boolean hasPreviouslyJoined() {
		return !data.isNewFile();
	}
}
