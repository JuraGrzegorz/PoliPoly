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
    public ClientReadFromServer(BufferedReader intoClient,List<JButton> listButtons,JPanel menuJoinGame,JButton statusButton) {
        this.intoClient=intoClient;
        this.listButtons=listButtons;
        this.menuJoinGame=menuJoinGame;
        this.statusButton=statusButton;
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
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
                }

                if(message.startsWith("ConfirmQuit")){
                    return;
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
                if(message.equals("nickNameTaken")){
                    statusButton.setText("Nick został zajety !!");
                    menuJoinGame.setVisible(false);
                    menuJoinGame.setVisible(true);
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
