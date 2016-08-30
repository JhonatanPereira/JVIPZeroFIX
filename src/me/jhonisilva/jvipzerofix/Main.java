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
        //O bug é no pagseguro, portanto deve ser checado neste comando
        if (e.getMessage().startsWith("/vipzero pagseguro ")) {
			String[] args = e.getMessage().split(" ");
			//só verificar o length do arg que vai o código de transação
			//padrão pagseguro (com hífen) = 36 caracteres
			//se for diferente disso, cancelar o evento
			//eficaz para evitar caracteres especiais após o código
			if (args[2].length() != 36) {
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
}
