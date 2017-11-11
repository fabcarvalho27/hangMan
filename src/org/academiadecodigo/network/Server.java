package org.academiadecodigo.network;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private final int PORT = 9999;
    private ServerSocket serverSocket;
    private Map<ClientDispatch, String> clientsMap;
    private Socket client1Socket;
    private Socket client2Socket;

    public Server() throws IOException {

        this.serverSocket = new ServerSocket(PORT);
        clientsMap = new ConcurrentHashMap<>();

    }


    public void start() {

        while (true) {

            try {

                //BLOCKING
                client1Socket = serverSocket.accept();
                //BLOCKING
                client2Socket = serverSocket.accept();

                sendMessage("Hello");

                Game game = new Game(new Player(client1Socket), new Player(client2Socket), "english", 3);
                game.init();
                game.start();

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
    }

    public void sendMessage(String message) {

        try {
            PrintWriter out = new PrintWriter(client1Socket.getOutputStream(), true);
            out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
