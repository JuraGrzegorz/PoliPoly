import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServerMainThread extends Thread{
    private final Server server;
    int[] playersPosition;
    int [] playerCash;

    ServerMainThread(Server server){
        this.server=server;
        playersPosition=new int[4];
        playerCash=new int[4];
        for(int i=0;i<4;i++){
            playersPosition[i]=0;
            playerCash[i]=800;
        }
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
                            Thread.sleep(100);
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }else{

                int index=0;
                String message;
                for(Communication val : server.listOfCommunication){
                    try {
                        val.message="yourTurn";
                        val.syncServerWriteToClient.release();

                        val.syncReadFromClient.acquire();

                        if(val.message.startsWith("move:")){
                            int moveNumber;
                            val.message=val.message.substring(("move:").length());
                            String[] tmp=val.message.split(":");
                            moveNumber=Integer.parseInt(tmp[0]);
                            playersPosition[index]+=moveNumber;
                            if(playersPosition[index]>=32){
                                playerCash[index]+=400;
                                server.listOfCommunication.get(index).message="cash:"+playerCash[index];
                                server.listOfCommunication.get(index).syncServerWriteToClient.release();
                            }
                            Thread.sleep(50);
                            playersPosition[index]=playersPosition[index]%32;

                            message="move:";
                            message+=playersPosition[index];
                            message+=":"+index;

                            for(int i=0;i<server.listOfCommunication.size();i++){
                                server.listOfCommunication.get(i).message=message;
                                server.listOfCommunication.get(i).syncServerWriteToClient.release();
                            }
                        }
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    index++;
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
