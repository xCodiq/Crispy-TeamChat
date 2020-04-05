/*
 *   ~
 *   ~ Copyright 2020 NeverEndingPvP. All rights reserved.
 *   ~
 *   ~ Licensed under the NeverEndingPvP License, Version 1.0 (the "License");
 *   ~ you may not use this file except in compliance with the License.
 *   ~
 *   ~ You are not allowed to edit the source.
 *   ~ You are not allowed to edit this text.
 *   ~ You are not allowed to sell this source.
 *   ~ You are not allowed to distribute this source in any way.
 *   ~ You are not allowed to claim this as yours.
 *   ~ You are not allowed to distribute.
 *   ~ You are not allowed to make own terms.
 *   ~ You are not allowed to place own warranty.
 *   ~ You are not allowed to make any sublicense.
 *   ~
 *   ~ Unless required by applicable law or agreed to in writing, software
 *   ~ distributed under the License is distributed on an "AS IS" BASIS.
 *   ~
 *   ~ Author: xCodiq (Discord: Codiq#3662)
 *   ~
 */

package com.crispyseries.crispyteamchat.chat.listener;

import com.crispyseries.crispyteamchat.TeamChat;
import com.crispyseries.crispyteamchat.chat.Chat;
import com.crispyseries.crispyteamchat.chat.ChatLoader;
import com.crispyseries.crispyteamchat.utilities.ChatUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatListener implements Listener {

    private final FileConfiguration config = TeamChat.getInstance().getConfig();

    @EventHandler(priority = EventPriority.HIGH)
    public void onAsyncPlayerChat(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String[] command = event.getMessage().replaceFirst("/", "").split("\\s+");
        String[] message = Arrays.copyOfRange(command, 1, command.length);

        StringBuilder builder = new StringBuilder();
        for (String string : message) builder.append(string).append(" ");

        // Loop through all the loaded chats
        for (Chat chat : ChatLoader.getChats()) {

            // Check if a chat setting is null, if TRUE, continue the loop
            if (chat.getPermission() == null || chat.getCommands().isEmpty() || chat.getFormat() == null) continue;

            // Check if the command is in the command list
            if (chat.getCommands().contains(command[0])) {
                event.setCancelled(true);

                // Check if the player has the permission
                if (!player.hasPermission(chat.getPermission())) {
                    player.sendMessage(ChatUtils.format(config.getString("no-permissions")));
                    return;
                }

                // Check if the message is empty
                if (builder.toString().isEmpty()) {
                    player.sendMessage(ChatUtils.format(config.getString("supply-message")));
                    return;
                }

                // Get all the players with the chat permission
                List<Player> playersToSend = Bukkit.getOnlinePlayers().stream().filter(p
                        -> p.hasPermission(chat.getPermission())).collect(Collectors.toList());

                String format = "";

                // Loop through all the players with the chat permission
                for (Player player1 : playersToSend) {
                    format = PlaceholderAPI.setPlaceholders(
                            player, chat.getFormat().replace("{MESSAGE}", builder.toString().trim()));

                    player1.sendMessage(ChatUtils.format(format));
                }

                Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(format));
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onServerCommand(ServerCommandEvent event) {
        CommandSender sender = event.getSender();

        String[] command = event.getCommand().replaceFirst("/", "").split("\\s+");
        String[] message = Arrays.copyOfRange(command, 1, command.length);

        StringBuilder builder = new StringBuilder();
        for (String string : message) builder.append(string).append(" ");

        // Loop through all the loaded chats
        for (Chat chat : ChatLoader.getChats()) {

            // Check if the command is in the command list
            if (chat.getCommands().contains(command[0])) {
                event.setCancelled(true);

                // Check if the message is empty
                if (builder.toString().isEmpty()) {
                    sender.sendMessage(ChatUtils.format(config.getString("supply-message")));
                    return;
                }

                // Get all the players with the chat permission
                List<Player> playersToSend = Bukkit.getOnlinePlayers().stream().filter(p
                        -> p.hasPermission(chat.getPermission())).collect(Collectors.toList());

                String format = ChatUtils.format(chat.getConsoleFormat().replace("{MESSAGE}", builder.toString().trim()));

                // Loop through all the players with the chat permission
                for (Player player1 : playersToSend) player1.sendMessage(format);

                Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(format));
                break;
            }
        }
    }
}
