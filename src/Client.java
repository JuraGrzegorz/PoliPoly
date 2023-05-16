import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket clientSocket;

    void ClientConnect(String ip, int port) throws IOException {
        this.clientSocket = new Socket(ip, port);
    }

}
