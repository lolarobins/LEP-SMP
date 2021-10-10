package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.ImmutableMap;

import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;

/**
 * Deals with player data.
 * @author Liam Robins
 *
 */
public class PlayerData {

	private DataFile data;
	private FileConfiguration file;
	private String colour;
	
	// The defaults to add to each player.
	private static final Map<String, Object> DEFAULTS = ImmutableMap.<String, Object>
		builder()
		.put("data.colour", new ColourRandomizer().getHex())
		.build();
	
	PlayerData(SMPPlugin instance, UUID uuid, boolean create) throws PlayerNotFoundException {
		try {
			data = new DataFile(instance, "player/"+uuid+".yml", create);
		} catch (IOException | InvalidConfigurationException e) {
			throw new PlayerNotFoundException(e.getMessage());
		}	
		
		file = data.getConfig();
		writeDefaults();
		
		// Load from file
		colour = file.getString("data.colour");
		
	}
	
	
	/**
	 * Gets the player data of player with specified UUID.
	 * @param instance Plugin instance (needed for internal use).
	 * @param uuid UUID of target player.
	 * @return The playerdata of that player.
	 * @throws PlayerNotFoundException Throws if player file doesn't exist.
	 */
	public static PlayerData get(SMPPlugin instance, UUID uuid) throws PlayerNotFoundException {
		return new PlayerData(instance, uuid, false);
	}
	
	
	/**
	 * Gets the player data of a player with specified UUID, or creates it (when specified).
	 * @param instance Plugin instance (needed for internal use).
	 * @param uuid UUID of target player.
	 * @param create Create file if doesn't exist.
	 * @return The playerdata of that player.
	 * @throws PlayerNotFoundException Throws if player file doesn't exist. (if create != true).
	 */
	public static PlayerData get(SMPPlugin instance, UUID uuid, boolean create) throws PlayerNotFoundException {
		return new PlayerData(instance, uuid, create);
	}	
	
	
	/**
	 * Checks if the player is new to the server. (Only can be used on first instance called).
	 * @return True if the player file is new.
	 */
	public boolean firstLoaded() {
		return !data.isNewFile();
	}
	
	
	/**
	 * Writes defaults if they do not already exist.
	 */
	public void writeDefaults() {
		
		for(String path : DEFAULTS.keySet()) {
			if(!file.contains(path)) {
				file.set(path, DEFAULTS.get(path));
			}
		}
		
		data.save();
	}
	
	
	/**
	 * Gets the colour of a player.
	 * @return Player's colour in hex code format.
	 */
	public String getColourHex() {
		return colour;
	}
}
