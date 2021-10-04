package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.Listener;

import ca.loellenrobotics.mc.smp.Plugin;

/**
 * Handles player stuff when they join.
 * @author Liam Robins
 *
 */
public class Join implements Listener {
	
	private final Plugin INSTANCE;
	
	public Join(Plugin instance) {
		INSTANCE = instance;
	}
	

}
