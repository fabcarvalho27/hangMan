package org.academiadecodigo.testers;

import org.academiadecodigo.network.Server;

import java.io.IOException;

public class ServerTester {


    public static void main(String[] args) {

        try {
            Server server = new Server();

            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
