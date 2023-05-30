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

    public JButton standardButtonGenerate (String name){
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        tmp.setBackground(new Color(0x2dce98));
        tmp.setForeground(Color.white);
        tmp.setUI(new StyledButtonUI());
        tmp.setPreferredSize(new Dimension(300, 50));
        return tmp;
    }

    public MainWindow(){
        gameStarted=false;
        stopHostingGame=false;

        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playButton=standardButtonGenerate("Graj");
        JButton creditsButton = standardButtonGenerate("Autorzy");
        JButton leaveButton = standardButtonGenerate("Wyjdź");
        JButton hostButton = standardButtonGenerate("Host");
        JButton joinButton = standardButtonGenerate("Join");
        JButton backButton = standardButtonGenerate("Wróć");

        JPanel menuEnter = new JPanel();
        menuEnter.setOpaque(false);

        menuEnter.setLayout(new BoxLayout(menuEnter, BoxLayout.Y_AXIS));
        menuEnter.add(Box.createVerticalGlue());
        menuEnter.add(playButton);
        menuEnter.add(Box.createVerticalStrut(10));
        menuEnter.add(creditsButton);
        menuEnter.add(Box.createVerticalStrut(10));
        menuEnter.add(leaveButton);
        menuEnter.add(Box.createVerticalGlue());

        JPanel menuPlay = new JPanel();
        menuPlay.setOpaque(false);

        menuPlay.setLayout(new BoxLayout(menuPlay, BoxLayout.Y_AXIS));
        menuPlay.add(Box.createVerticalGlue());
        menuPlay.add(hostButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(joinButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(backButton);
        menuPlay.add(Box.createVerticalGlue());

        JButton startGameButton = standardButtonGenerate("Start Game");

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }


        JButton ip_info = standardButtonGenerate("ip : "+localhost.getHostAddress());
        JButton backFromHostLobbyButton = standardButtonGenerate("Back");

        JTextField nickNameTextFieldHostMenu =new JTextField("HostOfGames");
        nickNameTextFieldHostMenu.setMaximumSize(new Dimension(200, 1));

        JButton changeNickNameHostButton =standardButtonGenerate("Zmień!");

        JButton hostStatusButton =standardButtonGenerate("status!");
        hostStatusButton.setVisible(false);

        JPanel menuHostGame = new JPanel();
        menuHostGame.setOpaque(false);
        menuHostGame.setLayout(new BoxLayout(menuHostGame, BoxLayout.Y_AXIS));
        menuHostGame.add(Box.createVerticalGlue());
        menuHostGame.add(hostStatusButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(startGameButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(ip_info);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(nickNameTextFieldHostMenu);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(changeNickNameHostButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(backFromHostLobbyButton);
        menuHostGame.add(Box.createVerticalStrut(10));



        JButton joinGameButton = standardButtonGenerate("Join Game");



        JTextField ipAddressGetTextField =new JTextField("192.168.18.14");
        ipAddressGetTextField.setMaximumSize(new Dimension(200, 1));

        JTextField nickNameTextFieldJoinMenu =new JTextField("Player");
        nickNameTextFieldJoinMenu.setMaximumSize(new Dimension(200, 1));

        JButton statusButton =standardButtonGenerate("Server nie istnieje!!");
        statusButton.setVisible(false);


        JButton backFromJoinLobbyButton =standardButtonGenerate("Back!");
        statusButton.setVisible(false);
        JButton changeNickNameJoinButton =standardButtonGenerate("Zmień!");
        changeNickNameJoinButton.setVisible(false);

        JPanel menuJoinGame = new JPanel();
        menuJoinGame.setOpaque(false);
        menuJoinGame.setLayout(new BoxLayout(menuJoinGame, BoxLayout.Y_AXIS));
        menuJoinGame.add(Box.createVerticalGlue());
        menuJoinGame.add(joinGameButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(nickNameTextFieldJoinMenu);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(changeNickNameJoinButton);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(ipAddressGetTextField);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(statusButton);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(backFromJoinLobbyButton);
        menuJoinGame.add(Box.createVerticalStrut(10));

        // Tworzenie panelu Container
        JPanel container;
        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon menuBackground = new ImageIcon("assets/tmp_bg.png");
                g.drawImage(menuBackground.getImage(), 0, 0, null);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        container.add(logoLabel);
        container.add(menuEnter);
        container.add(menuHostGame,BorderLayout.NORTH);
        container.add(menuJoinGame);
        menuPlay.setVisible(false);
        menuHostGame.setVisible(false);
        menuJoinGame.setVisible(false);
        container.add(menuPlay);




        //akcje przycisków
        playButton.addActionListener(back -> {
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

                ServerMainThread serverMainThread=new ServerMainThread(server,listButtons,menuHostGame);
                serverMainThread.start();
                while (true){

                    synchronized (this) {
                        if(gameStarted || stopHostingGame){
                            System.out.print("STOP HOSTING\n");
                            break;
                        }
                    }
                    Socket tmp_clientSock = null;
                    try {
                        tmp_clientSock = this.server.serverSocketChannel.accept();
                        server.addSemaphore();
                        /*System.out.print(this.server.listOfCommunication.size());*/
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
                    this.client.ClientConnect(ipAddressGetTextField.getText(),8080);
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
           /* Collections.shuffle(server.listOfSockets);*/
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

                ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client.intoClient,listButtons,menuJoinGame,statusButton,false,nickNameTextFieldJoinMenu);
                clientReadFromServer.start();

                this.client.fromClient.println("setNickname:"+nickNameTextFieldJoinMenu.getText());

                ipAddressGetTextField.setVisible(false);
                /*nickNameTextFieldJoinMenu.setVisible(false);*/
                joinGameButton.setVisible(false);
                changeNickNameJoinButton.setVisible(true);

            } catch (IOException e) {
                statusButton.setVisible(true);
            }

        });

        backFromHostLobbyButton.addActionListener(back -> {
            /*System.out.print(this.server.listOfSockets.size());

            for(int i=0;i<tmp_button.size();i++){
                menuHostGame.remove(tmp_button.get(i));
            }
            synchronized (this) {
                stopHostingGame=true;
            }

            try {
                this.server.serverSocketChannel.close();
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            menuPlay.setVisible(true);
            menuHostGame.setVisible(false);*/
        });

        changeNickNameJoinButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+nickNameTextFieldJoinMenu.getText());
        });

        backFromJoinLobbyButton.addActionListener(back -> {
            try{
                this.client.fromClient.println("Quit");
            }catch (NullPointerException error){}
            
            /*System.out.print("quit\n");*/
            for(int i=0;i<listButtons.size();i++){
                menuJoinGame.remove(listButtons.get(i));
            }
            ipAddressGetTextField.setVisible(true);
            nickNameTextFieldJoinMenu.setVisible(true);
            joinGameButton.setVisible(true);
            statusButton.setVisible(false);
            menuPlay.setVisible(true);
            menuJoinGame.setVisible(false);
            changeNickNameJoinButton.setVisible(false);

        });


        changeNickNameHostButton.addActionListener(back -> {
            this.client.fromClient.println("changeNickname:"+nickNameTextFieldHostMenu.getText());
        });




        leaveButton.addActionListener(leaveGame -> System.exit(0));


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
