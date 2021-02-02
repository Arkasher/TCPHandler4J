package com.yan.tcphandler4j.server;

public class ServerProperties {

    private String ip;
    private int port;
    private int maxPlayers;
    private int maxConnectionsPerClient;
    private String serverName;

    public ServerProperties(String ip, int port, int maxPlayers, String serverName, int maxConnectionsPerClient) {
        this.ip = ip;
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.serverName = serverName;
        this.maxConnectionsPerClient = maxConnectionsPerClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;


    }

    @Override
    public String toString() {
        return "ServerProperties{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", maxPlayers=" + maxPlayers +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}