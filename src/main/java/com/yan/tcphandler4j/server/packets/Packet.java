package com.yan.tcphandler4j.server.packets;

import com.yan.tcphandler4j.server.socket.SocketClient;

import java.util.HashMap;

public abstract class Packet {

    private int id;
    private PacketType packetType;
    public byte[] data;
    public SocketClient client;

    public Packet(int id, PacketType packetType, byte[] data) {
        this.id = id;
        this.packetType = packetType;
        this.data = data;
    }

    public Packet(int id, PacketType packetType, byte[] data, SocketClient client) {
        this.id = id;
        this.packetType = packetType;
        this.data = data;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public abstract byte[] encode();

    public abstract boolean isTokenNeeded();

    public abstract HashMap<String, Object> decode();

    public abstract void execute(HashMap<String, Object> decodedPacket);

    public abstract Packet newInstance(SocketClient client, byte[] data);

    public enum PacketType {
        PACKET_IN,
        PACKET_OUT
    }

}
