import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ClientReadFromServer extends Thread{
    private BufferedReader intoClient;
    List<JButton> listButtons;
    JPanel menuJoinGame;
    JButton statusButton;

    Boolean isLocalUser;

    JTextField nickNameFiled;
    JTextField ipAddressGetTextField;
    JButton joinGameButton;
    JButton changeNickNameJoinButton;
    public ClientReadFromServer(BufferedReader intoClient,List<JButton> listButtons,JPanel menuJoinGame,JButton statusButton, Boolean isLocalUser,JTextField nickNameFiled, JTextField ipAddressGetTextField,JButton joinGameButton,JButton changeNickNameJoinButton) {
        this.intoClient=intoClient;
        this.listButtons=listButtons;
        this.menuJoinGame=menuJoinGame;
        this.statusButton=statusButton;
        this.isLocalUser=isLocalUser;
        this.nickNameFiled=nickNameFiled;
        this.ipAddressGetTextField=ipAddressGetTextField;
        this.joinGameButton=joinGameButton;
        this.changeNickNameJoinButton=changeNickNameJoinButton;
    }
    public ClientReadFromServer(BufferedReader intoClient,List<JButton> listButtons,JPanel menuJoinGame,JButton statusButton, Boolean isLocalUser,JTextField nickNameFiled) {
        this.intoClient=intoClient;
        this.listButtons=listButtons;
        this.menuJoinGame=menuJoinGame;
        this.statusButton=statusButton;
        this.isLocalUser=isLocalUser;
        this.nickNameFiled=nickNameFiled;
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

                        listButtons.add(standardButtonGenerate(UsersNicks));
                        menuJoinGame.add(listButtons.get(listButtons.size()-1));
                    }
                    if(!isLocalUser){
                        statusButton.setVisible(false);
                        changeNickNameJoinButton.setVisible(true);
                        joinGameButton.setVisible(false);
                        ipAddressGetTextField.setVisible(false);
                    }

                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }

                if(message.startsWith("PlayerDisconnect:")){
                    String[] tmp=message.split(":");

                    for(int i=0;i<listButtons.size();i++){

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
                    for(int i=0;i<listButtons.size();i++){
                        if(listButtons.get(i).getText().equals(tmp[0])){
                            listButtons.get(i).setText(tmp[1]);
                            break;
                        }
                    }

                    statusButton.setVisible(false);
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }

                if(message.equals("nickNameTakenChanging")){
                    statusButton.setText("Nick został zajety !!");
                    statusButton.setVisible(true);
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }
                if(message.equals("nickNameTaken")){
                    statusButton.setText("Nick został zajety !!");
                    statusButton.setVisible(true);
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }

                if(message.startsWith("ConfirmQuit")){
                    /*for(int i=0;i<listButtons.size();i++){
                        menuJoinGame.remove(listButtons.get(i));
                    }
                    ipAddressGetTextField.setVisible(true);
                    nickNameTextFieldJoinMenu.setVisible(true);
                    joinGameButton.setVisible(true);
                    statusButton.setVisible(false);
                    menuPlay.setVisible(true);
                    menuJoinGame.setVisible(false);
                    changeNickNameJoinButton.setVisible(false);*/
                    System.out.print("Koniec");
                    return;
                }

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
