package org.academiadecodigo.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private final int PORT_NUMBER = 9999;

    private ServerSocket serverSocket;
    private ConcurrentHashMap<Socket, String> clientsMap = new ConcurrentHashMap<>();


    public void start() {

        while (true) {

        }
    }

    public void acceptClient(){



    }

    public void removeClient(){



    }





}
