package com.yan.tcphandler4j.server;

import com.yan.tcphandler4j.api.events.executors.PlayerJoinEventExecutor;
import com.yan.tcphandler4j.api.events.executors.PlayerQuitEventExecutor;
import com.yan.tcphandler4j.entities.Player;
import com.yan.tcphandler4j.handlers.EventBus;
import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.socket.TCPServerSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Tickable {

    private final ServerProperties serverProperties;
    private final TCPServerSocket tcpServerSocket;

    private boolean started = false;

    private final int TPS = 20;
    private final int TICK_TIME = 1000000000 / TPS;

    public static int PACKET_SIZE = 128;

    private List<Player> playerList;

    public Server(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
        this.tcpServerSocket = new TCPServerSocket(serverProperties.getIp(), serverProperties.getPort());
        this.playerList = new ArrayList<>();
    }

    public void init() {
        Logger logger = Instance.get("logger", Logger.class);
        if (started) {
            logger.warning("Server already started");
            return;
        }
        final long startingAt = System.currentTimeMillis();
        logger.info("Server starting...");
        PacketHandler.registerPackets();
        startServer();
        loadEvents();
        final long startedAt = System.currentTimeMillis();
        final long startTime = startedAt - startingAt;
        logger.info("Server started! Took: " + startTime + "ms");
        loop();
    }

    private void startServer() {
        Logger logger = Instance.get("logger", Logger.class);
        logger.info(serverProperties.toString());

        tcpServerSocket.start();
        started = true;
    }

    private void loop() {
        Logger logger = Instance.get("logger", Logger.class);
        try {
            long lastTick = System.nanoTime(), catchupTime = 0, curTime, wait;
            while (started) {
                curTime = System.nanoTime();
                wait = TICK_TIME - (curTime - lastTick) - catchupTime;
                if (wait > 0) {
                    Thread.sleep(wait / 1000000);
                    catchupTime = 0;
                    continue;
                } else {
                    catchupTime = Math.min(1000000000, Math.abs(wait));
                }

                lastTick = curTime;

                tick();
            }
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Tick loop error: " + throwable.getLocalizedMessage() + " | Restarting loop.");
            throwable.printStackTrace();
        }
    }

    public Player getPlayer(String uuid) {
        for (Player player : playerList) {
            if (player.getSocketClient().getUuid().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void removePlayer(String uuid) {
        Player player = getPlayer(uuid);
        playerList.remove(player);
    }

    public void removePlayer(Player player) {
        playerList.remove(player);
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public void stop() {
        started = false;
        tcpServerSocket.running = false;
    }

    public TCPServerSocket getTcpServerSocket() {
        return tcpServerSocket;
    }

    private void loadEvents() {
        EventBus eventBus = new EventBus();

        eventBus.registerEvents(new PlayerQuitEventExecutor());
        eventBus.registerEvents(new PlayerJoinEventExecutor());

        Instance.put("eventBus", eventBus);
    }

    @Override
    public void tick() {

    }
}
