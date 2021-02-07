package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.api.events.PlayerJoinEvent;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.JoinPacketOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class JoinPacketIn extends Packet {

    public JoinPacketIn(byte[] data) {
        super(PacketHandler.PACKET_JOIN_IN, PacketType.PACKET_IN, data);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public HashMap<String, Object> decode() {
        HashMap<String, Object> decodedPacket = new HashMap<>();

        String username = new String(Arrays.copyOfRange(data, 0, data.length)).trim();

        decodedPacket.put("username", username);

        return decodedPacket;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {
        Packet packet = new JoinPacketOut((String)decodedPacket.get("username"), UUID.randomUUID().toString().substring(0, 10));
        Instance.get("eventBus", EventBus.class).callEvent(new PlayerJoinEvent((String)decodedPacket.get("username")));
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
