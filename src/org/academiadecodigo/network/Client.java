package org.academiadecodigo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by codecadet on 09/11/2017.
 */
public class Client {

    //ENTRY POINT
    public static void main(String[] args) {

        int PORT = 9999;
        String hostname = "127.0.0.1";

        try {

            Client client = new Client(hostname, PORT);
            client.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //props
    private Socket clientSocket;

    //construct
    public Client(String hostname, int PORT) throws IOException {

        clientSocket = new Socket(hostname, PORT);

    }


    //code to run
    private void start() throws IOException {

        System.out.println("client running");


        while (true) {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //BLOCKED
            String messageToSend = getInput();
            //BLOCKED

            out.println(messageToSend);

            //BLOCKED
            String gettingFromServer = in.readLine();
            //BLOCKED

            System.out.println(gettingFromServer);

        }
    }

    private String getInput() {

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }


}
