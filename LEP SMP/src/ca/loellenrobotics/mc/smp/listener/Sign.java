package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import ca.loellenrobotics.mc.smp.AntiSwear;

/**
 * Censors sign text.
 * @author Liam Robins
 */
public class Sign implements Listener {

	@EventHandler
	public void signChange(SignChangeEvent e) {
		
		for(int i = 0; i < 4; i++) {
			e.setLine(i, AntiSwear.censor(e.getLine(i)));
		}
		
	}
	
}
