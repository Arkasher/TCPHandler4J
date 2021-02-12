package com.yan.tcphandler4j.api.events;

import com.yan.tcphandler4j.entities.Player;

public class PlayerChatEvent extends PlayerEvent {

    private String message;

    public PlayerChatEvent(Player player, String message) {
        super(player);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
