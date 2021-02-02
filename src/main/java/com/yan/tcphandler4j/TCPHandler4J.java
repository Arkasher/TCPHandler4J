package com.yan.tcphandler4j;

import com.yan.tcphandler4j.handlers.Instance;
import com.yan.tcphandler4j.handlers.OptionsHandler;
import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.server.ServerProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TCPHandler4J {

    public static void main(String[] args) {
       ServerProperties serverProperties = OptionsHandler.handleOptions(args);

       initLogger();
       init(serverProperties);
    }

    private static void init(ServerProperties serverProperties) {
        Server server = Instance.put("server", new Server(serverProperties));
        server.init();
    }

    private static void initLogger() {
        Logger logger = Logger.getLogger("serverLogger");

        try {
            File logDirectory = new File("./logs");
            logDirectory.mkdir();

            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

            FileHandler fh = new FileHandler("./logs/server_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        Instance.put("logger", logger);
    }

}
