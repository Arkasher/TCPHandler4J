package com.yan.tcphandler4j.server;

import com.yan.tcphandler4j.entities.Player;
import com.yan.tcphandler4j.handlers.Instance;

import java.util.List;

public class Emerald {

    public static String emeraldUUID = "emerald-05";

    public static void broadcastMessage(String message) {
        Emerald.getPlayers().forEach((player) -> player.sendMessage(message));
    }

    public static void kickPlayer(Player player) {
        player.disconnect("");
    }

    public static void kickPlayer(Player player, String message) {
        player.disconnect(message);
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
