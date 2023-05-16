import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Server {
    public ServerSocket serverSocketChannel;

    PrintWriter fromServer;
    BufferedReader  intoServer;

    /*List<Socket> listOfSockets;*/

    void openSocket(int port) throws IOException {
        this.serverSocketChannel= new ServerSocket(port);
        System.out.println("Serwer nasłuchuje na porcie " + port);
    }
    void SetCommunicationParameters(Socket clientSocket) throws IOException {
        if(clientSocket==null){
            return;
        }
        this.fromServer = new PrintWriter(clientSocket.getOutputStream(), true);
        this.intoServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}
