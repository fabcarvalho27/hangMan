package org.academiadecodigo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


        public static void main(String[] args) {

            int portNumber = 12345;

            ServerSocket serverSocket = null;

            Socket clientSocket = null;

            ExecutorService cachedPool = Executors.newCachedThreadPool();



            try {
                serverSocket = new ServerSocket(portNumber);


                while (true) {

                    clientSocket = serverSocket.accept();

                    ServerThreads client = new ServerThreads(clientSocket);

                    cachedPool.submit(client);
                }
            }


            catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                    if (serverSocket != null) {
                        serverSocket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            cachedPool.shutdown();
        }

        static class ServerThreads implements Runnable {

            Socket clientSocket;

            public ServerThreads(Socket clientSocket) {
                this.clientSocket = clientSocket;
            }


            @Override
            public void run() {

                PrintWriter out;

                try {

                    System.out.println("Client connected...");

                    while (true) {

                        out = new PrintWriter(clientSocket.getOutputStream(), true);

                        BufferedReader in = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));

                        String received = in.readLine();

                        //avoid null loop when client leaves
                        if (received == null || received.equals("EXIT")) {

                            clientSocket.close();

                            break;
                        }

                        System.out.println(received);

                        out.println("Message received: " + received);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




