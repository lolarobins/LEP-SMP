package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.ImmutableMap;

import ca.loellenrobotics.mc.smp.exception.TeamException;

/**
 * Deals with team data information.
 * @author Liam Robins
 *
 */
public class TeamData {
	
	
	private DataFile data;
	private FileConfiguration file;
	private String colour, description;
	private final String ID;
	private final long CREATED;
	
	
	private final Map<String, Object> DEFAULTS = ImmutableMap.<String, Object>
	builder()
	.put("data.colour", "#0ac900")
	.put("data.created", System.currentTimeMillis())
	.put("data.description", "")
	.build();
	
	
	TeamData(SMPPlugin instance, String id) throws TeamException {
		try {
			data = new DataFile(instance, "team/"+id+".yml", false);
		} catch (IOException | InvalidConfigurationException e) {
			throw new TeamException(e.getMessage());
		}	
		
		file = data.getConfig();
		writeDefaults();
	
		this.ID = id;
		colour = file.getString("data.colour");
		description = file.getString("data.description");
		CREATED = file.getLong("data.created");
		
	}
	
	
	TeamData(SMPPlugin instance, String id, PlayerData leader) throws TeamException {
		try {
			data = new DataFile(instance, "team/"+id+".yml", true);
		} catch (IOException | InvalidConfigurationException e) {
			throw new TeamException(e.getMessage());
		}	
		
		file = data.getConfig();
		writeDefaults();
		
		this.ID = id;
		colour = file.getString("data.colour");
		description = file.getString("data.description");
		CREATED = file.getLong("data.created");
		
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
	 * Gets a team.
	 * @param teamID ID of a team to get.
	 * @return TeamData of team, null if doesn't exist.
	 */
	public static TeamData get(SMPPlugin instance, String teamID) {
		
		if(teamID == null || teamID.isEmpty()) return null;
		
		try {
			return new TeamData(instance, teamID);
		} catch(TeamException e) {
			return null;
		}
		
	}

	
	public static TeamData create(SMPPlugin instance, String teamID, PlayerData leader) throws TeamException {
		
		if(get(instance, teamID) != null) {
			throw new TeamException("Team already exists");
		}
		
		return new TeamData(instance, teamID, leader);
		
	}
	
	
	public final String getID() {
		return ID;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public String getHexColour() {
		return colour;
	}
	
	
	public final long getTimeCreated() {
		return CREATED;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
		file.set("data.description", description);
		data.save();
	}
	
	
	public void setHexColour(String colourCode) {
		colour = colourCode;
		file.set("data.colour", colourCode);
		data.save();
	}
	
}
