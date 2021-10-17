package ca.loellenrobotics.mc.smp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.md_5.bungee.api.ChatColor;

/**
 * Sets the server's name in server list, etc.
 * @author Liam Robins
 *
 */
public class ServerList implements Listener {

	@EventHandler
	public void list(ServerListPingEvent e) {
		e.setMotd(ChatColor.LIGHT_PURPLE + "" + "Lo-Ellen Park SMP\n"
				+ ChatColor.YELLOW + "1.17.1");
	}
	
}
