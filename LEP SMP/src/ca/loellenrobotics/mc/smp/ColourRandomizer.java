package ca.loellenrobotics.mc.smp;

import java.awt.Color;
import java.util.Random;

/**
 * Copy & modified from an online forum. Generates a random pastel colour for names. 
 * @author Liam Robins
 *
 */
public class ColourRandomizer {

	private final Color COLOUR;
	
	public Color getColour() {
		return COLOUR;
	}
	
	public String getHex() {
		return "#"+Integer.toHexString(COLOUR.getRGB()).substring(2);
	}
	
	public ColourRandomizer(){
		Random random = new Random();
		final float hue = random.nextFloat();
		final float saturation = (random.nextInt(2000) + 5000) / 10000f;
		final float luminance = 0.9f;
	
		final Color color = Color.getHSBColor(hue, saturation, luminance);
		this.COLOUR = color;
	}
	
}
