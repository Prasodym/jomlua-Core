package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            String playerGM = player.getGameMode().toString().toLowerCase();

            if (command.getName().equalsIgnoreCase("gm")) {
                if (player.hasPermission("jomlua.gamemode")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("0")) {
                            player.setGameMode(GameMode.SURVIVAL);

                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §cSurvival§a mode.");
                        } else if (args[0].equalsIgnoreCase("1")) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §cCreativ§a mode.");
                        } else if (args[0].equalsIgnoreCase("2")) {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §cAdventure§a mode.");
                        } else if (args[0].equalsIgnoreCase("3")) {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §cSpectator§a mode.");
                        }
                    } else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cBitte benutze §a /gm <mode> <player>");
                    }
                    if (player.hasPermission("jomlua.gamemode.out")) {
                        if (args.length == 2) {
                            try {
                                Player target = Bukkit.getPlayer(args[1]);
                                if (args[0].equalsIgnoreCase("0")) {
                                    target.setGameMode(GameMode.SURVIVAL);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" +playerGM + "§a mode.");
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                                } else if (args[0].equalsIgnoreCase("1")) {
                                    target.setGameMode(GameMode.CREATIVE);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + playerGM + "§a mode.");
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                                } else if (args[0].equalsIgnoreCase("2")) {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + playerGM + "§a mode.");
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                                } else if (args[0].equalsIgnoreCase("3")) {
                                    target.setGameMode(GameMode.SPECTATOR);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + playerGM + "§a mode.");
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "§7Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                                }

                            } catch (NullPointerException d) {
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDer Spieler wurde nicht gefunden oder existiert nicht.");
                            }
                        }
                    } else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                    }
                } else {
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                }
            }
        }else{
            if (args.length == 2) {
                try {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("0")) {

                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + target.getGameMode() + "§a mode.");
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                    } else if (args[0].equalsIgnoreCase("1")) {
                        target.setGameMode(GameMode.CREATIVE);
                        target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + target.getGameMode() + "§a mode.");
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + target.getGameMode() + "§a mode.");
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im §c" + target.getGameMode() + "§a mode.");
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "Der Spieler §c" + target.getName() + "§7 ist nun im §c" + target.getGameMode() + " mode.");
                    }

                } catch (NullPointerException d) {
                    sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER_ONLINE.getText());
                }
            }else{
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§cInfo: §e/gm <Gamemode> <player>");
            }
        }
        return false;
    }
}
