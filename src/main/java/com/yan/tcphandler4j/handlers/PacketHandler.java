package com.yan.tcphandler4j.handlers;

import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.server.packets.in.ConnectionStartPacketIn;
import com.yan.tcphandler4j.server.packets.in.JoinPacketIn;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.server.packets.out.ConnectionStartPacketOut;
import com.yan.tcphandler4j.server.packets.out.JoinPacketOut;
import com.yan.tcphandler4j.server.socket.SocketClient;
import com.yan.tcphandler4j.utils.TokenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PacketHandler {

    public static int PACKET_JOIN_IN = 1;
    public static int PACKET_JOIN_OUT = 2;
    public static int PACKET_QUIT_IN = 3;
    public static int PACKET_QUIT_OUT = 4;
    public static int PACKET_CONNECTION_START_IN = 5;
    public static int PACKET_CONNECTION_START_OUT = 6;

    private static final List<Packet> packets = new ArrayList<>();

    public static void registerPackets() {
        Logger logger = Instance.get("logger", Logger.class);
        logger.info("Registering packets...");
        packets.add(new JoinPacketIn(null));
        packets.add(new JoinPacketOut(null, null));
        packets.add(new ConnectionStartPacketOut(null));
        packets.add(new ConnectionStartPacketIn(null));

        logger.info(packets.size() + " packets registred");
    }

    public static void handle(int packetId, byte[] data, SocketClient client) {
        boolean needReturn = handleSpecify(packetId, data, client);
        if (needReturn) {
            return;
        }
        Packet packet = getPacket(packetId);
        if (packet == null) {
            return;
        }
        Packet newPacket = packet.newInstance(data);
        newPacket.execute(newPacket.decode());
    }

    private static boolean handleSpecify(int packetId, byte[] data, SocketClient client) {
        if (packetId == PACKET_CONNECTION_START_IN) {
            byte[] token = TokenUtils.generateToken();
            client.setToken(token);
            client.sendPacket(new ConnectionStartPacketOut(token));
            return true;
        }

        return false;
    }

    public static void broadcast(Packet packet) {
        Server server = Instance.get("server", Server.class);
        for (SocketClient client : server.getTcpServerSocket().getClients()) {
            client.sendPacket(packet);
        }
    }

    public static void broadcast(Packet packet, SocketClient toExclude) {
        Server server = Instance.get("server", Server.class);
        for (SocketClient client : server.getTcpServerSocket().getClients()) {
            if (toExclude.getSocket().getInetAddress().toString().equals(client.getSocket().getInetAddress().toString()))
                continue;
            client.sendPacket(packet);
        }
    }

    public static Packet getPacket(int packetId) {
        for (Packet packet : packets) {
            if (packet.getId() == packetId) {
                return packet;
            }
        }
        return null;
    }

    public static boolean isTokenNeededForPacket(int packetId) {
        for (Packet packet : packets) {
            if (packet.getId() == packetId) {
                return packet.isTokenNeeded();
            }
        }
        return true;
    }

}
