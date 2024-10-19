import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {

    public void run() throws SocketException, IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true){
            try{
                System.out.println("Server is Listening on port : "+port);
                Socket acceptedCon = socket.accept();
                System.out.println("Connection accepted from client : "+acceptedCon.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedCon.getOutputStream());  //to client means sending to client
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedCon.getInputStream()));
                toClient.println("Hello from the Server");
                toClient.close();
                fromClient.close();
                acceptedCon.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
