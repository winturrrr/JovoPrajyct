package com.mygdx.game;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoThread extends Thread {
    protected Socket socket;
    protected Socket sentToClient;

    public EchoThread(Socket clientSocket,Socket sentToClient) {
        this.socket = clientSocket;
        this.sentToClient = sentToClient;
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        PrintStream out = null;
        InputStream inp2 = null;
        BufferedReader brinp2 = null;
        PrintStream out2 = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new PrintStream(socket.getOutputStream());
            inp2 = sentToClient.getInputStream();
            brinp2 = new BufferedReader(new InputStreamReader(inp2));
            out2 = new PrintStream(sentToClient.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;

        while (true) {
            try {
                line = brinp.readLine();
                brinp2 = brinp;
                System.out.println("Received " + line);
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    sentToClient.close();
                    return;
                } else {
                    int n = Integer.parseInt(line);
                    out.println("" + n*n);
                    out.flush();
                    out2.println("" + n*n);
                    out2.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
