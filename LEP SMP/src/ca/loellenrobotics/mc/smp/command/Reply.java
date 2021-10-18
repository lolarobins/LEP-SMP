package ca.loellenrobotics.mc.smp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.loellenrobotics.mc.smp.PlayerData;
import ca.loellenrobotics.mc.smp.SMPPlugin;
import ca.loellenrobotics.mc.smp.TextReplacement;
import ca.loellenrobotics.mc.smp.exception.PlayerException;
import net.md_5.bungee.api.ChatColor;

/**
 * Command for replying to the last person.
 * @author Liam Robins
 *
 */
public class Reply implements CommandExecutor {

	private final SMPPlugin INSTANCE;
	
	public Reply(SMPPlugin instance) {
		INSTANCE = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		// Gets the last player
		Player t = Bukkit.getPlayer(Message.lastReply.get(s.getName()));
		
		// If the player isn't online or recognized
		if(t == null) {
			s.sendMessage("§cThere is nobody to reply to.");
			return true;
		}
		
		// If no message is written
		if(args.length == 0) {
			s.sendMessage("§cPlease enter a message.");
			return true;
		}
				
		String msgToSend = "";
		
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
			} catch (PlayerException e) {
				e.printStackTrace();
			}
		}
		
		// Sends the message
		try {
			
			String format = ChatColor.of(senderColour) + s.getName()+" §r§7>§r "+ChatColor.of(PlayerData.get(INSTANCE, t.getUniqueId()).getColourHex()) + t.getName() + "§r§8:§r " + msgToSend;
			s.sendMessage(format);
			t.sendMessage(format);
			
			Message.lastReply.put(s.getName(), t.getName());
			Message.lastReply.put(t.getName(), s.getName());
			
		} catch (PlayerException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
