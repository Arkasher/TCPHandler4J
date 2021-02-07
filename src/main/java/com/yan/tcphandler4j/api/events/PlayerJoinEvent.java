package com.yan.tcphandler4j.api.events;

import com.yan.tcphandler4j.entities.Player;

public class PlayerJoinEvent extends PlayerEvent {

    public PlayerJoinEvent(Player player) {
        super(player);
    }

}
