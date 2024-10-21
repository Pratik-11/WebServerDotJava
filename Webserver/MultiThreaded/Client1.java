// package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

// this client will send multiple requests

public class Client1 {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try {
                    InetAddress adress = InetAddress.getByName("localhost");
                    Socket socket = new Socket(adress, port);
                    try (PrintWriter toServer = new PrintWriter(socket.getOutputStream());
                            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));) 
                            {
                                toServer.println("Hello from client");
                                String line = fromServer.readLine();
                                System.out.println("Response from the server : " + line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    public static void main(String[] args) {
        Client1 client = new Client1();
        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception ex) {

            }
        }
    }
}
