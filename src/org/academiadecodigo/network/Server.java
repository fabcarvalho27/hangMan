package org.academiadecodigo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int PORT = 9999;
    private ServerSocket serverSocket;

    private Map<Socket, String> clientsMap;
    //private Map<String, String> registeredUsers;


    public Server() throws IOException {

        this.serverSocket = new ServerSocket(PORT);
        clientsMap = new ConcurrentHashMap<>();
        //registeredUsers = new ConcurrentHashMap<>();

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


            clientsMap.put(clientSocket, "client[" + i + "]");

            System.out.println("\nClient accepted\n"+"Socket: " +clientSocket+"\nClient: "+clientsMap.get(clientSocket));
            System.out.println("");

            cachedClientThreadPool.submit(new ClientDispatch(clientSocket, this));

        }


    }


    public void acceptClient() {


    }

    public void removeClient() {


    }
}
