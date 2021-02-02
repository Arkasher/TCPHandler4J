package com.yan.tcphandler4j.api.events;

public class PlayerJoinEvent extends Event {

    private String username;

    public PlayerJoinEvent(String username) {
        this.username = username;

    }

    public void print() {
        System.out.println(username + " joined");
    }

}
