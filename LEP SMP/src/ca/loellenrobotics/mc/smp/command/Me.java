package ca.loellenrobotics.mc.smp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Describes an action.
 * @author Liam Robins
 *
 */
public class Me implements CommandExecutor {

	final SMPPlugin INSTANCE;
	
	public Me(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(args.length == 0) {
			s.sendMessage("§cPlease enter a message.");
			return true;
		}
		
		String colour;
		if(!(s instanceof Player)) {
			colour = "#FF0000";
		}else {
			try {
				colour = PlayerData.get(INSTANCE, ((Player) s).getUniqueId()).getColourHex();
			} catch (PlayerException e) {
				colour = "#FF0000";
			}
		}
		
		String msg = "";
		for(String tmp : args) {
			msg = msg+tmp+" ";
		}
		
		Bukkit.broadcastMessage(ChatColor.of(colour)+"* "+s.getName() + " "+msg+"*");
		return true;
	}

}
