package com.yan.tcphandler4j.server.packets.in;

import com.yan.tcphandler4j.api.events.PlayerJoinEvent;
import com.yan.tcphandler4j.entities.Player;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.JoinPacketOut;
import com.yan.tcphandler4j.server.socket.SocketClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class JoinPacketIn extends Packet {

    public JoinPacketIn(SocketClient client, byte[] data) {
        super(PacketHandler.PACKET_JOIN_IN, PacketType.PACKET_IN, data, client);
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
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        String username = (String)decodedPacket.get("username");
        Player player = new Player(username, client);
        client.setUuid(uuid);
        Packet packet = new JoinPacketOut(username, uuid);

        Instance.get("server", Server.class).addPlayer(player);

        Instance.get("eventBus", EventBus.class).callEvent(new PlayerJoinEvent(player));
        PacketHandler.broadcast(packet);
    }

    @Override
    public Packet newInstance(SocketClient client, byte[] data) {
        return new JoinPacketIn(client, data);
    }

    @Override
    public boolean isTokenNeeded() {
        return true;
    }

}
