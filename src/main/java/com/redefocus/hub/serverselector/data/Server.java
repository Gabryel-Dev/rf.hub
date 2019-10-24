package com.redefocus.hub.serverselector.data;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.redefocus.hub.FocusHub;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
@Getter
public class Server {
    private final String name;
    private final ItemStack icon;
    private final Integer slot;

    public void send(Player player) {
        ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();

        byteArrayDataOutput.writeUTF("connect");
        byteArrayDataOutput.writeUTF(name);

        player.sendPluginMessage(FocusHub.getInstance(), "BungeeCord", byteArrayDataOutput.toByteArray());
    }
}
