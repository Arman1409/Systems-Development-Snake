package main;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SnakeServer {

    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet();

    public SnakeServer() {
        System.out.println("Snake server is running...");
        ExecutorService pool = Executors.newFixedThreadPool(20);
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                pool.execute(new ClientHandler(listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    for (PrintWriter writer : clientWriters) {
                        writer.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientWriters.remove(out);
            }
        }
    }
}
