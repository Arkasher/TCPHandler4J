package com.yan.tcphandler4j.server.socket;

import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.utils.TokenUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServerSocket extends Thread {

    private final String ip;
    private final int port;

    public boolean running = false;

    private List<SocketClient> clientList = new ArrayList<>();

    public TCPServerSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public List<SocketClient> getClients() {
        return clientList;
    }

    public SocketClient getClient(byte[] token) {
        for(SocketClient client : getClients()) {
            if (!TokenUtils.compare(token, client.getToken())) {
                continue;
            }
            return client;
        }
        return null;
    }

    private long countConnections(InetAddress inetAddress) {
        return clientList.stream().filter((e) -> Arrays.compare(inetAddress.getAddress(),
                e.getSocket().getInetAddress().getAddress()) == 0).count();
    }

    @Override
    public void run() {
        super.run();
        Logger logger = Instance.get("logger", Logger.class);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            running = true;
            while (running) {
                Socket socket = serverSocket.accept();
                SocketClient client = new SocketClient(socket);
                clientList.add(client);
                client.start();
            }
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Packet loop error: " + throwable.getLocalizedMessage() + " | Restarting loop");
            throwable.printStackTrace();
        }
    }
}
