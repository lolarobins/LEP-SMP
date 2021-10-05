package ca.loellenrobotics.mc.smp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ca.loellenrobotics.mc.smp.command.Message;
import ca.loellenrobotics.mc.smp.command.Reply;
import ca.loellenrobotics.mc.smp.listener.Chat;
import ca.loellenrobotics.mc.smp.listener.Join;
import ca.loellenrobotics.mc.smp.listener.Sign;

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
		registerListeners();
		
		// Registers commands.
		registerCommands();
		
		// Loads data for players if they are already in game.
		for(Player player : Bukkit.getOnlinePlayers()) {
			Loader.loadPlayer(this, player.getUniqueId());
		}
	}
	
	
	// Registers each listener. This allows modification of events in game.
	private void registerListeners() {
		// #registerEvents(<class that implements Listener>, <JavaPlugin, use this>) 
		getServer().getPluginManager().registerEvents(new Chat(this), this);
		getServer().getPluginManager().registerEvents(new Join(this), this);
		getServer().getPluginManager().registerEvents(new Sign(), this);
	}
	
	
	// Registers each command. Must be defined in plugin.yml.
	private void registerCommands() {
		// #getCommand("<command name in plugin.yml>").setExecutor(<class that implements CommandExecutor>)
		getCommand("message").setExecutor(new Message(this));
		getCommand("reply").setExecutor(new Reply(this));
	}
	
}
