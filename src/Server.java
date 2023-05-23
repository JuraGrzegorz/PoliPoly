import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Communication{
    Semaphore syncServerMainThread;
    Semaphore syncServerWriteToClient;
    String message;
    String nickName;
    public Communication() {
        syncServerMainThread =new Semaphore(0);
        syncServerWriteToClient=new Semaphore(0);
        message="";
    }

}
public class Server {
    public ServerSocket serverSocketChannel;
    int state;
    Semaphore syncJoiningPlayers;
    List<Communication> listOfCommunication;
    List<String>listPlayerNicknames;

    Server(){
        listOfCommunication =new ArrayList<>();
        listPlayerNicknames=new ArrayList<>();
        syncJoiningPlayers=new Semaphore(0);
    }
    void openSocket(int port) throws IOException {
        this.serverSocketChannel= new ServerSocket(port);
    }
    void addSemaphore(){
        Communication tmp=new Communication();
        listOfCommunication.add(tmp);
    }
}
