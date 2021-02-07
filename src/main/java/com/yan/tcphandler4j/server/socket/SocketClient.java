package com.yan.tcphandler4j.server.socket;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.server.packets.Packet;
import com.yan.tcphandler4j.utils.ByteUtils;
import com.yan.tcphandler4j.utils.TokenUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class SocketClient extends Thread {

    private Socket socket;
    private long lastPacket = System.currentTimeMillis();
    private byte[] token;

    public SocketClient(Socket socket) {
        this.socket = socket;
    }

    public long getLastPacket() {
        return lastPacket;
    }

    public void setLastPacket(long lastPacket) {
        this.lastPacket = lastPacket;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean sendPacket(Packet packet) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(packet.encode());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            byte[] bytes = new byte[Server.PACKET_SIZE];

            int readCount = 0;

            while ((readCount = inputStream.read(bytes)) != -1) {
                byte[] packetId = Arrays.copyOfRange(bytes, 0, 2);
                if (PacketHandler.isTokenNeededForPacket(packetId[0])) {
                    byte[] token = Arrays.copyOfRange(bytes, 2, 12);
                    if (!TokenUtils.compare(token, this.token)) {
                        continue;
                    }
                    byte[] data = Arrays.copyOfRange(bytes, 12, bytes.length);
                    PacketHandler.handle(packetId[0], data, this);
                    continue;
                }
                byte[] data = Arrays.copyOfRange(bytes, 1, bytes.length);
                PacketHandler.handle(packetId[0], data, this);
            }
            defaultQuitPacket();
        } catch (IOException e) {
            defaultQuitPacket();
        }
    }

    private void defaultQuitPacket() {
        PacketHandler.handle(PacketHandler.PACKET_QUIT_IN, new byte[]{}, this);
    }
}
