/**
 *	**	**	**	**	**	**	**	**
 * 	Login.java
 * 	menu
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		alvin
 * 	Year		:		2015
 * 	Time		:		2:07:34 PM
 * 
 *	**	**	**	**	**	**	**	**
 */
package menu;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

/**
 * @author alvin
 */
public class Login {

	static boolean update = false;
	
	public static void update() {
		if (update) {
			GUI.Background.draw("dirt");
			game.Text.draw("Username:", Display.getWidth()/2-192, Display.getHeight()/2+160, 16, Color.white);
			GUI.Input.draw("Username", Display.getWidth()/2-192, Display.getHeight()/2+92, 64, 48, 6, "(your name here)");
			game.Text.draw("Password:", Display.getWidth()/2-192, Display.getHeight()/2+64, 16, Color.white);
			GUI.Input.password("Password", Display.getWidth()/2-192, Display.getHeight()/2-4, 64, 48, 6, "(password here)");
		}
	}
	
	public static void draw() {
		update = true;
	}
	
	public static void destroy() {
		update = false;
	}
	
}
