package com.yan.tcphandler4j.handlers;

import com.yan.tcphandler4j.server.ServerProperties;
import org.apache.commons.cli.*;

public class OptionsHandler {

    public static ServerProperties handleOptions(String[] args) {
        Options options = new Options();

        Option optionPort = new Option("p", "port", true, "port that the server will be run");
        optionPort.setRequired(false);
        options.addOption(optionPort);

        Option optionServerName = new Option("sn", "server-name", true, "the name of server");
        optionServerName.setRequired(false);
        options.addOption(optionServerName);

        Option optionServerIp = new Option("si", "server-ip", true, "the ip of server");
        optionServerIp.setRequired(false);
        options.addOption(optionServerIp);

        Option optionMaxPlayers = new Option("mp", "max-players", true, "the number of max players of this server");
        optionMaxPlayers.setRequired(false);
        options.addOption(optionMaxPlayers);

        Option optionMaxConnectionsPerClient = new Option("mcpc", "max-connections-per-client", true, "the number of max connections per client");
        optionMaxConnectionsPerClient.setRequired(false);
        options.addOption(optionMaxConnectionsPerClient);


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        int port = Integer.parseInt(cmd.getOptionValue("p", "55000"));
        int maxPlayers = Integer.parseInt(cmd.getOptionValue("mp", "20"));
        int maxConnectionsPerClient = Integer.parseInt(cmd.getOptionValue("mcpc", "5"));

        String serverName = cmd.getOptionValue("sn", "TCPHandler4JServer");
        String ip = cmd.getOptionValue("si", "0.0.0.0");

        return new ServerProperties(ip, port, maxPlayers, serverName, maxConnectionsPerClient);
    }

}
