import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket clientSocket;

    PrintWriter fromClient;
    BufferedReader  intoClient;

    void ClientConnect(String ip, int port) throws IOException {
        this.clientSocket = new Socket(ip, port);
    }

    void SetCommunicationParameters(Socket clientSocket) throws IOException {
        if(clientSocket==null){
            return;
        }
        fromClient = new PrintWriter(clientSocket.getOutputStream(), true);
        intoClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}

class Player{
    boolean playerConnected;
    Player(){
        playerConnected=false;
    }

    void PlayerConnect(){
        playerConnected=true;
    }
    void PlayerDisconnect(){
        playerConnected=false;
    }
}