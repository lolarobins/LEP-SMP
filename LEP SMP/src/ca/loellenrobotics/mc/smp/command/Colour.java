package ca.loellenrobotics.mc.smp.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.ColourRandomizer;
import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Allows players to set the colour of their name.
 * @author Liam Robins
 *
 */
public class Colour implements CommandExecutor {
	
	final SMPPlugin INSTANCE;
	
	public Colour(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	public static boolean validHexCode(final String colorCode) {
        Matcher matcher = Pattern.compile("^#([a-fA-F0-9]{6})$").matcher(colorCode);
        return matcher.matches();
    }
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(!(s instanceof Player)) return true;
		
		try {
			
			Player p = (Player) s;
			PlayerData data = PlayerData.get(INSTANCE, p.getUniqueId());
			
			if(args.length == 0) {
				data.setColourHex(new ColourRandomizer().getHex());
				s.sendMessage(ChatColor.GREEN + "Your name colour has been randomly set to " + ChatColor.of(data.getColourHex()) + data.getColourHex() + ChatColor.GREEN + ".");
				return true;
			}
			
			data.setColourHex(args[0]);
			s.sendMessage(ChatColor.GREEN + "Your name colour has been set to " + ChatColor.of(data.getColourHex()) + data.getColourHex() + ChatColor.GREEN + ".");
			
		} catch (PlayerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			s.sendMessage(ChatColor.RED + "Invalid colour format. Valid hex code must be in format '#HEXCDE'.");
		}
		
		return true;
	}

}
