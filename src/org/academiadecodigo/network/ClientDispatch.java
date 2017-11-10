package org.academiadecodigo.network;

import org.academiadecodigo.terminalGFX.TerminalGFX;
import org.academiadecodigo.terminalGFX.TextReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientDispatch implements Runnable {

    private Socket clientSocket;
    private Server server;
    private TerminalGFX terminalGFX;

    public ClientDispatch(Socket clientSocket, Server server) throws IOException {

        this.clientSocket = clientSocket;
        this.server = server;
        this.terminalGFX = new TerminalGFX();

    }


    @Override
    public void run() {


        try {

            TextReader textReader = new TextReader() ;




            while (!clientSocket.isClosed()) {


                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //server.broadcast(textReader.returnLogo());
                server.broadcast(terminalGFX.render());


                //BLOCK
                String clientInput = in.readLine();
                //BLOCK


                //System.out.println(clientInput);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
