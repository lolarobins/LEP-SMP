package ca.loellenrobotics.mc.smp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.MinecraftServer;

/**
 * Scheduled repeating task - for tab list, etc.
 * @author Liam Robins
 *
 */
public class Updater {
	
	public static void run(SMPPlugin instance) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {

			@Override
			public void run() {
				try {
					new Updater(instance);
				} catch (PlayerNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}, 10, 20);
	}

	public Updater(SMPPlugin instance) throws PlayerNotFoundException {
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			PlayerData data = PlayerData.get(instance, p.getUniqueId());
			p.setPlayerListName(ChatColor.of(data.getColourHex()) + p.getName());
			p.setPlayerListHeaderFooter(ChatColor.LIGHT_PURPLE + "Lo-Ellen Park SMP",
					ChatColor.LIGHT_PURPLE + "Ping: " + ChatColor.YELLOW + p.getPing() + "ms"
							+ "\n" + ChatColor.LIGHT_PURPLE + "TPS: " + ChatColor.YELLOW + MinecraftServer.TPS);
		}
		
	}
	
}
