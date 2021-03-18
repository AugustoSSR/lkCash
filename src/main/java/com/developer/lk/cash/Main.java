package com.developer.lk.cash;

import com.developer.lk.api.server.ServerAPI;
import com.developer.lk.cash.commands.CommandCash;
import com.developer.lk.cash.listeners.Listeners;
import com.developer.lk.cash.mysql.Conexao;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ServerAPI.getConsole().sendMessage("");
        ServerAPI.getConsole().sendMessage("    §a§l[lkCash] §f Plugin sendo inicializado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkCash] §f Versão " + getServer().getBukkitVersion());
        ServerAPI.getConsole().sendMessage("");

        getServer().getPluginManager().registerEvents(new Listeners(), this);

        getCommand("cash").setExecutor(new CommandCash());
        Conexao.criarTabela();


    }

    @Override
    public void onDisable() {
        ServerAPI.getConsole().sendMessage("    §a§l[lkCash] §f Plugin sendo finalizado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkCash] §f Versão " + getServer().getBukkitVersion());
    }
}
