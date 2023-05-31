import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ServerMainThread extends Thread{
    private Server server;

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
                            }

                            val.syncServerWriteToClient.release();
                        }

                        if(val.message.startsWith("Quit")){
                            for(int i=0;i<server.listOfCommunication.size();i++){
                                if(server.listOfCommunication.get(i).nickName==val.nickName){
                                    //wylaczenie watkow !!
                                    server.listOfCommunication.get(i).message="ConfirmQuit";
                                    server.listOfCommunication.get(i).syncServerWriteToClient.release();
                                    server.listOfCommunication.remove(i);
                                }else{
                                    server.listOfCommunication.get(i).message="PlayerDisconnect:"+val.nickName;
                                    server.listOfCommunication.get(i).syncServerWriteToClient.release();
                                }
                                break;
                            }
                        }

                        if(val.message.startsWith("changeNickname:")){
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
                                val.message="nickNameTaken";
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
