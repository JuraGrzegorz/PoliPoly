import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ClientReadFromServer extends Thread{
    private final BufferedReader intoClient;
    List<JButton> listButtons;
    JPanel menuJoinGame;
    JButton statusButton;

    Boolean isLocalUser;

    JTextField nickNameFiled;
    public ClientReadFromServer(BufferedReader intoClient,List<JButton> listButtons,JPanel menuJoinGame,JButton statusButton, Boolean isLocalUser,JTextField nickNameFiled) {
        this.intoClient=intoClient;
        this.listButtons=listButtons;
        this.menuJoinGame=menuJoinGame;
        this.statusButton=statusButton;
        this.isLocalUser=isLocalUser;
        this.nickNameFiled=nickNameFiled;
    }

    /*public ClientReadFromServer(BufferedReader intoClient) {
        this.intoClient=intoClient;
        isLocalUser=true;
    }*/


    public void run() {
        String message;
        while (true){
            try {
                message=intoClient.readLine();
                if(!isLocalUser){
                    if(message.startsWith("JoiningLobby:")){
                        String[] tmp=message.split(":");
                        tmp=tmp[1].split(",");
                        for(String UsersNicks:tmp){

                            listButtons.add(standardButtonGenerate(UsersNicks));
                            menuJoinGame.add(listButtons.get(listButtons.size()-1));
                        }

                        menuJoinGame.setVisible(false);
                        menuJoinGame.setVisible(true);
                    }

                    if(message.startsWith("PlayerDisconnect:")){
                        String[] tmp=message.split(":");

                        for(int i=0;i<listButtons.size();i++){
                            System.out.print(listButtons.get(i).getText()+ " "+tmp[1]+"\n");
                            if(listButtons.get(i).getText().equals(tmp[1])){
                                menuJoinGame.remove(listButtons.get(i));
                                listButtons.remove(i);
                                break;
                            }
                        }
                        menuJoinGame.setVisible(false);
                        menuJoinGame.setVisible(true);
                    }

                    if(message.startsWith("ConfirmChangeNickname:")){
                        message = message.substring(("ConfirmChangeNickname:").length());
                        String[] tmp=message.split(":");
                        for (JButton listButton : listButtons) {
                            if (listButton.getText().equals(tmp[0])) {
                                listButton.setText(tmp[1]);
                                break;
                            }
                        }

                        statusButton.setVisible(false);
                        menuJoinGame.setVisible(false);
                        menuJoinGame.setVisible(true);
                    }
                }

                if(message.equals("nickNameTaken")){
                    statusButton.setText("Nick został zajety !!");
                    statusButton.setVisible(true);
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }

                if(message.startsWith("ConfirmQuit")){
                    return;
                }
                /*System.out.print(message);*/
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
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