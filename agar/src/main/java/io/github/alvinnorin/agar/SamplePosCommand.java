package io.github.alvinnorin.agar;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SamplePosCommand
implements CommandExecutor,
Listener {
    static boolean show_balance = false;
    static float btc = 0.0f;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        if (split.length == 0) {
            player.sendMessage("Current BTC: " + btc + "");
            btc += 1.0f;
            return true;
        }
        if (split.length == 3) {
            try {
                double x = Double.parseDouble(split[0]);
                double y = Double.parseDouble(split[1]);
                double z = Double.parseDouble(split[2]);
                player.teleport(new Location(player.getWorld(), x, y, z));
            }
            catch (NumberFormatException ex) {
                player.sendMessage("Given location is invalid");
            }
            return true;
        }
        return false;
    }
}
