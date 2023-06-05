import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.List;


public class MainWindow {
    private final JFrame window;
    private static final int BUTTONFONTSIZE = 16;

    Server server;
    Client client;
    boolean gameStarted;
    boolean stopHostingGame;

    NickNameTakenWindow alertWindow;

    public MainWindow(){
        gameStarted=false;
        stopHostingGame=false;
        MenuWindow menuWindow=new MenuWindow();
        alertWindow=new NickNameTakenWindow();
        client=null;
        JPanel container;
        // Tworzenie panelu Container
        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon menuBackground = new ImageIcon("assets/tmp_bg.png");
                g.drawImage(menuBackground.getImage(), 0, 0, null);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        menuWindow.logoLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        container.add(menuWindow.logoLabel);
        container.add(menuWindow.mainMenu);
        container.add(menuWindow.menuHostGame, BorderLayout.NORTH);
        container.add(menuWindow.menuJoinGame);
        menuWindow.menuPlay.setVisible(false);
        menuWindow.menuHostGame.setVisible(false);
        menuWindow.menuJoinGame.setVisible(false);
        container.add(menuWindow.menuPlay);




        //akcje przycisków
        menuWindow.playButton.addActionListener(back -> {
            menuWindow.mainMenu.setVisible(false);
            menuWindow.menuPlay.setVisible(true);
        });

        menuWindow.enterJoinMenuButton.addActionListener(back -> {
            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuJoinGame.setVisible(true);
        });

        menuWindow.joinGameButton.addActionListener(back -> {

            if(this.client==null){

                try {
                    this.client=new Client();
                    this.client.ClientConnect(menuWindow.ipAddressGetTextField.getText(),8080);
                    this.client.SetCommunicationParameters(this.client.clientSocket);

                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,client.fromClient,menuWindow,menuWindow.joinGameListButtons,alertWindow);
                    clientReadFromServer.start();

                    this.client.fromClient.println("setNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());

                } catch (IOException e) {
                    System.out.print(e);
                    /*statusButton.setVisible(true);*/
                }
            }
        });

        menuWindow.enterHostMenuButton.addActionListener(back -> {
            this.server=new Server();

            synchronized (this) {
                stopHostingGame=false;
            }

            try {
                this.server.openSocket(8080);
                this.server.serverSocketChannel.setSoTimeout(1000);

            } catch (IOException e) {
                alertWindow.setMessage("Server juz istnieje!");
                alertWindow.show();
                return;
            }
            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuHostGame.setVisible(true);

            Thread ThreadWaitingForPlayers = new Thread(() -> {

                ServerMainThread serverMainThread=new ServerMainThread(server);
                serverMainThread.start();
                while (true){

                    synchronized (this) {
                        if(gameStarted || stopHostingGame){
                            break;
                        }
                    }
                    Socket tmp_clientSock = null;
                    try {
                        tmp_clientSock = this.server.serverSocketChannel.accept();
                        server.addSemaphore();
                        Communication tmp_Comm=this.server.listOfCommunication.get(this.server.listOfCommunication.size()-1);
                        ServerReadFromClient serverReadThread=new ServerReadFromClient(tmp_clientSock,tmp_Comm,server.syncJoiningPlayers);
                        serverReadThread.start();
                        ServerWriteTOClient serverWriteThread=new ServerWriteTOClient(tmp_clientSock,tmp_Comm);
                        serverWriteThread.start();

                    } catch (IOException e) {}
                }

            });
            ThreadWaitingForPlayers.start();

            while (true){
                try {
                    this.client=new Client();
                    this.client.ClientConnect("localhost",8080);
                    this.client.SetCommunicationParameters(this.client.clientSocket);

                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,client.fromClient,menuWindow,menuWindow.hostGameListButtons,alertWindow);
                    clientReadFromServer.start();
                    this.client.fromClient.println("setNickname:"+menuWindow.nickNameTextFieldHostMenu.getText());

                    break;
                } catch (IOException e) {}
            }
        });

        menuWindow.backToMainMenuButton.addActionListener(back -> {
            menuWindow.mainMenu.setVisible(true);
            menuWindow.menuPlay.setVisible(false);
        });

        menuWindow.backFromJoinMenuButton.addActionListener(back -> {
            if(client!=null){
                try{
                    this.client.fromClient.println("Quit");
                    client=null;
                }catch (NullPointerException error){}
            }else{
                menuWindow.menuPlay.setVisible(true);
                menuWindow.menuJoinGame.setVisible(false);
            }
        });

        menuWindow.backFromHostMenuButton.addActionListener(back -> {
            try{
                this.client.fromClient.println("Quit");
            }catch (NullPointerException error){}
        });

        menuWindow.changeNickNameHostButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldHostMenu.getText());
        });

        menuWindow.changeNickNameJoinButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());
        });




        menuWindow.startGameButton.addActionListener(back -> {
            GamingWindow a=new GamingWindow();
            /*synchronized (this) {
                gameStarted=true;
            }*/
        });

        menuWindow.leaveButton.addActionListener(leaveGame -> System.exit(0));

        window = new JFrame();
        window.setTitle("PoliPoly");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.add(container);

    }

    public void show() {
        window.setVisible(true);

    }

}
