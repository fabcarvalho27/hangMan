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
                sendMessage("Welcome to hangMan!", client1Socket);
                sendMessage("Waiting for second Player...", client1Socket);

                client2Socket = serverSocket.accept();
                sendMessage("Welcome to hangMan!", client2Socket);
                //BLOCKING

                Game game = new Game(new Player(client1Socket, "Player1"), new Player(client2Socket, "Player2"), "english", 3);

                sendMessage("Game START, " + game.getPlayer1().getName(), client1Socket);
                sendMessage("Game START, " + game.getPlayer2().getName(), client2Socket);

                // Start Game

                game.start();

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
}
