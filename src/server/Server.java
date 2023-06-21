import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Communication{
    Semaphore syncServerMainThread;
    Semaphore syncReadFromClient;
    Semaphore syncServerWriteToClient;
    String message;
    String nickName;
    public Communication() {
        syncServerMainThread =new Semaphore(0);
        syncServerWriteToClient=new Semaphore(0);
        syncReadFromClient=new Semaphore(0);
        message="";
    }

}
public class Server {
    public ServerSocket serverSocketChannel;
    int state;
    Semaphore syncJoiningPlayers;
    List<Communication> listOfCommunication;
    Server(){
        listOfCommunication =new ArrayList<>();
        syncJoiningPlayers=new Semaphore(0);
    }
    void openSocket(int port) throws IOException {
        serverSocketChannel= new ServerSocket(port);
    }
    void addSemaphore(){
        Communication tmp=new Communication();
        listOfCommunication.add(tmp);
    }
}
