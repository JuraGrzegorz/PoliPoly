import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public ServerSocketChannel serverSocketChannel;
    public Selector selector;

    void openSocket(int port) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);
        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Serwer nasłuchuje na porcie 12345...");
    }
    void acceptClient() throws IOException {
        while(true){

            int readyChannels = selector.select();
            System.out.print(readyChannels + "\n");
        }

        /*SocketChannel clientSocketChannel = serverSocketChannel.accept();
        System.out.println("Połączenie z klientem nawiązane.");*/
    }
}
