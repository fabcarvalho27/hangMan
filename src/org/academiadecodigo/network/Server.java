package org.academiadecodigo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int PORT = 9999;
    private ServerSocket serverSocket;
    private Map<ClientDispatch, String> clientsMap;

    public Server() throws IOException {

        this.serverSocket = new ServerSocket(PORT);
        clientsMap = new ConcurrentHashMap<>();

    }


    public void start() throws IOException {

        ExecutorService cachedClientThreadPool = Executors.newCachedThreadPool(); //no fixed client limit
        Socket clientSocket;

        int i = 1;


        while (true) {

            System.out.println("waiting for client");

            //BLOCKING
            clientSocket = serverSocket.accept();
            //BLOCKING

            ClientDispatch client = new ClientDispatch(clientSocket, this);

            clientsMap.put(client, "client[" + i + "]");

            System.out.println("\nClient accepted\n" + "Socket: " + clientSocket + "\nClient: " + clientsMap.get(clientSocket));
            System.out.println("");

            cachedClientThreadPool.submit(client);

            //TESTING sendAll
            sendAll("hello player");

            i++;
        }
    }

    //TALK TO EVERY CLIENT
    public void sendAll(String messageForClient) {

        for (ClientDispatch client :
                clientsMap.keySet()) {

            //avoids infinite null
            if (clientsMap.keySet() == null) {
                return;
            }

            client.send(messageForClient);
        }
    }
}
