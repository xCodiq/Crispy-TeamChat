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

package com.crispyseries.crispyteamchat.commands;

import com.crispyseries.crispyteamchat.TeamChat;
import com.crispyseries.crispyteamchat.chat.ChatLoader;
import com.crispyseries.crispyteamchat.utilities.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamChatCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TeamChat teamChat = TeamChat.getInstance();
        PluginDescriptionFile desc = teamChat.getDescription();

        if (args.length == 0) {
            sender.sendMessage(ChatUtils.format("&eThis server is running &c" + desc.getFullName()));
        } else if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    if (sender.hasPermission("crispyteamchat.reload")) {
                        long start = System.currentTimeMillis();
                        if (teamChat.getConfig() == null) teamChat.saveDefaultConfig();
                        teamChat.reloadConfig();
                        teamChat.saveConfig();
                        new ChatLoader(teamChat);
                        sender.sendMessage(ChatUtils.format("&eSuccessfully reloaded &cconfiguration files&e (took " +
                                (System.currentTimeMillis() - start)
                                + "ms)"));
                    }
                    break;
                case "about":
                default:
                    sender.sendMessage(ChatUtils.format("&eCurrently running &c" + desc.getName()));
                    sender.sendMessage(ChatUtils.format("  &e• Version: " + desc.getVersion()));
                    sender.sendMessage(ChatUtils.format("  &e• Authors: " + String.join(", ", desc.getAuthors())));
                    break;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].startsWith("r")) return Collections.singletonList("reload");
        else if (args[0].startsWith("a")) return Collections.singletonList("about");
        else return Arrays.asList("reload", "about");
    }
}
