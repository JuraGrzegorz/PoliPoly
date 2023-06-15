import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ServerReadFromClient extends Thread{
    private final BufferedReader  intoServer;
    private final Communication communication;
    private final Semaphore syncJoiningPlayers;
    boolean gameStarted;
    public ServerReadFromClient(Socket clientSocket, Communication communication, Semaphore syncJoiningPlayers,boolean gameStarted) throws IOException {
        this.intoServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.communication=communication;
        this.syncJoiningPlayers=syncJoiningPlayers;
        this.gameStarted=gameStarted;
    }

    public void run() {
        while (true){
            try {
                communication.message=intoServer.readLine();
                if(communication.message.equals("Quit")){
                    syncJoiningPlayers.release();
                    break;
                }
                synchronized (this) {
                    if(gameStarted){
                        communication.syncReadFromClient.release();
                        System.out.print("dziala");
                    }else{
                        syncJoiningPlayers.release();
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
