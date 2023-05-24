import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ServerMainThread extends Thread{
    private Server server;
    List<JButton> listButtons;
    JPanel menuHostGame;

    ServerMainThread(Server server,List<JButton> listButtons,JPanel menuHostGame){
        this.server=server;
        this.listButtons=listButtons;
        this.menuHostGame=menuHostGame;
    }
    public void run() {

        while (true){
            if(server.state==0){
                try {
                    server.syncJoiningPlayers.acquire();

                    for(Communication val : server.listOfCommunication){

                        if(val.message.startsWith("setNickname:")){
                            String nickName = val.message.substring(12);
                            if(server.listPlayerNicknames.contains(nickName)){
                                val.message="nickNameTaken";
                            }else{
                                val.nickName=nickName;
                                server.listPlayerNicknames.add(nickName);
                                listButtons.add(standardButtonGenerate(nickName));


                                val.message="JoiningLobby:";
                                for(String str:server.listPlayerNicknames){
                                       val.message+=str+",";
                                }


                                menuHostGame.add(listButtons.get(listButtons.size()-1));
                                menuHostGame.setVisible(false);
                                menuHostGame.setVisible(true);

                                for(int i=0;i<server.listOfCommunication.size()-1;i++){
                                    server.listOfCommunication.get(i).message="JoiningLobby:"+nickName+",";
                                    server.listOfCommunication.get(i).syncServerWriteToClient.release();
                                }
                            }
                            val.syncServerWriteToClient.release();
                        }
                        if(val.message.startsWith("Quit")){
                            for(int i=0;i<server.listPlayerNicknames.size();i++){
                                if(server.listPlayerNicknames.get(i)==val.nickName){
                                    server.listPlayerNicknames.remove(i);
                                }
                            }
                            for(int i=0;i<listButtons.size();i++){
                                if(listButtons.get(i).getText()==val.nickName){
                                    menuHostGame.remove(listButtons.get(i));
                                    listButtons.remove(i);
                                }
                            }
                            for(int i=0;i<server.listOfCommunication.size();i++){
                                if(server.listOfCommunication.get(i).nickName==val.nickName){
                                    //wylaczenie watkow !!
                                    server.listOfCommunication.get(i).message="ConfirmQuit";
                                    server.listOfCommunication.get(i).syncServerWriteToClient.release();
                                    server.listOfCommunication.remove(i);
                                }
                            }
                            System.out.print(server.listOfCommunication.size());
                            for(Communication SendDisPlayer:server.listOfCommunication){
                                SendDisPlayer.message="PlayerDisconnect:"+val.nickName;
                                SendDisPlayer.syncServerWriteToClient.release();
                            }
                            menuHostGame.setVisible(false);
                            menuHostGame.setVisible(true);
                            break;
                        }

                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
    //tymczasowo
    public JButton standardButtonGenerate (String name){
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, 16));
        tmp.setBackground(new Color(0x2dce98));
        tmp.setForeground(Color.white);
        tmp.setUI(new StyledButtonUI());
        tmp.setPreferredSize(new Dimension(300, 50));
        return tmp;
    }
}
