/**
 *	**	**	**	**	**	**	**	**
 * 	Update.java
 * 	menu
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		Alvin
 * 	Year		:		2013
 * 	Time		:		21:52:42
 * 
 *	**	**	**	**	**	**	**	**
 */
package menu;

import org.lwjgl.opengl.Display;

import singleplayer.server;
import GUI.Button;
import game.Text;

/**
 * @author Alvin
 */
public class Main {
	
	static boolean load = false;
	
	public static void update() {
		if (!load) {
			load();
		} else {
			Multiplayer.update();
			Settings.update();
			singleplayer.Dev.update();
			Login.update();
		}
	}
	
	public static void load() {
		if (!menu.Multiplayer.connected) {
			GUI.Background.draw("dirt");
			if (menu.Multiplayer.connection_path == 0) {
				GUI.Button.draw("INFO", "LOADING..", Display.getWidth()/2, Display.getHeight()/2 - 246, 0, 0, "stonebrick", 24, 128);
				if (menu.Multiplayer.connection_init) {
					try {
						System.out.println("[INFO]: Connecting to " + singleplayer.server.address);
						//client.Main.say("login");
						//client.Main.connect("localhost");
						load = true;
							System.out.println("[INFO]: Connected to " + singleplayer.server.address);
					} catch (Exception e) {
						menu.Multiplayer.connection_path = 1;
						menu.Multiplayer.connection_init = false;
					}
				}
			}
			if (menu.Multiplayer.connection_path == 1) {
				try {
					server.address = server.address = "localhost";
					GUI.Button.draw("INFO", "CONNECTING TO " + singleplayer.server.address, Display.getWidth()/2-64 * 4, Display.getHeight()/2 - 246, 6, 1, "stonebrick", 16, 128);
					System.out.println("[INFO]: Connecting to " + singleplayer.server.address);
					//singleplayer.server.sendGet("http://" + server.address + "/index.php?field0=GET&&field1=player.online");
					//client.Main.say("login");
					//client.Main.connect("localhost");
					load = true;
				} catch (Exception e) {
					System.out.println("[INFO]: Connection failed: " + e);
					e.printStackTrace();
				}
			}
			menu.Multiplayer.connection_init = true;
		}
	}
	
}
