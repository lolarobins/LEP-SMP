package ca.loellenrobotics.mc.smp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ca.loellenrobotics.mc.smp.command.Colour;
import ca.loellenrobotics.mc.smp.command.Grade;
import ca.loellenrobotics.mc.smp.command.Me;
import ca.loellenrobotics.mc.smp.command.Message;
import ca.loellenrobotics.mc.smp.command.Name;
import ca.loellenrobotics.mc.smp.command.Reply;
import ca.loellenrobotics.mc.smp.command.School;
import ca.loellenrobotics.mc.smp.listener.Chat;
import ca.loellenrobotics.mc.smp.listener.Join;
import ca.loellenrobotics.mc.smp.listener.Leave;
import ca.loellenrobotics.mc.smp.listener.ServerList;
import ca.loellenrobotics.mc.smp.listener.Sign;

/**
 * Class extending JavaPlugin
 * @author Liam Robins
 *
 */
public class SMPPlugin extends JavaPlugin {
	
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
		
		// Updates tab list & others.
		Updater.run(this);
	}
	
	
	// Registers each listener. This allows modification of events in game.
	private void registerListeners() {
		// #registerEvents(<class that implements Listener>, <JavaPlugin, use this>) 
		getServer().getPluginManager().registerEvents(new Chat(this), this);
		getServer().getPluginManager().registerEvents(new Join(this), this);
		getServer().getPluginManager().registerEvents(new Sign(), this);
		getServer().getPluginManager().registerEvents(new ServerList(), this);
		getServer().getPluginManager().registerEvents(new Leave(this), this);
	}
	
	
	// Registers each command. Must be defined in plugin.yml.
	private void registerCommands() {
		// #getCommand("<command name in plugin.yml>").setExecutor(<class that implements CommandExecutor>)
		getCommand("message").setExecutor(new Message(this));
		getCommand("reply").setExecutor(new Reply(this));
		getCommand("me").setExecutor(new Me(this));
		getCommand("name").setExecutor(new Name(this));
		getCommand("school").setExecutor(new School(this));
		getCommand("grade").setExecutor(new Grade(this));
		getCommand("colour").setExecutor(new Colour(this));
	}
	
}
