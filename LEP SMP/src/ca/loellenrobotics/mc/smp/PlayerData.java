package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

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
	private String colour, name, school, teamid;
	private TeamData team;
	private int grade;
	
	// The defaults to add to each player.
	private final Map<String, Object> DEFAULTS = ImmutableMap.<String, Object>
		builder()
		.put("data.colour", new ColourRandomizer().getHex())
		.put("data.name", "")
		.put("data.school", "")
		.put("data.grade", 0)
		.put("data.team", "")
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
		name = file.getString("data.name");
		school = file.getString("data.school");
		grade = file.getInt("data.grade");
		teamid = file.getString("data.team");
		team = TeamData.get(teamid);
		
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
	 * Sets the name of a player
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
		file.set("data.name", name);
		data.save();
	}
	
	
	/**
	 * Sets the school the player goes to
	 * @param school School name
	 */
	public void setSchool(String school) {
		this.school = school;
		file.set("data.school", school);
		data.save();
	}
	
	
	/**
	 * Sets the grade of a player
	 * @param grade The grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
		file.set("data.grade", grade);
		data.save();
	}
	
	
	/**
	 * Sets the team of a player.
	 * @param team Team to set.
	 */
	public void setTeam(TeamData team) {
		this.team = team;
	}
	
	
	/**
	 * Removes a player from a team.
	 */
	public void removeTeam() {
		if(team == null) return;
	}
	
	
	/**
	 * Sets the hex colour for the player's name.
	 * @param colourCode Hex code #000000
	 */
	public void setColourHex(String colourCode) {
        if(!Pattern.compile("^#([a-fA-F0-9]{6})$").matcher(colourCode).matches()) throw new IllegalArgumentException("");
        this.colour = colourCode;
        file.set("data.colour", colourCode);
        data.save();
	}
	
	
	/**
	 * Gets the colour of a player.
	 * @return Player's colour in hex code format.
	 */
	public String getColourHex() {
		return colour;
	}
	
	
	/**
	 * Gets the player's set name.
	 * @return Player's real name.
	 */
	public String getName() {
		if(name.isEmpty()) return null;
		return name;
	}
	
	
	/**
	 * Gets the school the player goes to
	 * @return The school's name
	 */
	public String getSchool() {
		if(school.isEmpty()) return null;
		return school;
	}
	
	
	/**
	 * Gets the players grade
	 * @return Grade, 0 if not set or reset.
	 */
	public int getGrade() {
		return grade;
	}
	
	
	/**
	 * Gets the ID of a players team. Empty string if none.
	 * @return Team ID
	 */
	public String getTeamID() {
		return teamid;
	}
	
	
	/**
	 * Gets the team the player is on.
	 * @return The team.
	 */
	public TeamData getTeam() {
		return team;
	}
}
