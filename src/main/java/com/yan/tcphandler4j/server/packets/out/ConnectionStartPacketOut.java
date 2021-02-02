package com.yan.tcphandler4j.server.packets.out;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class ConnectionStartPacketOut extends Packet {

    private byte[] token;

    public ConnectionStartPacketOut(byte[] token) {
        super(PacketHandler.PACKET_CONNECTION_START_OUT, PacketType.PACKET_OUT, token);
        this.token = token;
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[11];

        ByteBuffer buf = ByteBuffer.wrap(data);

        buf.put((byte) PacketHandler.PACKET_CONNECTION_START_OUT);
        buf.put(token);

        return buf.array();
    }

    @Override
    public HashMap<String, Object> decode() {
        return null;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {

    }

    @Override
    public Packet newInstance(byte[] data) {
        return null;
    }

    @Override
    public boolean isTokenNeeded() {
        return false;
    }
}
