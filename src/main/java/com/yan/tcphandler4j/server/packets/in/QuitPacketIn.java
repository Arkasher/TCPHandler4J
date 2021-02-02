package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;

import java.util.HashMap;

public class QuitPacketIn extends Packet {

    public QuitPacketIn() {
        super(PacketHandler.PACKET_QUIT_IN, PacketType.PACKET_IN, null);
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
        return new JoinPacketIn(data);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }

}
