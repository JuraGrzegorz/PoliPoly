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
        MenuWindow menuWindow=new MenuWindow();

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
            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuJoinGame.setVisible(true);

            try {
                this.client=new Client();
                this.client.ClientConnect(menuWindow.ipAddressGetTextField.getText(),8080);
                this.client.SetCommunicationParameters(this.client.clientSocket);

                ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,menuWindow,menuWindow.joinGameListButtons);
                clientReadFromServer.start();

                this.client.fromClient.println("setNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());

            } catch (IOException e) {
                System.out.print(e);
                /*statusButton.setVisible(true);*/
            }

        });

        menuWindow.enterHostMenuButton.addActionListener(back -> {
            this.server=new Server();

            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuHostGame.setVisible(true);
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

                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,menuWindow,menuWindow.hostGameListButtons);
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
            try{
                this.client.fromClient.println("Quit");
            }catch (NullPointerException error){}
            menuWindow.menuPlay.setVisible(true);
            menuWindow.menuJoinGame.setVisible(false);
        });

        menuWindow.backFromHostMenuButton.addActionListener(back -> {
            menuWindow.menuPlay.setVisible(true);
            menuWindow.menuHostGame.setVisible(false);
        });

        menuWindow.changeNickNameHostButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldHostMenu.getText());
        });

        menuWindow.changeNickNameJoinButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());
        });




//        startGameButton.addActionListener(back -> {
//            synchronized (this) {
//                gameStarted=true;
//            }
//           *//* Collections.shuffle(server.listOfSockets);*//*
//        });
//
//        joinButton.addActionListener(back -> {
//            menuPlay.setVisible(false);
//            menuJoinGame.setVisible(true);
//        });
//
//
//        backFromHostLobbyButton.addActionListener(back -> {
//            try{
//                this.client.fromClient.println("Quit");
//            }catch (NullPointerException error){}
//
//        });
//
//        changeNickNameJoinButton.addActionListener(back -> {
//            this.client.fromClient.println("changeNickname:"+nickNameTextFieldJoinMenu.getText());
//        });
//
//        backFromJoinLobbyButton.addActionListener(back -> {
//            try{
//                this.client.fromClient.println("Quit");
//            }catch (NullPointerException error){}
//
//        });
//
//
//        changeNickNameHostButton.addActionListener(back -> {
//            this.client.fromClient.println("changeNickname:"+nickNameTextFieldHostMenu.getText());
//        });




        menuWindow.leaveButton.addActionListener(leaveGame -> System.exit(0));

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
