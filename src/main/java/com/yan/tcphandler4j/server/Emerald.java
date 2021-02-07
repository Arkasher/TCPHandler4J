package com.yan.tcphandler4j.server;

import com.yan.tcphandler4j.entities.Player;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.out.ChatPacketOut;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Emerald {

    public static String emeraldUUID = "emerald-05";

    public static void broadcastMessage(String message) {
        PacketHandler.broadcast(new ChatPacketOut(emeraldUUID, message.getBytes(StandardCharsets.UTF_8)));
    }

    public static void kickPlayer(Player player) {

    }

    public static Player getPlayer(String uuid) {
        Server server = Instance.get("server", Server.class);

        return server.getPlayer(uuid);
    }

    public static List<Player> getPlayers() {
        Server server = Instance.get("server", Server.class);

        return server.getPlayers();
    }

}
