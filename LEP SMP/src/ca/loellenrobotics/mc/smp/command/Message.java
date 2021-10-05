package ca.loellenrobotics.mc.smp.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.Plugin;
import ca.loellenrobotics.mc.smp.TextReplacement;
import ca.loellenrobotics.mc.smp.exception.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;

/**
 * Command for messaging other players.
 * @author Liam Robins
 *
 */
public class Message implements CommandExecutor {

	private final Plugin INSTANCE;
	
	public Message(Plugin instance) {
		INSTANCE = instance;
	}
	
	// Stores replies for /reply, /r
	static HashMap<String, String> lastReply = new HashMap<String, String>();
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {

		// If no player is specified
		if(args.length == 0) {
			s.sendMessage("§7» §cPlease specify a player.");
			return true;
		}
		
		// Gets the targeted player
		Player t = Bukkit.getPlayer(args[0]);
		
		// If the player isn't online or recognized
		if(t == null) {
			s.sendMessage("§7» §cPlayer not recognized.");
			return true;
		}
		
		// If no message is written
		if(args.length == 1) {
			s.sendMessage("§7» §cPlease enter a message.");
			return true;
		}
		
		String msgToSend = "";
		args[0] = "";
		
		// Turns string collection into a single string message
		for(String tmp : args) {
			msgToSend += tmp + " ";
		}
		
		// Censors message
		msgToSend = TextReplacement.parse(msgToSend);
		
		String senderColour = "";
		Player p = null;
		
		// Sets the colour of the sender, needed for console senders
		if(!(s instanceof Player)) {
			senderColour = "#FF0000";
		}else {
			p = (Player) s;
			
			try {
				senderColour = PlayerData.get(INSTANCE, p.getUniqueId()).getColourHex();
			} catch (PlayerNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// Sends the message
		try {
			
			String format = ChatColor.of(senderColour) + s.getName()+" §r§7>§r "+ChatColor.of(PlayerData.get(INSTANCE, t.getUniqueId()).getColourHex()) + t.getName() + "§r§8:§r" + msgToSend;
			s.sendMessage(format);
			t.sendMessage(format);
			
			lastReply.put(s.getName(), t.getName());
			lastReply.put(t.getName(), s.getName());
			
		} catch (PlayerNotFoundException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	
	
}
