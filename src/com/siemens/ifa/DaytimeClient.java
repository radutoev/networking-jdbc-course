package com.siemens.ifa;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DaytimeClient {
    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 13)) {
            final InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            StringBuffer buf = new StringBuffer();
            for(int c = reader.read(); c != -1; c = reader.read()) {
                buf.append((char) c);
            }
            System.out.println(buf.toString());
        } catch (Exception e) { }
    }
}
