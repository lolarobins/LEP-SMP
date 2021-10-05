package ca.loellenrobotics.mc.smp.exception;

/**
 * Used for PlayerData, this throws on certain things that require a player to be online.
 * @author Liam Robins
 */
public class PlayerOfflineException extends Exception {
	
	private static final long serialVersionUID = -438711892936069L;
	
	public PlayerOfflineException(String message) {
		super(message);
	}

}
