package io.github.alvinnorin.agar;

import io.github.alvinnorin.agar.agar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SampleDebugCommand
implements CommandExecutor {
    private final agar plugin;

    public SampleDebugCommand(agar plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player) {
            Player player = null;
            this.plugin.setDebugging(player, !this.plugin.isDebugging(player = (Player)sender));
            return true;
        }
        return false;
    }
}