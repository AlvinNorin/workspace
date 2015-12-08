/**
 *	**	**	**	**	**	**	**	**
 * 	Menu.java
 * 	mainMenu
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		Alvin
 * 	Year		:		2013
 * 	Time		:		20:27:18
 * 
 *	**	**	**	**	**	**	**	**
 */
package menu;

import game.Text;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import GUI.Button;
import sound.Sound;
import file.*;

/**
 * @author Alvin
 */
public class Settings {
	static Random r = new Random();
	static boolean update = false;
	static long c = 0;
	static int d = 256;
	static String settings = "";
	
	static boolean sounds = false;
	static boolean fullscreen = false;
	
	public static void update() {
		if (update) {
			try {
				file.Read.setFile("res/Settings.conf");
				settings = file.Read.readFile().toString();
				file.Read.closeFile();
			} catch (Exception e) {
				Create.write("res/Settings.conf", "sounds=true\nfullscreen=true");
				Create.closeFile();
				file.Read.setFile("res/Settings.conf");
				settings = file.Read.readFile().toString();
				file.Read.closeFile();
			}
			sounds = settings.contains("sounds=true");
			fullscreen = settings.contains("fullscreen=true");
			if (sounds) {
				Sound.mute = true;
			} else {
				Sound.mute();
			}
			Sound.random("random", 1f, "OGG", "res/sound/music", 11);
			GUI.Background.draw("dirt");
			Text.draw("SETTINGS", GUI.Coordinates.x/2-94, GUI.Coordinates.y/4*3, 6, Color.white);
			if (GUI.Button.draw("MUSIC", "Sounds: " + sounds, Display.getWidth()/2-128, GUI.Coordinates.y/2, 6, 1, "planks_oak", 16, 48) && System.currentTimeMillis() > c) {
				if (sounds) {
					sound.Sound.mute();
					Create.write("res/Settings.conf", settings.replace("sounds=true", "sounds=false"));
					Create.closeFile();
				} else {
					sound.Sound.mute = false;
					Create.write("res/Settings.conf", settings.replace("sounds=false", "sounds=true"));
					//Create.write("sounds=true");
					Create.closeFile();
				}
				c = System.currentTimeMillis()+d;
			}
			if (GUI.Button.draw("FULLSCREEN", "FULLSCREEN:" + fullscreen, Display.getWidth()/2-128, GUI.Coordinates.y/2-48, 6, 1, "planks_oak", 16, 48) && System.currentTimeMillis() > c) {
				if (fullscreen) {
					try {
						Display.setFullscreen(false);
					} catch (LWJGLException e) {
						e.printStackTrace();
					}
					Create.write("res/Settings.conf", settings.replace("fullscreen=true", "fullscreen=false"));
					Create.closeFile();
				} else {
					try {
						Display.setFullscreen(true);
					} catch (LWJGLException e) {
						e.printStackTrace();
					}
					sound.Sound.mute = false;
					Create.write("res/Settings.conf", settings.replace("fullscreen=false", "fullscreen=true"));
					//Create.write("sounds=true");
					Create.closeFile();
				}
				c = System.currentTimeMillis()+d;
			}
			
			if (GUI.Button.draw("BACK", "Back", Display.getWidth()/2-128, GUI.Coordinates.y/2-128, 6, 1, "planks_oak", 16, 48)) {
				menu.Settings.update = false;
				menu.Multiplayer.update = true;
			}
			file.Read.closeFile();
		}
	}
	
	public static void destroy(boolean Music) {
		update = false;
	}
	
	public static void start() {
		update = true;
	}
	
}