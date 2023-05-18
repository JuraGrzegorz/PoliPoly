import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Communication{
    PrintWriter fromServer;
    BufferedReader  intoServer;

    String playerNickName;

    public Communication(PrintWriter fromServer,BufferedReader intoServer) {
        this.fromServer=fromServer;
        this.intoServer=intoServer;
    }
    public void SetNickName(String playerNickName){
        this.playerNickName=playerNickName;
    }
}
public class Server {
    public ServerSocket serverSocketChannel;

    List<Communication> listOfSockets;
    Server(){
        listOfSockets=new ArrayList<>();
    }
    void openSocket(int port) throws IOException {
        this.serverSocketChannel= new ServerSocket(port);
        System.out.println("Serwer nasłuchuje na porcie " + port);
    }
    void SetCommunicationParameters(Socket clientSocket) throws IOException {
        if(clientSocket==null){
            return;
        }
        listOfSockets.add(new Communication(new PrintWriter(clientSocket.getOutputStream(), true)
                ,new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))));
    }
}
