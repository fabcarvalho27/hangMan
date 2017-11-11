package org.academiadecodigo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ClientDispatch implements Runnable {

    private Socket clientSocket;
    private Server server;

    private PrintWriter out;
    private BufferedReader in;

    private String clientInput;


    public ClientDispatch(Socket clientSocket, Server server) {

        this.clientSocket = clientSocket;
        this.server = server;

        try {

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


            while (!clientSocket.isClosed()) {


                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                clientInput = in.readLine();

                //avoids infinite null
                if (clientInput == null) {
                    clientSocket.close();
                    return;
                }

                System.out.println("\"" + clientInput + "\"" + " received from client");

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

    //RETURNS FALSE IF CHAR INPUT IS NOT A VALID LETTER
    public boolean isValidChar (char charToBeTested) {

        //converted to String so to test with regex
        String charToString = String.valueOf(charToBeTested);

        // regex \p{L} matches a single code point in the category "letter"
        if (charToString.matches("\\p{L}")) {
            System.out.println("Valid character");
            return true;
        }

        System.out.println("Invalid character");
        return false;
    }


    public Socket getClientSocket() {
        return clientSocket;
    }
}
