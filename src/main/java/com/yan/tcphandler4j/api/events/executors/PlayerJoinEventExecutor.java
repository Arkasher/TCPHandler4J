package com.yan.tcphandler4j.api.events.executors;

import com.yan.tcphandler4j.annotations.EventHandler;
import com.yan.tcphandler4j.api.events.Listener;
import com.yan.tcphandler4j.api.events.PlayerJoinEvent;

public class PlayerJoinEventExecutor implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.print();
    }

}
