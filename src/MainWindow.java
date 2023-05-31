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


    public MainWindow(){
        gameStarted=false;
        stopHostingGame=false;
        MenuWindow MenuWindow=new MenuWindow();

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
        logoLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        container.add(logoLabel);
        container.add(mainMenu);
        container.add(menuHostGame, BorderLayout.NORTH);
        container.add(menuJoinGame);
        menuPlay.setVisible(false);
        menuHostGame.setVisible(false);
        menuJoinGame.setVisible(false);
        container.add(menuPlay);




        //akcje przycisków
        /*playButton.addActionListener(back -> {
            menuEnter.setVisible(false);
            menuPlay.setVisible(true);
        });

        backButton.addActionListener(back -> {
            menuPlay.setVisible(false);
            menuEnter.setVisible(true);
        });


        List<JButton> listButtons=new ArrayList<>();
        hostButton.addActionListener(back -> {

            this.server=new Server();

            menuPlay.setVisible(false);
            menuHostGame.setVisible(true);
            synchronized (this) {
                stopHostingGame=false;
            }


            Thread ThreadWaitingForPlayers = new Thread(() -> {

                try {
                    this.server.openSocket(8080);
                    this.server.serverSocketChannel.setSoTimeout(1000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

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

                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,listButtons,menuHostGame,hostStatusButton,true,nickNameTextFieldHostMenu);
                    clientReadFromServer.start();

                    this.client.fromClient.println("setNickname:"+nickNameTextFieldHostMenu.getText());

                    break;
                } catch (IOException e) {}
            }

        });

        startGameButton.addActionListener(back -> {
            synchronized (this) {
                gameStarted=true;
            }
           *//* Collections.shuffle(server.listOfSockets);*//*
        });

        joinButton.addActionListener(back -> {
            menuPlay.setVisible(false);
            menuJoinGame.setVisible(true);
        });

        joinGameButton.addActionListener(back -> {

            try {
                this.client=new Client();
                this.client.ClientConnect(ipAddressGetTextField.getText(),8080);
                this.client.SetCommunicationParameters(this.client.clientSocket);

                ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,listButtons,menuJoinGame,statusButton,false,nickNameTextFieldJoinMenu,ipAddressGetTextField,joinGameButton,changeNickNameJoinButton);
                clientReadFromServer.start();

                this.client.fromClient.println("setNickname:"+nickNameTextFieldJoinMenu.getText());

            } catch (IOException e) {
                statusButton.setVisible(true);
            }

        });

        backFromHostLobbyButton.addActionListener(back -> {
            try{
                this.client.fromClient.println("Quit");
            }catch (NullPointerException error){}

        });

        changeNickNameJoinButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+nickNameTextFieldJoinMenu.getText());
        });

        backFromJoinLobbyButton.addActionListener(back -> {
            try{
                this.client.fromClient.println("Quit");
            }catch (NullPointerException error){}

        });


        changeNickNameHostButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+nickNameTextFieldHostMenu.getText());
        });




        leaveButton.addActionListener(leaveGame -> System.exit(0));
*/

        window = new JFrame();
        window.setTitle("PoliPoly");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null);
        window.add(container);

    }

    public void show() {
        window.setVisible(true);

    }

}
