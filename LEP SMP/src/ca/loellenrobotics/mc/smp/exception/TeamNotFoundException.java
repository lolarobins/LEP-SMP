package ca.loellenrobotics.mc.smp.exception;

/**
 * Thrown if a team is not found.
 * @author Liam Robins
 *
 */
public class TeamNotFoundException extends Exception {

	private static final long serialVersionUID = -2555423923846385273L;

	public TeamNotFoundException(String message) {
		super(message);
	}
	
}
