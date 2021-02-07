package com.yan.tcphandler4j.api.events;

import com.yan.tcphandler4j.entities.Player;

public class PlayerEvent extends Event {

    private Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
