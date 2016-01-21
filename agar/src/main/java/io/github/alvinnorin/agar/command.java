package io.github.alvinnorin.agar;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.alvinnorin.agar.agarPlayer;
import io.github.alvinnorin.agar.agar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class command implements CommandExecutor, Listener {
    public static agar plugin;
    private static ProtocolManager protocolManager;
    static ScoreboardManager manager;
    static Scoreboard board;
    static Objective objective;

    public command(agar instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
    }

    public static void enable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName((Object)ChatColor.RED + "command [SAT]");
        for (Player online2 : Bukkit.getOnlinePlayers()) {
            Score score = objective.getScore(online2.getName() + ": ");
            score.setScore(0);
        }
        for (Player online2 : Bukkit.getOnlinePlayers()) {
            online2.setScoreboard(board);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left the server! :'(");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	/*if (event.getPlayer().getLocation().getBlockX() != agarPlayer.x.get(event.getPlayer())
    			|| event.getPlayer().getLocation().getBlockZ() != agarPlayer.z.get(event.getPlayer()))
        try {
            int x = agarPlayer.x.get((Object)event.getPlayer());
            int z = agarPlayer.z.get((Object)event.getPlayer());
            int[] pos = new int[]{x, 4, z};
            int[] nev = new int[]{event.getPlayer().getLocation().getBlockX()-4, 4, event.getPlayer().getLocation().getBlockZ()-4};
            agarPlayer.clean(event.getPlayer(), pos, nev);
            //agarPlayer.x.put(event.getPlayer(), event.getPlayer().getLocation().getBlockX());
            //agarPlayer.z.put(event.getPlayer(), event.getPlayer().getLocation().getBlockZ());
        }
        catch (NullPointerException e) {
            agarPlayer.x.put(event.getPlayer(), event.getPlayer().getLocation().getBlockX());
            agarPlayer.z.put(event.getPlayer(), event.getPlayer().getLocation().getBlockZ());
        }
        int[] pos = new int[]{event.getPlayer().getLocation().getBlockX(), 4, event.getPlayer().getLocation().getBlockZ()};*/
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String move;
        plugin.getLogger().info(event.getDeathMessage() + "!!!!!!!!!!!!!!!");
        String strKiller = event.getDeathMessage().substring(event.getDeathMessage().indexOf("by")).substring(3).substring(0, event.getDeathMessage().substring(event.getDeathMessage().indexOf("by")).substring(3).indexOf(" using"));
        Player killer = Bukkit.getServer().getPlayer(strKiller);
        Player target = event.getEntity();
        Player source = target.getKiller();
        if (killer.getName() == target.getName()) {
            move = command.get("http://localhost/test.php?move=" + killer.getUniqueId().toString() + "&to=bitcoinrpc&amount=0.00000005");
            if (move.startsWith("1")) {
                target.sendMessage("- 0.00000005 command for killing yourself.");
            }
        } else {
            move = command.get("http://localhost/test.php?move=" + target.getUniqueId().toString() + "&to=" + killer.getUniqueId().toString() + "&amount=0.00000005");
            if (move.startsWith("1")) {
                target.sendMessage("- 0.00000001 command");
            }
            if ((move = command.get("http://localhost/test.php?move=bitcoinrpc&to=" + killer.getUniqueId().toString() + "&amount=0.00000004")).startsWith("1")) {
                killer.sendMessage("+ 0.00000002 command");
            }
        }
        source.getLastDamageCause();
        plugin.getLogger().info(target.getName() + event.getDeathMessage() + (Object)source.getLastDamageCause());
        target.getName();
        event.getDeathMessage();
        for (Player online2 : Bukkit.getOnlinePlayers()) {
            String command2 = command.get("http://localhost/test.php?getbalance=" + online2.getUniqueId().toString());
            Score score = objective.getScore(online2.getName() + ": ");
            String s = Float.toString(Float.valueOf(command2).floatValue() * 1.0E8f).substring(0, Float.toString(Float.valueOf(command2).floatValue() * 1.0E8f).indexOf(46));
            int i = Integer.valueOf(s);
            score.setScore(i);
        }
        for (Player online2 : Bukkit.getOnlinePlayers()) {
            online2.setScoreboard(board);
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        if (split.length == 0) {
        	//food.clean(player);
        	schedule.renderOthers(plugin);
        	food.maintain(plugin);
            Block b1 = plugin.getServer().getWorld("world").getBlockAt(-314, 4, 546);
            Block from = plugin.getServer().getWorld("world").getBlockAt(-135, 4, 546);
            Block to = plugin.getServer().getWorld("world").getBlockAt(-133, 4, 546);
            to = from;
            from.setTypeId(0);
            player.sendMessage("done. " + b1.getTypeId());
            b1.setTypeId(3);
            int[] pos = new int[]{player.getLocation().getBlockX()-4, 4, player.getLocation().getBlockZ()-4};
            agarPlayer.draw(player, pos);
            return true;
        }
        if (split.length > 0) {
            player.sendMessage((Object)ChatColor.GRAY + "Withdrawing " + (Object)ChatColor.WHITE + split[0] + (Object)ChatColor.GRAY + " command to " + (Object)ChatColor.GOLD + split[1]);
            return true;
        }
        return false;
    }

    public static String get(String url) {
        String rtn = null;
        try {
            String inputLine;
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
            while ((inputLine = in.readLine()) != null) {
                rtn = inputLine;
            }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static void json(String str) {
    }

    public static String callURL(String myURL) {
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            BufferedReader bufferedReader;
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60000);
            }
            if (urlConn != null && urlConn.getInputStream() != null && (bufferedReader = new BufferedReader(in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset()))) != null) {
                int cp;
                while ((cp = bufferedReader.read()) != -1) {
                    sb.append((char)cp);
                }
                bufferedReader.close();
            }
            in.close();
        }
        catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        return sb.toString();
    }

    private void playSleepAnimation(Player player) {
        player.sendBlockChange(player.getLocation(), 2, (byte) 0);
    }

    static {
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        objective = board.registerNewObjective((Object)ChatColor.RED + "command", "dummy");
    }
}