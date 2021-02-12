package com.yan.tcphandler4j.entities;

import com.yan.tcphandler4j.server.Emerald;
import com.yan.tcphandler4j.server.packets.out.ChatPacketOut;
import com.yan.tcphandler4j.server.packets.out.PacketKickOut;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Player extends Entity {

    private String username;
    private SocketClient socketClient;

    public Player(String username, SocketClient socketClient) {
        this.username = username;
        this.socketClient = socketClient;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void sendMessage(String message) {
        socketClient.sendPacket(new ChatPacketOut(Emerald.emeraldUUID, message.getBytes(StandardCharsets.UTF_8)));
    }

    //provisório
    public void teleport(byte[] location) {

    }

    public void disconnect(String message) {
        socketClient.sendPacket(new PacketKickOut(socketClient.getUuid()));
        try {
            socketClient.getSocket().close();
        } catch (IOException ignored) {}
    }
}
