/**
 *	**	**	**	**	**	**	**	**
 * 	Multiplayer.java
 * 	menu
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		Alvin
 * 	Year		:		2014
 * 	Time		:		19:52:51
 * 
 *	**	**	**	**	**	**	**	**
 */
package menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.lwjgl.opengl.GL11.*;

import GUI.Texture;
import _3D.Cube;
import player.Player1;
import player.Player2;
import player.Player3;
import player.Player4;
import player.Player1.dir;
import singleplayer.server;
import sound.Sound;

import org.lwjgl.opengl.GL12.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Alvin
 */
public class Multiplayer {
	static boolean update = true;
	static Random r = new Random();
	static boolean connected = false;
	static boolean connection_init = false;
	static int connection_path = 0;
	static String status1 = "JOIN_SERVER " + "";
	static String Connect_button = "Connect";
	@SuppressWarnings("unused")
	public static void update() {
		if (update) {
			game.Text.drawChar((char)1, 0, 1024, 128, 128, Color.white);
			Sound.random("random", 1f, "OGG", "res/sound/music", 11);
			//Sound.random("random", 1f, "OGG", "res/sound/music/records", 11);
			GUI.Background.draw("dirt");
			int x = 0;
			game.Text.draw("Server address:", Display.getWidth()/2-192, Display.getHeight()/2+160, 16, Color.white);
			GUI.Input.draw("IP", Display.getWidth()/2-192, Display.getHeight()/2+92, 64, 48, 6, "\1\2");
			//if (connected == false) if (client.Main.say("request").toString() == "DISCONNECTED\0" || client.Main.say("request").toString() == "HOST NOT FOUND\0") status1 = "Server not found.";
			if (GUI.Button.draw("Connect", Connect_button, Display.getWidth()/2-64*3, Display.getHeight()/2+46, 8, 1, "planks_oak", 16, 48)) {
				Connect_button = "Attempting to connect to server";
				client.Main.say("0001");
				int connect_status = client.Main.connect(GUI.Input.value[GUI.Input.find("IP")]);
				if (connect_status == 1) {
					Connect_button = "Unknown Host";
					connected = false;
				} else if (connect_status == 2) {
					Connect_button = "Server not found";
					connected = false;
				} else {
					Connect_button = "Server found";
					connected = true;
				}
				//client.Main.say(GUI.Input.value[GUI.Input.find("IP")]);
			}
			
			//BufferedImage test = ;//new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
			if (false) {
				client.Main.say("0002");
				int tex = GUI.Texture.loadTexture(client.Protocol.server_icon);
				int x2, y2 = 0;
				int width = 64, height = 64;
				int x1 = (Display.getWidth()/2-64*3)-64, y1 = (Display.getHeight()/2-2)-64;
				x2 = x1+width;
				y2 = y1+height;
				glEnable(GL_TEXTURE_2D);
				glBindTexture(GL_TEXTURE_2D, tex);
				Color.white.bind();
				if (height == 0) y2 = y1+64;
				if (width == 0) x2 = x1+64;
				GL11.glBegin(GL11.GL_POLYGON);
					GL11.glTexCoord2f(0, 0);
			    	GL11.glVertex2f(x1, y1);
			    	GL11.glTexCoord2f(0, 1);
			    	GL11.glVertex2f(x1, y2);
			    	GL11.glTexCoord2f(1, 1);
			    	GL11.glVertex2f(x2, y2);
			    	GL11.glTexCoord2f(1, 0);
			    	GL11.glVertex2f(x2, y1);
				GL11.glEnd();
			}
			if (connected) {
				if (GUI.Button.draw("Join", client.Protocol.server_name, Display.getWidth()/2-64*3, Display.getHeight()/2-2, 8, 1, "planks_oak", 16, 48)) {
					Login.draw();
					/*if (status1 == "SERVER NOT FOUND.") {
						singleplayer.server.address = GUI.Input.value[GUI.Input.find("IP")];
						status1 = "Connected!";
					} else {
					try {
							x = Integer.parseInt(singleplayer.server.sendGet("http://" + server.address + "/index.php?field0=GET&&field1=player.online"));
							/* int y = StringUtils.substring(x, 1);
							if (x == 0) Player1.me = true;
							if (x == 1) Player2.me = true;
							if (x == 2) Player3.me = true;
							if (x == 3) Player4.me = true;
							player.Main.Player = "YOU ARE: PLAYER " + (x + 1);
							try {
								Integer.parseInt(singleplayer.server.sendGet("http://" + server.address + "/index.php?field0=SET&&field1=player.online&&field2=" + (x + 1)));
							} catch (Exception e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (x > 3) {
							GUI.Button.draw("Info", "SERVER IS FULL", Display.getWidth()/2-64*3, Display.getHeight()/2-64, 3, 1, "planks_oak", 16, 128);
						} else {
							GUI.Destroy.menus(true);
							singleplayer.Dev.start();
						}
					}*/
				}
			}
			
			/*if (GUI.Button.draw("Settings", "Settings", Display.getWidth()/2-64*3, Display.getHeight()/2-50, 8, 1, "planks_oak", 16, 48)) {
				menu.Multiplayer.update = false;
				menu.Settings.update = true;
				menu.Settings.c = System.currentTimeMillis() + 500;
			}*/
			/*if (GUI.Button.draw("Clear", "CLEAR SERVER", Display.getWidth()/2-64 * 3, Display.getHeight()/2 - 246, 3, 1, "stonebrick", 16, 128)) {
				//Cube.draw();
				singleplayer.server.sendGet("http://" + server.address + "/index.php?field0=SET&&field1=player.online&&field2=" + 0);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_F1)) {
				System.out.print("[singleplayer.server.address]: ");
				singleplayer.server.address = "hej";
				System.out.println(singleplayer.server.address);
			}*/
			//_3D.Kernel.drawCube(512, 512, 0, 1024, 1024, 2);
 		}
	}
	
	public static void destroy(boolean Music) {
		update = false;
	}
	
	public static void start() {
		update = true;
	}
	
}