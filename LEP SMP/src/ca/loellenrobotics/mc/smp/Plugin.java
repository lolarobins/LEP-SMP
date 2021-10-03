package ca.loellenrobotics.mc.smp;

import org.bukkit.plugin.java.JavaPlugin;

import ca.loellenrobotics.mc.smp.listener.Chat;

/**
 * Class extending JavaPlugin
 * @author Liam Robins
 *
 */
public class Plugin extends JavaPlugin {
	
	/*
	 * This class file is the API's 'point of contact' when loading the plugin from the /plugins directory.
	 * 
	 * Commands, events, etc. must be registered in either onEnable, or onLoad overrided methods.
	 * onLoad - called before world is loaded.
	 * onEnable - called after world is loaded. (Recommended, especially if hooked to other plugins as most plugins register things in their enable methods.)
	 */
	
	@Override
	public void onEnable() {
		
		// Pulls swears from file and stores them into memory.
		AntiSwear.load(this);
	
		// Registers the listeners.
		getServer().getPluginManager().registerEvents(new Chat(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
