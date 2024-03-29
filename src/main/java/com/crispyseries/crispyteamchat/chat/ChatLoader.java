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

package com.crispyseries.crispyteamchat.chat;

import com.crispyseries.crispyteamchat.TeamChat;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ChatLoader {

    private static final List<Chat> chats = new ArrayList<>();

    /**
     * Instantiates a new Chat loader.
     *
     * @param teamChat the team chat
     */
    public ChatLoader(TeamChat teamChat) {
        FileConfiguration config = teamChat.getConfig();
        ConfigurationSection chatSection = config.getConfigurationSection("chats");

        chats.clear();
        for (String key : chatSection.getKeys(false)) {
            Chat chat;

            String permission = chatSection.getString(key + ".permission");
            String format = chatSection.getString(key + ".format");
            String console_format = chatSection.getString(key + ".console-format");
            List<String> commands = chatSection.getStringList(key + ".commands");

            chat = new Chat(key, permission, format, console_format, commands);
            chats.add(chat);
        }
        teamChat.getLogger().info("Found and loaded " + chats.size() + " chat channels!");
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public static List<Chat> getChats() {
        return chats;
    }
}
