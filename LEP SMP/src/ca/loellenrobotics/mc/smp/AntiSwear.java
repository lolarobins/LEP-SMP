package ca.loellenrobotics.mc.smp;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles the anti-swear for LEP server.
 * @author Liam Robins
 */
public class AntiSwear {
	
	private static ArrayList<String> standalone = new ArrayList<String>(), contains = new ArrayList<String>();
	
	
	/**
	 * Loads the swears into memory. Run this in onEnable.
	 * @param plugin The plugin instance to load with.
	 */
	public static void load(JavaPlugin plugin) {
		
		DataFile swearfile = null;
		
		try {
			swearfile = new DataFile(plugin, "swears.yml");
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		if (swearfile.getConfig().getStringList("standalone") != null) {
			standalone.addAll(swearfile.getConfig().getStringList("standalone"));
		}
		
		if (swearfile.getConfig().getStringList("contains") != null) {
			contains.addAll(swearfile.getConfig().getStringList("contains"));
		}
		
	}
	
	
	/**
	 * Censors any text.
	 * @param string The text to censor.
	 * @return the censored text.
	 */
	public static String censor(String string) {
		
		String censored = string;
		
		// Replaces words that contain swears.
		if(!contains.isEmpty()) {
			for(String str : string.split(" ")) {
				for(String str2 : contains) {
					if(str.contains(str2)) {
						censored = censored.replaceAll(str, tag(str));
					}
				}
			}
		}
		
		// Replaces words that are defined themselves (prevents words such as assistance for being censored).
		if(!standalone.isEmpty()) {
			for(String str : string.split(" ")) {
				if(standalone.contains(str.toLowerCase())) {
					censored = censored.replaceAll(str, tag(str));
				}
			}
		}
		
		return censored;
	
	}
	
	
	/**
	 * Replaces a certain string with hashtags.
	 * @param string String to replace.
	 * @return a string with hashtags replacing all characters, including spaces.
	 */
	public static String tag(String string) {
		
		String tags = "";
		
		for(int i=0; i < string.length(); i++) {
			tags=tags+"#";
		}
		
		return tags;
	
	}
	
}
