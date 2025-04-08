package com.opredis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Rserver{
    private static final int PORT = 6379;

    public static void startServer() {
        System.out.println("Starting TCP server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}