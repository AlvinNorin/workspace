package io.github.alvinnorin.agar;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class matrix {

	static HashMap<Player, Integer> owned_x = new HashMap();
	static HashMap<Player, Integer> owned_z = new HashMap();
	static HashMap<Player, Integer> owned_mass = new HashMap();
	static HashMap<Player, Boolean> claim_done = new HashMap();
	static HashMap<Player, String> owned = new HashMap();
	
	public static void claim(Player player, int x, int y) {
		try {
			owned_x.containsKey(player);
			owned_z.containsKey(player);
			owned_mass.containsKey(player);
		} catch (NullPointerException e) {
			owned_x.put(player, 0);
			owned_z.put(player, 0);
			owned_mass.put(player, 0);
        }
		owned_x.put(player, 1);
		owned_x.put(player, 0);
		owned_x.put(player, 2);
		owned_x.put(player, 4);
		player.sendMessage(owned_x.get(player)+"");
	}
	
	public static void put(Player player, int x, int z) {
		String current = owned.get(player);
		if (current == null) current = "";
		owned.put(player, current+","+x+" "+z);
		//player.sendMessage(owned.get(player));
	}
	
	public static void clear(Player player) {
		try {
			owned.get(player);
		} catch (NullPointerException e) {
			owned.put(player, "");
        }
		owned.put(player, "");
	}
	
	public static void get(Player player) {
		String[] s1 = owned.get(player).split(",");
		for (int c = 0; c < s1.length; c++) {
			
		}
	}
	
	public static boolean contains(Player player, int x, int z) {
		boolean rtn = false;
		try {
			rtn = owned.get(player).contains(x + " " + z);
		} catch (java.lang.NullPointerException e) {
			
		}
		return rtn;
	}
	
	public static void done(Player player) {
		//owned_x.forEach(action);
	}
	
}
