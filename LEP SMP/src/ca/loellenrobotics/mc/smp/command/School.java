package ca.loellenrobotics.mc.smp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Sets the school the player goes to
 * @author Liam Robins
 *
 */
public class School implements CommandExecutor {

	final SMPPlugin INSTANCE;
	
	public School(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(!(s instanceof Player)) return true;
		
		Player p = (Player) s;
		
		if(args.length == 0) {
			p.sendMessage(ChatColor.RED + "Please enter a school.");
			return true;
		}
		
		String name = "";
		for(String input : args) {
			name += input + " ";
		}
		
		try {
			
			PlayerData data = PlayerData.get(INSTANCE, p.getUniqueId());
			
			data.setSchool(name);
			p.sendMessage(ChatColor.GREEN + "Your school has been set to " + name);
			
		} catch (PlayerException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
