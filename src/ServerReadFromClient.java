import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ServerReadFromClient extends Thread{
    private BufferedReader  intoServer;
    private Communication communication;
    Semaphore syncJoiningPlayers;
    public ServerReadFromClient(Socket clientSocket, Communication communication, Semaphore syncJoiningPlayers) throws IOException {
        this.intoServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.communication=communication;
        this.syncJoiningPlayers=syncJoiningPlayers;
    }

    public void run() {
        while (true){
            try {
                communication.message=intoServer.readLine();
                syncJoiningPlayers.release();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
