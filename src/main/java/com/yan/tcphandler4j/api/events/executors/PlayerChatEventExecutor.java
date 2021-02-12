package com.yan.tcphandler4j.api.events.executors;

import com.yan.tcphandler4j.annotations.EventHandler;
import com.yan.tcphandler4j.api.events.Listener;
import com.yan.tcphandler4j.api.events.PlayerChatEvent;
import com.yan.tcphandler4j.server.Emerald;

public class PlayerChatEventExecutor implements Listener {

    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event) {
        Emerald.broadcastMessage(event.getPlayer().getUsername() + ": " + event.getMessage());
    }

}
