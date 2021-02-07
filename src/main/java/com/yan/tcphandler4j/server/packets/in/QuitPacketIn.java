package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.api.events.PlayerQuitEvent;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.QuitPacketOut;

import java.util.HashMap;

public class QuitPacketIn extends Packet {

    public QuitPacketIn(byte[] data) {
        super(PacketHandler.PACKET_QUIT_IN, PacketType.PACKET_IN, data);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public HashMap<String, Object> decode() {
        HashMap<String, Object> decodedPacket = new HashMap<>();

        return decodedPacket;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {
        Packet packet = new QuitPacketOut((String)decodedPacket.get("username"));
        Instance.get("eventBus", EventBus.class).callEvent(new PlayerQuitEvent());
        PacketHandler.broadcast(packet);
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
