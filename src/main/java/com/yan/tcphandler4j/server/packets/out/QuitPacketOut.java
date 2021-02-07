package com.yan.tcphandler4j.server.packets.out;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class QuitPacketOut extends Packet {

    private String uuid;

    public QuitPacketOut(String uuid) {
        super(PacketHandler.PACKET_QUIT_OUT, PacketType.PACKET_OUT, null);
        this.uuid = uuid;
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[11];

        ByteBuffer buf = ByteBuffer.wrap(data);

        buf.put((byte) PacketHandler.PACKET_QUIT_OUT);
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
        return new QuitPacketOut(null);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }
}
