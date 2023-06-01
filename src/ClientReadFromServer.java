import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ClientReadFromServer extends Thread{
    private BufferedReader intoClient;
    MenuWindow menuWindow;
    List<JButton> listButtons;
    int countOfPlayer;

    public ClientReadFromServer(BufferedReader intoClient,MenuWindow menuWindow,List<JButton> listButtons) {
        this.intoClient=intoClient;
        this.menuWindow=menuWindow;
        this.listButtons=listButtons;
        countOfPlayer=0;
    }

    public void run() {
        String message;
        while (true){
            try {
                message=intoClient.readLine();
                if(message.startsWith("JoiningLobby:")){
                    String[] tmp=message.split(":");
                    tmp=tmp[1].split(",");

                    for(String UsersNicks:tmp){
                        listButtons.get(countOfPlayer).setText(UsersNicks);
                        countOfPlayer++;
                    }
                }

                if(message.startsWith("PlayerDisconnect:")){
                    String[] tmp=message.split(":");
                    for(int i=0;i<listButtons.size();i++){

                        if(listButtons.get(i).getText().equals(tmp[1])){
                            int j;
                            for(j=i;j<countOfPlayer-1;j++){
                                listButtons.get(j).setText(listButtons.get(j+1).getText());
                            }
                            listButtons.get(j).setText(String.valueOf(countOfPlayer));
                            countOfPlayer--;
                            break;
                        }

                    }
                }

                if(message.startsWith("ConfirmChangeNickname:")){
                    message = message.substring(("ConfirmChangeNickname:").length());
                    String[] tmp=message.split(":");
                    for(int i=0;i<listButtons.size();i++){
                        if(listButtons.get(i).getText().equals(tmp[0])){
                            listButtons.get(i).setText(tmp[1]);
                            break;
                        }
                    }
                }

//                if(message.equals("nickNameTakenChanging")){
//                    statusButton.setText("Nick został zajety !!");
//                    statusButton.setVisible(true);
//                    menuJoinGame.setVisible(false);
//                    menuJoinGame.setVisible(true);
//                }
//                if(message.equals("nickNameTaken")){
//                    statusButton.setText("Nick został zajety !!");
//                    statusButton.setVisible(true);
//                    menuJoinGame.setVisible(false);
//                    menuJoinGame.setVisible(true);
//                }
//
                if(message.startsWith("ConfirmQuit")){
                    for(int i=0;i<listButtons.size();i++){
                        listButtons.get(i).setText(String.valueOf(i+1));
                    }
                    return;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
