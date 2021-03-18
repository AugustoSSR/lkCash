package com.developer.lk.cash.commands;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.cash.mysql.Metodos;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCash implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;

        if (command.getName().equalsIgnoreCase("cash")) {

            if (args.length == 0) {
                MessegeAPI.send(jogador, "Seu cash: " + Metodos.getCash(jogador));
                return true;
            }

            if (args.length >= 1) {
                if (jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length < 2) {
                            MessegeAPI.send(jogador, "Use: §f/cash set <player> <quantia>");
                            return true;
                        }

                        Player target = Bukkit.getPlayerExact(args[1]);
                        Integer quantia;
                        try {
                            quantia = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            MessegeAPI.send(jogador, "Digite um numero valido.");
                            return true;
                        }
                        Metodos.setCash(jogador, quantia);
                        MessegeAPI.send(jogador, "Você setou o cash do jogador §f" + target.getName() + " §apara §f" + quantia);
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("add")) {
                        if (args.length <2) {
                            MessegeAPI.send(jogador, "Use: §f/cash add <player> <quantidade>");
                            return true;
                        }

                        Player target = Bukkit.getPlayerExact(args[1]);
                        Integer quantia;
                        try {
                            quantia = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            MessegeAPI.send(jogador, "Digite um numero valido.");
                            return true;
                        }

                        Metodos.addCash(jogador, quantia);
                        MessegeAPI.send(jogador, "Você adicionou §f" + quantia + " §ade cash na conta do jogador §f" + target.getName());
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("remove")) {
                        if (args.length <2) {
                            MessegeAPI.send(jogador, "Use: §f/cash remove <player> <quantidade>");
                            return true;
                        }

                        Player target = Bukkit.getPlayerExact(args[1]);
                        Integer quantia;
                        try {
                            quantia = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            MessegeAPI.send(jogador, "Digite um numero valido.");
                            return true;
                        }

                        Metodos.removeCash(jogador, quantia);
                        MessegeAPI.send(jogador, "Você removeu §f" + quantia + " §ade cash na conta do jogador §f" + target.getName());
                        return true;

                    }


                    MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    sender.sendMessage("§aSaldo do jogador §f" + target.getName() + ": §7" + Metodos.getCash(target));
                } else {
                    sender.sendMessage("§cEste jogador nao existe!");
                }
            }

            return true;
        }


        return false;
    }
}
