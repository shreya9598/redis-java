package com.opredis;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received from client: " + line);
                writer.write("Echo: " + line + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}
