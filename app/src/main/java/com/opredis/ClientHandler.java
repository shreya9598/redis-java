package com.opredis;

import java.io.*;
import java.net.Socket;
import com.opredis.handler.RespHandler;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream()
        ) {
            while (true) {
                StringBuilder rawCommand = new StringBuilder();
                int c = input.read();

                if (c == -1) break;

                rawCommand.append((char) c);

                // First byte should be '*'
                if (c == '*') {
                    String line = readLine(input);
                    rawCommand.append(line).append("\r\n");

                    int argsCount = Integer.parseInt(line);

                    for (int i = 0; i < argsCount; i++) {
                        int typeChar = input.read(); // should be $
                        rawCommand.append((char) typeChar);

                        String lenLine = readLine(input);
                        rawCommand.append(lenLine).append("\r\n");

                        int strLen = Integer.parseInt(lenLine);
                        byte[] strData = input.readNBytes(strLen);
                        rawCommand.append(new String(strData));

                        // read trailing \r\n
                        input.read(); // \r
                        input.read(); // \n
                        rawCommand.append("\r\n");
                    }

                    // Now print the full raw command received
                    System.out.println("Full RESP command:\n" + rawCommand);

                    byte [] response = RespHandler.hanndleResp(rawCommand.toString());

                    // Respond with OK just to test
                    output.write(response);
                    output.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    private String readLine(InputStream input) throws IOException {
        ByteArrayOutputStream lineBuffer = new ByteArrayOutputStream();
        int b;
        while ((b = input.read()) != -1) {
            if (b == '\r') {
                input.read(); // skip \n
                break;
            }
            lineBuffer.write(b);
        }
        return lineBuffer.toString();
    }
}
