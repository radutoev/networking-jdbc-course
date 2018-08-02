package com.siemens.ifa;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {

    private final static int PORT = 13;


    public static void main(String[] args) {
        try(final ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try(Socket connection = serverSocket.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());

                    Date d = new Date();

                    out.write(d.toString() + "\r\n");
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
