package ca.loellenrobotics.mc.smp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Grabs YAML files from plugin directory.
 * @author Liam Robins
 */
public class DataFile {
	
	private final JavaPlugin PLUGIN;
	private final String NAME;
	private final File FILE;
	private final FileConfiguration CONFIG;
	
	/**
	 * Loads a file from the plugin's directory.
	 * @param plugin The JavaPlugin instance.
	 * @param name The name of the file. (Ex: config)
	 */
	public DataFile(JavaPlugin plugin, String name) {
		
		PLUGIN = plugin;
		NAME = name;
		
		FILE = new File(PLUGIN.getDataFolder(), NAME);
		
		if (!FILE.exists()) {
			FILE.getParentFile().mkdirs();
            PLUGIN.saveResource(NAME, false);
		}
		
		CONFIG = new YamlConfiguration();
		
		try {
			CONFIG.load(FILE);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Saves the file's configuration.
	 */
	public void save() {
		
		if (FILE == null || CONFIG == null) {
			return;
		}
		
		try {
			CONFIG.save(FILE);
		} catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Gets the name of the file.
	 * @return the file's name.
	 */
	public String getFileName() {
		return NAME;
	}
	
	/**
	 * Gets the file.
	 * @return the file.
	 */
	public File getFile() {
		return FILE;
	}
	
	/**
	 * Gets the FileConfiguration.
	 * @return the file's configuration.
	 */
	public FileConfiguration getConfig() {
		return CONFIG;
	}
	
}
