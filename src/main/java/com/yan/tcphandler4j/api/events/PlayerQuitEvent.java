package com.yan.tcphandler4j.api.events;

import com.yan.tcphandler4j.entities.Player;

public class PlayerQuitEvent extends PlayerEvent {

    public PlayerQuitEvent(Player player) {
        super(player);
    }

}
