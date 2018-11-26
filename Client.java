package com.mygdx.game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void go() {
        String hostname = "localhost";
        int port = 1978;
        //int port = 6789;
        Socket clientSocket = null;
        DataOutputStream os = null;
        BufferedReader is = null;
        try {
            clientSocket = new Socket(hostname, port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
        }
        if (clientSocket == null || os == null || is == null) {
            System.err.println("Something is wrong. One variable is null.");
            return;
        }

        try {
            while (true) {
                System.out.print("Enter an integer (0 to stop connection, -1 to stop server): ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String keyboardInput = br.readLine();
                os.writeBytes(keyboardInput + "\n");
                int n = Integer.parseInt(keyboardInput);
                if (n == 0 || n == -1) {
                    break;
                }
                String responseLine = is.readLine();
                System.out.println("Server returns its square as: " + responseLine);
            }
            os.close();
            is.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }}}

