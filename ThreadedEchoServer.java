package com.mygdx.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

    static final int PORT = 1978;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Socket player1 = null, player2 = null;


        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }

        try {
            socket = serverSocket.accept();
            player1 = socket;
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }

        try {
            socket = serverSocket.accept();
            player2 = socket;
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        // new thread for a client
        new EchoThread(player1,player2).start();
        new EchoThread(player2,player1).start();
    }
}
