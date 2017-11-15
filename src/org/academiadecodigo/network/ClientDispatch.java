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

    private String clientInput;



    public ClientDispatch(Socket clientSocket, Server server) {

        try {
            this.clientSocket = clientSocket;
            this.server = server;
            terminalGFX = new TerminalGFX(new GameStatus());


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


                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                terminalGFX.render();

                //server.sendAll(terminalGFX.p2Screen());

                //BLOCK
                String clientInput = in.readLine();
                //BLOCK
                clientInput = in.readLine();

                //avoids infinite null
                if (clientInput == null) {
                    clientSocket.close();
                    return;
                }

                System.out.println(clientInput + " received from client");

                closeDispatcher();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeDispatcher() {

        String endMessage = "/q";

        if (clientInput != null) {
            if (clientInput.equals(endMessage)) {
                try {
                    clientSocket.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Socket getClientSocket() {
        return clientSocket;
    }
}
