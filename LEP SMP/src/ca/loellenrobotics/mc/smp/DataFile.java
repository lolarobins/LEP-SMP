package ca.loellenrobotics.mc.smp;

import java.io.File;
import java.io.FileNotFoundException;
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
	private final boolean NEW;
	
	
	/**
	 * Loads a file from the plugin's directory.
	 * @param plugin The JavaPlugin instance.
	 * @param name The name of the file. (Ex: config)
	 * @throws InvalidConfigurationException If configuration file is invalid.
	 * @throws IOException IOException thrown by loading file.
	 * @throws FileNotFoundException File does not exist.
	 */
	public DataFile(JavaPlugin plugin, String name) throws FileNotFoundException, IOException, InvalidConfigurationException {

		PLUGIN = plugin;
		NAME = name;
		
		FILE = new File(PLUGIN.getDataFolder(), NAME);
		
		if (!FILE.exists()) {
			FILE.getParentFile().mkdirs();
            PLUGIN.saveResource(NAME, false);
		}
		
		NEW = false;
		
		CONFIG = new YamlConfiguration();
		CONFIG.load(FILE);
		
	}
	
	
	/**
	 * Loads a file from the plugin's directory.
	 * @param plugin The JavaPlugin instance.
	 * @param name The name of the file. (Ex: config)
	 * @param create Create file if it does not already exist.
	 * @throws InvalidConfigurationException If configuration file is invalid.
	 * @throws IOException IOException thrown by loading file.
	 * @throws FileNotFoundException File does not exist.
	 */
	public DataFile(JavaPlugin plugin, String name, boolean create) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		PLUGIN = plugin;
		NAME = name;
		boolean isNew = false;
		
		FILE = new File(PLUGIN.getDataFolder(), NAME);
		
		if (!FILE.exists()) {
			FILE.getParentFile().mkdirs();
            PLUGIN.saveResource(NAME, false);
            if(create) FILE.createNewFile();
            isNew = true;
		}
		
		NEW = isNew;
		
		CONFIG = new YamlConfiguration();
		CONFIG.load(FILE);
		
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
	
	
	/**
	 * Get if the file was just created.
	 * @return Returns true if it was just created.
	 */
	public boolean isNewFile() {
		return NEW;
	}
	
}
