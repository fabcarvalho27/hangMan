package org.academiadecodigo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientDispatch implements Runnable {

    private Socket clientSocket;
    private Server server;


    public ClientDispatch(Socket clientSocket, Server server) {

        this.clientSocket = clientSocket;
        this.server = server;

    }


    @Override
    public void run() {

        try {


            while (!clientSocket.isClosed()) {


                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String clientInput = in.readLine();


                System.out.println(clientInput);

            }








        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
