package com.mygdx.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    ServerSocket echoServer = null,echoServer2 = null;
    Socket clientSocket = null,clientSocket2 = null;
    int port;
    public Server1( int port ) {
        this.port = port;
    }
    public void stopServer() {
        System.out.println( "Server cleaning up." );
        System.exit(0);
    }
    public void startServer() {
        // Try to open a server socket on the given port
        // Note that we can't choose a port less than 1024 if we are not
        // privileged users (root)

        try {
            echoServer = new ServerSocket(port);
            echoServer2 = new ServerSocket(port);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println( "Waiting for connections. Only one connection is allowed." );
        while ( true ) {
            try {
                clientSocket = echoServer.accept();
                //clientSocket2 = echoServer2.accept();
                Server1Connection oneconnection = new Server1Connection(clientSocket, this);
                //Server1Connection twoconnection = new Server1Connection(clientSocket2,this);
                oneconnection.run();
                //twoconnection.run();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

