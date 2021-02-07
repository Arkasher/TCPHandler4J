package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.api.events.PlayerQuitEvent;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.QuitPacketOut;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.util.HashMap;

public class QuitPacketIn extends Packet {

    public QuitPacketIn(SocketClient client, byte[] data) {
        super(PacketHandler.PACKET_QUIT_IN, PacketType.PACKET_IN, data, client);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public HashMap<String, Object> decode() {
        HashMap<String, Object> decodedPacket = new HashMap<>();

        decodedPacket.put("uuid", client.getUuid());

        return decodedPacket;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {
        Packet packet = new QuitPacketOut((String) decodedPacket.get("uuid"));
        Instance.get("eventBus", EventBus.class).callEvent(new PlayerQuitEvent());
        PacketHandler.broadcast(packet);
    }

    @Override
    public Packet newInstance(SocketClient client, byte[] data) {
        return new QuitPacketIn(client, data);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }

}
