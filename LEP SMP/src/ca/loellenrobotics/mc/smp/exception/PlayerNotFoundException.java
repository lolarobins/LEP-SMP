package ca.loellenrobotics.mc.smp.exception;

/**
 * As the name suggests, thrown when a player cannot be found.
 * @author Liam Robins
 *
 */
public class PlayerNotFoundException extends Exception {
	
	private static final long serialVersionUID = 7439762995650615170L;
	
	public PlayerNotFoundException(String message) {
		super(message);
	}
}
