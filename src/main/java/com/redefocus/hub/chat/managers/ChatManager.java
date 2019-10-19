package com.redefocus.hub.chat.managers;

import com.google.common.collect.Maps;
import com.redefocus.hub.util.TimeFormatter;

import java.util.HashMap;
import java.util.UUID;

public class ChatManager {
    private static HashMap<UUID, Long> cooldown = Maps.newHashMap();

    public static String getFormatted(UUID uuid) {
        return TimeFormatter.format(ChatManager.cooldown.get(uuid)-System.currentTimeMillis());
    }

    public static Long setCooldown(UUID uuid, Integer cooldown) {
        return ChatManager.cooldown.put(uuid, (cooldown.longValue()+System.currentTimeMillis()));
    }

    public static Boolean hasCooldown(UUID uuid) {
        return ChatManager.cooldown.containsKey(uuid) && ChatManager.cooldown.get(uuid) > System.currentTimeMillis();
    }
}
