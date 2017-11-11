package org.academiadecodigo.network;

import org.academiadecodigo.game.GameStatus;
import org.academiadecodigo.terminalGFX.TerminalGFX;
import org.academiadecodigo.terminalGFX.TextReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDispatch implements Runnable {

    private Socket clientSocket;
    private Server server;
    private TerminalGFX terminalGFX;
    private PrintWriter out;
    private BufferedReader in;



    public ClientDispatch(Socket clientSocket, Server server) {

        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.terminalGFX = new TerminalGFX();


            out = new PrintWriter(clientSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        out.println(message);
    }

    @Override
    public void run() {


        try {

            GameStatus gameStatus = new GameStatus();



            while (!clientSocket.isClosed()) {


                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                terminalGFX.render(gameStatus);

                server.sendAll(terminalGFX.p2Screen());

                //BLOCK
                String clientInput = in.readLine();
                //BLOCK


                //System.out.println(clientInput);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
