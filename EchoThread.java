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

    private float p_x;
    private float p_y;
    private int p_d;
    private int projectile;
    private float s_x;
    private float s_y;
    private int special;

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
                System.out.println(line);
                String[] values = line.split(" ");
                for (int i = 0; i < values.length; i++){
                    switch(i){
                        case(0): p_x = Float.parseFloat(values[i]); break;
                        case(1): p_y = Float.parseFloat(values[i]); break;
                        case(2): p_d = Integer.parseInt(values[i]); break;
                        case(3): projectile = Integer.valueOf(values[i]); break;
                        case(4): s_x = Float.parseFloat(values[i]); break;
                        case(5): s_y = Float.parseFloat(values[i]); break;
                        case(6): special = Integer.parseInt(values[i]); break;
                    }
                }
                System.out.println("Received " + line);
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    System.out.println("Socket closed.");
                    socket.close();
                    sentToClient.close();
                    return;
                } else {
                    out.println("Player1 : " + p_x + " "+ p_y + " "+ p_d + " "+ projectile + " "+ s_x + " "+ s_y + " "+special+"\n");
                    out.flush();
                    out2.println("Player2 : " + p_x + " "+ p_y + " "+ p_d + " "+ projectile + " "+ s_x + " "+ s_y + " " + special + "\n");
                    out2.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
