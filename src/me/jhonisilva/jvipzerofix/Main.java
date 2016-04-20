package me.jhonisilva.jvipzerofix;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getLogger().info("JVIPZeroFIX habilitado.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        getLogger().info("JVipZeroFIX desabilitado.");
    }

    @EventHandler
    private void onCmd(PlayerCommandPreprocessEvent e) {
        if ((e.getMessage().toLowerCase().startsWith("/vipzero") || e.getMessage().toLowerCase().startsWith("/bukkit:vipzero") || e.getMessage().toLowerCase().startsWith("/usekey") || e.getMessage().toLowerCase().startsWith("/usarkey") || e.getMessage().toLowerCase().startsWith("/vipzero:usarkey") || e.getMessage().toLowerCase().startsWith("/vipzero:usekey"))) {
            if (!valida(e.getMessage().toLowerCase())) {
                e.setCancelled(true);
                Player p = e.getPlayer();
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                    if (p1.isOp()) {
                        p1.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "JVIPZeroFIX" + ChatColor.GRAY + "]" + ChatColor.RED + " O jogador " + p.getName() + " tentou se aproveitar da falha no VipZero.");
                    }
                }
            }
        }
    }

    public boolean valida(String msg) {
        String p = "^\\/[a-zA-Z0-9\\s\\-]*$";
        if (msg.matches(p)) {
            return true;
        }
        return false;
    }
}
