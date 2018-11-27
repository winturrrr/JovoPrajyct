package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class Server1Connection {
    BufferedReader is;
    PrintStream os;
    Socket clientSocket,clientSocket2;
    Server1 server,server2;
    public Server1Connection(Socket clientSocket, Server1 server) {
        this.clientSocket = clientSocket;
        this.server = server;
        System.out.println("Connection established with: " + clientSocket);
        try {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void run() {
        String line;
        try {
            boolean serverStop = false;
            while (true) {
                line = is.readLine();
                System.out.println("Received " + line);
                int n = Integer.parseInt(line);
                if (n == -1) {
                    serverStop = true;
                    break;
                }
                if (n == 0) {
                    break;
                }
                os.println("" + n);
            }
            System.out.println("Connection closed.");
            is.close();
            os.close();
            clientSocket.close();
            if (serverStop) {
                server.stopServer();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

