package ca.loellenrobotics.mc.smp.command;

import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;

/**
 * Sets a name
 * @author Liam Robins
 *
 */
public class Name implements CommandExecutor {

	final SMPPlugin INSTANCE;
	
	public Name(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(!(s instanceof Player)) return true;
		
		Player p = (Player) s;
		
		if(args.length == 0) {
			p.sendMessage(ChatColor.RED + "Please enter a name.");
			return true;
		}
		
		String name = "";
		for(String input : args) {
			if(!Pattern.compile("^[a-zA-Z]+$").matcher(input).matches()) {
				p.sendMessage(ChatColor.RED + "Name can only contain letters.");
				return true;
			}
			name += input + " ";
		}
		
		try {
			
			PlayerData data = PlayerData.get(INSTANCE, p.getUniqueId());
			
			data.setName(name);
			p.sendMessage(ChatColor.GREEN + "Your name has been set to " + name);
			
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	
	
}
