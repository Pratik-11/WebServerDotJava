// package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server1 {

   
    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{  //lamda function accepts clientsocket returns nothing
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the Server");
                toClient.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }
    
    public static void main(String[] args) {
        int port = 8010;
        Server1 server = new Server1();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is Listening on port : "+port);
            while(true){
                Socket acceptedSocket = serverSocket.accept();
                // New socket connection formed now add it into a thread
                Thread thread = new Thread(()-> server.getConsumer().accept(acceptedSocket));
                thread.start();

                //using functional interface 
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
