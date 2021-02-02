package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;

import java.util.HashMap;

public class ConnectionStartPacketIn extends Packet {

    public ConnectionStartPacketIn(byte[] data) {
        super(PacketHandler.PACKET_CONNECTION_START_IN, PacketType.PACKET_IN, data);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public HashMap<String, Object> decode() {
        HashMap<String, Object> decodedPacket = new HashMap<>();

        return null;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {

    }

    @Override
    public Packet newInstance(byte[] data) {
        return new ConnectionStartPacketIn(data);
    }

    @Override
    public boolean isTokenNeeded() {
        return false;
    }
}
