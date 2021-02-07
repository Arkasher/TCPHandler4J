package com.yan.tcphandler4j.api.events.executors;

import com.yan.tcphandler4j.annotations.EventHandler;
import com.yan.tcphandler4j.api.events.Listener;
import com.yan.tcphandler4j.api.events.PlayerQuitEvent;

public class PlayerQuitEventExecutor implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

    }

}
