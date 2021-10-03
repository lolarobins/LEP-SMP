package ca.loellenrobotics.mc.smp;

import net.md_5.bungee.api.ChatColor;

public final class TextReplacement {

	public static void parse(String string) {
		
		string = ChatColor.translateAlternateColorCodes('&', string);
		string = AntiSwear.censor(string);
		
	}
	
}
