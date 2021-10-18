package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.ImmutableMap;

import ca.loellenrobotics.mc.smp.exception.TeamNotFoundException;

/**
 * Deals with team data information.
 * @author Liam Robins
 *
 */
public class TeamData {
	
	private DataFile data;
	private FileConfiguration file;
	
	private final Map<String, Object> DEFAULTS = ImmutableMap.<String, Object>
	builder()
	.put("data.colour", "#0ac900")
	.build();
	
	
	TeamData(SMPPlugin instance, String id, boolean create) throws TeamNotFoundException{
		try {
			data = new DataFile(instance, "team/"+id+".yml", create);
		} catch (IOException | InvalidConfigurationException e) {
			throw new TeamNotFoundException(e.getMessage());
		}	
		
		file = data.getConfig();
		
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
	
	
	public static TeamData get(String teamID) {
		if(teamID == null || teamID.isEmpty()) return null;
		return null;
	}
	
}
