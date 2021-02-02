package com.yan.tcphandler4j.server.packets;

import java.util.HashMap;

public abstract class Packet {

    private int id;
    private PacketType packetType;
    public byte[] data;

    public Packet(int id, PacketType packetType, byte[] data) {
        this.id = id;
        this.packetType = packetType;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public abstract byte[] encode();

    public abstract boolean isTokenNeeded();

    public abstract HashMap<String, Object> decode();

    public abstract void execute(HashMap<String, Object> decodedPacket);

    public abstract Packet newInstance(byte[] data);

    public enum PacketType {
        PACKET_IN,
        PACKET_OUT
    }

}
