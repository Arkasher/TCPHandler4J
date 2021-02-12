package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.api.events.PlayerChatEvent;
import com.yan.tcphandler4j.api.events.PlayerJoinEvent;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.Emerald;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.ChatPacketOut;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.util.HashMap;

public class ChatPacketIn extends Packet {

    public ChatPacketIn(SocketClient client, byte[] data) {
        super(PacketHandler.PACKET_CHAT_IN, PacketType.PACKET_IN, data, client);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public HashMap<String, Object> decode() {
        HashMap<String, Object> decodedPacket = new HashMap<>();

        decodedPacket.put("uuid", client.getUuid());
        decodedPacket.put("message", data);

        return decodedPacket;
    }

    @Override
    public void execute(HashMap<String, Object> decodedPacket) {
        Instance.get("eventBus", EventBus.class).callEvent(new PlayerChatEvent(Emerald.getPlayer((String) decodedPacket.get("uuid")), new String((byte[]) decodedPacket.get("message"))));
    }

    @Override
    public Packet newInstance(SocketClient client, byte[] data) {
        return new ChatPacketIn(client, data);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }

}
