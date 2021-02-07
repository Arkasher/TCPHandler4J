package com.yan.tcphandler4j;

import com.yan.tcphandler4j.handlers.PacketHandler;
import com.yan.tcphandler4j.server.Server;
import com.yan.tcphandler4j.utils.ByteUtils;
import com.yan.tcphandler4j.utils.TokenUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("0.0.0.0", 55000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write((byte)5); //connection packet

        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[Server.PACKET_SIZE];

        int readCount = 0;

        while ((readCount = inputStream.read(bytes)) != -1) {
            bytes = ByteUtils.trim(bytes);
            if(bytes[0] == (byte)6) {
                byte[] username = "YalzinBr".getBytes(StandardCharsets.UTF_8);

                byte[] data = new byte[3000];

                ByteBuffer buf = ByteBuffer.wrap(data);

                buf.put((byte) PacketHandler.PACKET_JOIN_IN);
                buf.put(bytes);
                buf.put(username);


                outputStream.write(buf.array());
            }
        }
    }

}
