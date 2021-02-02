package com.yan.tcphandler4j.api.events;

public class PlayerQuitEvent extends Event {

    public PlayerQuitEvent() {

    }

    public void print() {
        System.out.println("player quit");
    }

}
