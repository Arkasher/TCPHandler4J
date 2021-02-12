package com.yan.tcphandler4j.server.packets.out;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PacketKickOut extends Packet {

    private String uuid;

    public PacketKickOut(String uuid) {
        super(PacketHandler.PACKET_KICK_OUT, PacketType.PACKET_OUT, null);
        this.uuid = uuid;
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[27];

        ByteBuffer buf = ByteBuffer.wrap(data);

        buf.put((byte) PacketHandler.PACKET_KICK_OUT);
        buf.put(uuid.getBytes(StandardCharsets.UTF_8));
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
    public Packet newInstance(SocketClient client, byte[] data) {
        return new PacketKickOut( null);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }
}
