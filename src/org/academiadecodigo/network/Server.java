package org.academiadecodigo.network;

import org.academiadecodigo.game.Game;
import org.academiadecodigo.game.Player;

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
    private Socket client1Socket;
    private Socket client2Socket;

    public Server() throws IOException {

        this.serverSocket = new ServerSocket(PORT);
        clientsMap = new ConcurrentHashMap<>();

    }


    public void start() {

        ExecutorService gamesThreadPool = Executors.newCachedThreadPool();


        while (true) {

            try {

                //BLOCKING
                client1Socket = serverSocket.accept();
                sendMessage("\nWelcome to hangMan!\n", client1Socket);

                // String player1Name = readMessage(client1Socket);
                sendMessage("Waiting for second player", client1Socket);


                client2Socket = serverSocket.accept();

                sendMessage("Welcome to hangMan!\n", client2Socket);
                //String player2Name = readMessage(client2Socket);
                //BLOCKING

                Game game = new Game(client1Socket, client2Socket, "intellinames", 3);
                gamesThreadPool.submit(game);
                //Game game = new Game(new Player(client1Socket, player1Name), new Player(client2Socket, player2Name), "intellijunkies", 3);


                //sendMessage("Game START, " + game.getPlayer1().getName(), client1Socket);
                //sendMessage("Game START, " + game.getPlayer2().getName(), client2Socket);

                // Start Game


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void sendMessage(String message, Socket socket) {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage(Socket socket) {

        String message = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void sendAllMessage(String message, Socket client1Socket, Socket client2Socket) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(client1Socket.getOutputStream(), true);
            out.println(message);
            out = new PrintWriter(client2Socket.getOutputStream(), true);
            out.println(message);
            out.close();

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

    public Socket getClient1Socket() {
        return client1Socket;
    }

    public void setClient1Socket(Socket client1Socket) {
        this.client1Socket = client1Socket;
    }

    public Socket getClient2Socket() {
        return client2Socket;
    }

    public void setClient2Socket(Socket client2Socket) {
        this.client2Socket = client2Socket;
    }
}
