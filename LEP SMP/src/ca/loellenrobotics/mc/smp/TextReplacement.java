package ca.loellenrobotics.mc.smp;

import net.md_5.bungee.api.ChatColor;

public final class TextReplacement {

	public static String parse(String string) {
		
		string = ChatColor.translateAlternateColorCodes('&', string);
		string = AntiSwear.censor(string);
		
		return string;
	}
	
	public static String lightParse(String string) {
		return AntiSwear.censor(string);
	}
	
}
