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


    public void start() {

        ExecutorService cachedClientThreadPool = Executors.newCachedThreadPool(); //no fixed client limit
        Socket clientSocket;

        int i = 1;


        while (true) {


            try {
                //TESTING sendOne
                sendOne("client[1]", "Testing sendOne method ");

                System.out.println("waiting for client");

                //BLOCKING
                clientSocket = serverSocket.accept();
                //BLOCKING

                ClientDispatch client = new ClientDispatch(clientSocket, this);

                clientsMap.put(client, "client[" + i + "]");


                System.out.println("\nClient accepted\n" + "Socket: " + clientSocket + "\nClient: " + clientsMap.values());
                System.out.println("");

                cachedClientThreadPool.submit(client);

                //TESTING sendAll
                sendAll("testing sendAll method");

                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void comunicateToClient(Socket clientSocket, String message) throws IOException {

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        //TALK TO EVERY CLIENT
    }
    public void sendAll(String messageForClient) {

        for (ClientDispatch client :
                clientsMap.keySet()) {

            client.send(messageForClient);
        }
    }


/*
    public void broadcast(String message) throws IOException {

        synchronized (clientsMap){

            for (Socket clientSocket : clientsMap.keySet()){

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

                out.println(message);
            }
        }
    */

    //TALK TO A SINGLE CLIENT
    public void sendOne(String clientName, String messageToClient) {
        for (Map.Entry<ClientDispatch, String> client :
                clientsMap.entrySet()) {

            if (clientName.equals(client.getValue())) {
                System.out.println("Message " + messageToClient + " delivered to client");
                client.getKey().send(messageToClient);
            }

        }
    }


}
