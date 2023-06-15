import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServerMainThread extends Thread{
    private final Server server;

    ServerMainThread(Server server){
        this.server=server;
    }
    public void run() {

        while (true){
            if(server.state==0){
                try {
                    server.syncJoiningPlayers.acquire();

                    for(Communication val : server.listOfCommunication){

                        if(val.message.startsWith("setNickname:")){
                            setNickNameCommand(val);
                            break;
                        }

                        if(val.message.startsWith("Quit")){
                            quitCommand(val);
                            break;
                        }

                        if(val.message.startsWith("changeNickname:")){
                            changeNicknameCommand(val);
                            break;
                        }

                        if(val.message.startsWith("startGame")){
                            server.state=1;
                            for(int i=0;i<server.listOfCommunication.size();i++){
                                server.listOfCommunication.get(i).message="gameStarted";
                                server.listOfCommunication.get(i).syncServerWriteToClient.release();
                            }
                            Collections.shuffle(server.listOfCommunication);
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }else{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for(Communication val : server.listOfCommunication){
                    System.out.print("poszlo");

                    val.message="move:";
                    val.syncServerWriteToClient.release();
                    return;
                    /*try {
                        val.syncReadFromClient.acquire();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                }
            }
        }
    }
    void setNickNameCommand(Communication val){
        String nickName = val.message.substring(("setNickname:").length());

        int x;
        for(x=0;x<server.listOfCommunication.size()-1;x++){
            if(server.listOfCommunication.get(x).nickName.equals(nickName)){
                break;
            }
        }
        if(x==server.listOfCommunication.size()-1){
            val.nickName=nickName;


            val.message="JoiningLobby:";
            for(int i=0;i<server.listOfCommunication.size();i++){
                val.message+=server.listOfCommunication.get(i).nickName+",";
            }

            for(int i=0;i<server.listOfCommunication.size()-1;i++){
                server.listOfCommunication.get(i).message="JoiningLobby:"+nickName+",";
                server.listOfCommunication.get(i).syncServerWriteToClient.release();
            }
        }else{
            val.message="nickNameTaken";
            server.listOfCommunication.remove(server.listOfCommunication.size()-1);
        }

        val.syncServerWriteToClient.release();
    }
    void quitCommand(Communication val){
        int indexToDel = 0;

        for(int i=0;i<server.listOfCommunication.size();i++){
            if(server.listOfCommunication.get(i).nickName.equals(val.nickName)){
                //wylaczenie watkow !!
                server.listOfCommunication.get(i).message="ConfirmQuit";
                server.listOfCommunication.get(i).syncServerWriteToClient.release();
                indexToDel=i;
                if(i==0){
                    for(int j=1;j<server.listOfCommunication.size();j++){
                        server.listOfCommunication.get(j).message="ForceQuit";
                        server.listOfCommunication.get(j).syncServerWriteToClient.release();
                    }
                    break;
                }
            }else{
                server.listOfCommunication.get(i).message="PlayerDisconnect:"+val.nickName;
                server.listOfCommunication.get(i).syncServerWriteToClient.release();
            }
        }
        server.listOfCommunication.remove(indexToDel);
    }
    void changeNicknameCommand(Communication val){
        String nickName = val.message.substring(("changeNickname:").length());
        int x=0;
        for(x=0;x<server.listOfCommunication.size();x++){
            if(server.listOfCommunication.get(x).nickName.equals(nickName)){
                break;
            }
        }
        if(x==server.listOfCommunication.size()){
            val.message="ConfirmChangeNickname:"+val.nickName+':';
            val.nickName=nickName;
            val.message+=nickName;
            for(int i=0;i<server.listOfCommunication.size();i++){
                server.listOfCommunication.get(i).message=val.message;
                server.listOfCommunication.get(i).syncServerWriteToClient.release();
            }
        }else{
            val.message="nickNameTakenChanging";
            val.syncServerWriteToClient.release();
        }
    }
}
