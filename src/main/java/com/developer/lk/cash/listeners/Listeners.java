package com.developer.lk.cash.listeners;

import com.developer.lk.cash.mysql.Metodos;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onCash(PlayerJoinEvent event) {

        Player jogador = event.getPlayer();

        if (!Metodos.hasJogador(jogador)) {
            Metodos.adicionarJogador(jogador);
        }
    }

    /*

    @EventHandler
    public void onTentar(PlayerInteractEvent event) {
        Player jogador = event.getPlayer();

        if (jogador.getWorld().getName().equalsIgnoreCase("Tesouros")) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block.getType() == Material.CHEST) {
                    if (jogador.getNearbyEntities()) {

                    }
                }
            }
        }
    }

     */

}
