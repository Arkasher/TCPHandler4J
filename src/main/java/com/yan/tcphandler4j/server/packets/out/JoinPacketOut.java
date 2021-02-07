package com.yan.tcphandler4j.server.packets.out;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.in.JoinPacketIn;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class JoinPacketOut extends Packet {

    private String username;
    private String uuid;

    public JoinPacketOut(String username, String uuid) {
        super(PacketHandler.PACKET_JOIN_OUT, PacketType.PACKET_OUT, null);
        this.username = username;
        this.uuid = uuid;
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[27];

        ByteBuffer buf = ByteBuffer.wrap(data);

        buf.put((byte) PacketHandler.PACKET_JOIN_OUT);
        buf.put(uuid.getBytes(StandardCharsets.UTF_8));
        buf.put(username.getBytes(StandardCharsets.UTF_8));

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
        return new JoinPacketIn(data);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }
}
