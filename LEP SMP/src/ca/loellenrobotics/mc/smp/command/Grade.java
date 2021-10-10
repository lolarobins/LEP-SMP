package ca.loellenrobotics.mc.smp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;

/**
 * Sets player's grade
 * @author Liam Robins
 *
 */
public class Grade implements CommandExecutor{

	final SMPPlugin INSTANCE;
	
	public Grade(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(!(s instanceof Player)) return true;
		
		Player p = (Player) s;
		
		if(args.length != 1) {
			p.sendMessage(ChatColor.RED + "Please enter your grade.");
			return true;
		}
		
		try {
			
			int grade = Integer.parseInt(args[0]);
			if(grade > 13 || grade < 7 && grade != 0) throw new NumberFormatException();
			
			PlayerData data = PlayerData.get(INSTANCE, p.getUniqueId());
			data.setGrade(grade);
			if(grade != 0) p.sendMessage(ChatColor.GREEN + "Grade set to " + grade);
			else p.sendMessage(ChatColor.GREEN + "Your grade has been reset");
			
		} catch (NumberFormatException e) {
			p.sendMessage(ChatColor.RED + "Invalid grade entered. Must be in range of 7-13.");
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
	
}
