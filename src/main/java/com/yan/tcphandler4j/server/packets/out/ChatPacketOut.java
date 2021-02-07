package com.yan.tcphandler4j.server.packets.out;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ChatPacketOut extends Packet {

    private final String uuid;
    private final byte[] message;

    public ChatPacketOut(String uuid, byte[] message) {
        super(PacketHandler.PACKET_CHAT_OUT, PacketType.PACKET_OUT, null);
        this.message = message;
        this.uuid = uuid;
    }

    @Override
    public byte[] encode() {
        byte[] data = new byte[Server.PACKET_SIZE];

        ByteBuffer buf = ByteBuffer.wrap(data);

        buf.put((byte) PacketHandler.PACKET_CHAT_OUT);
        buf.put(uuid.getBytes(StandardCharsets.UTF_8));
        buf.put(message);

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
        return new ChatPacketOut(null, null);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }
}
