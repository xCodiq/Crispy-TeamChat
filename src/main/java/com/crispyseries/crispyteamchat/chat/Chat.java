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

import java.util.List;

public class Chat {

    private final String id;
    private final String permission;
    private final String format, console_format;

    private final List<String> commands;

    /**
     * Instantiates a new Chat.
     *
     * @param id             the id
     * @param permission     the permission
     * @param format         the format
     * @param console_format the console format
     * @param commands       the commands
     */
    public Chat(String id, String permission, String format, String console_format, List<String> commands) {
        this.id = id;
        this.permission = permission;
        this.format = format;
        this.console_format = console_format;
        this.commands = commands;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Gets format.
     *
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Gets console format.
     *
     * @return the console format
     */
    public String getConsoleFormat() {
        return console_format;
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public List<String> getCommands() {
        return commands;
    }
}
