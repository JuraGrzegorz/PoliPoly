import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWriteTOClient extends Thread{
    private PrintWriter fromServer;
    private Communication communication;

    public ServerWriteTOClient(Socket clientSocket, Communication communication) throws IOException {
        this.fromServer=new PrintWriter(clientSocket.getOutputStream(), true);
        this.communication=communication;
    }

    public void run() {
        while (true){
            try {
                communication.syncServerWriteToClient.acquire();
                fromServer.println(communication.message);
                communication.message="";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
