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

package com.crispyseries.crispyteamchat;

import com.crispyseries.crispyteamchat.chat.ChatLoader;
import com.crispyseries.crispyteamchat.chat.listener.ChatListener;
import com.crispyseries.crispyteamchat.commands.TeamChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamChat extends JavaPlugin {

    private static TeamChat instance = null;

    /**
     * Gets instance of the {@link TeamChat} class
     *
     * @return the instance of the class
     */
    public static TeamChat getInstance() {
        // Check if instance equals null, if TRUE, throw IllegalStateException
        if (instance == null) throw new IllegalStateException("TeamChat instance cannot be null");
        return instance;
    }

    @Override
    public void onEnable() {
        // Setting the TeamChat instance
        TeamChat.instance = this;

        // Saving the default configuration file
        this.saveDefaultConfig();

        // Register main command
        this.getCommand("crispyteamchat").setExecutor(new TeamChatCommand());

        // Load all the chats
        ChatLoader chatLoader = new ChatLoader(instance);

        // Register the chat listener
        this.getServer().getPluginManager().registerEvents(new ChatListener(), instance);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
