import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class MainWindow {
    private final JFrame window;
    private static final int BUTTONFONTSIZE = 16;

    Server server;
    Client client;
    boolean gameStarted;

    private JButton standardButtonGenerate (String name){
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

        // Dodanie JLabel z logo
        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Tworzenie przycisków menuEnter
        JButton playButton = new JButton("Graj");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        playButton.setBackground(new Color(0x2dce98));
        playButton.setForeground(Color.white);
        playButton.setUI(new StyledButtonUI());
        playButton.setPreferredSize(new Dimension(300, 50));

        JButton creditsButton = new JButton("Autorzy");
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        creditsButton.setBackground(new Color(0x2dce98));
        creditsButton.setForeground(Color.white);
        creditsButton.setUI(new StyledButtonUI());
        creditsButton.setPreferredSize(new Dimension(300, 50));

        JButton leaveButton = new JButton("Wyjdź");
        leaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaveButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        leaveButton.setBackground(new Color(0x2dce98));
        leaveButton.setForeground(Color.white);
        leaveButton.setUI(new StyledButtonUI());
        leaveButton.setPreferredSize(new Dimension(300, 50));


        // Tworzenie przycisków menuPlay
        JButton hostButton = new JButton("Host");
        hostButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        hostButton.setBackground(new Color(0x2dce98));
        hostButton.setForeground(Color.white);
        hostButton.setUI(new StyledButtonUI());
        hostButton.setPreferredSize(new Dimension(300, 50));

        JButton joinButton = new JButton("Join");
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        joinButton.setBackground(new Color(0x2dce98));
        joinButton.setForeground(Color.white);
        joinButton.setUI(new StyledButtonUI());
        joinButton.setPreferredSize(new Dimension(300, 50));


        JButton backButton = new JButton("Wróć");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        backButton.setBackground(new Color(0x2dce98));
        backButton.setForeground(Color.white);
        backButton.setUI(new StyledButtonUI());
        backButton.setPreferredSize(new Dimension(300, 50));


        //Panel Główny
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

        //Panel Po wciśnięciu graj
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



        JButton startGameButton = new JButton("Star Game!");
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        startGameButton.setBackground(new Color(0x2dce98));
        startGameButton.setForeground(Color.white);
        startGameButton.setUI(new StyledButtonUI());
        startGameButton.setPreferredSize(new Dimension(300, 50));


        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        JButton ip_info = new JButton("ip : "+localhost.getHostAddress());
        ip_info.setAlignmentX(Component.CENTER_ALIGNMENT);
        ip_info.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        ip_info.setBackground(new Color(0x2dce98));
        ip_info.setForeground(Color.white);
        ip_info.setUI(new StyledButtonUI());
        ip_info.setPreferredSize(new Dimension(300, 50));


        JPanel menuHostGame = new JPanel();
        menuHostGame.setOpaque(false);

        menuHostGame.setLayout(new BoxLayout(menuHostGame, BoxLayout.Y_AXIS));
        menuHostGame.add(Box.createVerticalGlue());
        menuHostGame.add(startGameButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(ip_info);
        menuHostGame.add(Box.createVerticalStrut(10));


        JButton joinGameButton = new JButton("Join Game!");
        joinGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinGameButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        joinGameButton.setBackground(new Color(0x2dce98));
        joinGameButton.setForeground(Color.white);
        joinGameButton.setUI(new StyledButtonUI());
        joinGameButton.setPreferredSize(new Dimension(300, 50));



        JTextField ipAddressGetTextField =new JTextField("192.168.18.14");
        ipAddressGetTextField.setMaximumSize(new Dimension(200, 1));

        JTextField nickNameTextField =new JTextField("Player");
        nickNameTextField.setMaximumSize(new Dimension(200, 1));

        JButton statusButton =standardButtonGenerate("Server nie istnieje!!");
        statusButton.setVisible(false);


        JPanel menuJoinGame = new JPanel();
        menuJoinGame.setOpaque(false);
        menuJoinGame.setLayout(new BoxLayout(menuJoinGame, BoxLayout.Y_AXIS));
        menuJoinGame.add(Box.createVerticalGlue());
        menuJoinGame.add(joinGameButton);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(nickNameTextField);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(ipAddressGetTextField);
        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(statusButton);
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

        hostButton.addActionListener(back -> {
            System.out.print("START HOSTING\n");
            menuPlay.setVisible(false);
            menuHostGame.setVisible(true);



            Thread ThreadWaitingForPlayers = new Thread(() -> {


                this.server=new Server();
                try {
                    this.server.openSocket(8080);
                    this.server.serverSocketChannel.setSoTimeout(2000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                while (true){
                    //dodac synchronizacje
                    int countOfPlayers=0;
                    if(gameStarted){
                        break;
                    }
                    Socket tmp_clientSock = null;
                    try {
                        tmp_clientSock = this.server.serverSocketChannel.accept();
                        this.server.SetCommunicationParameters(tmp_clientSock);
                        countOfPlayers++;

                        menuHostGame.add(standardButtonGenerate(this.server.intoServer.readLine()));
                        menuHostGame.setVisible(false);
                        menuHostGame.setVisible(true);
                        //dalsza komunikacja

                        if(countOfPlayers==4){
                            break;
                        }

                    } catch (IOException e) {}

                }
            });
            ThreadWaitingForPlayers.start();

        });

        startGameButton.addActionListener(back -> {
            gameStarted=true;
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
                this.client.fromClient.println(nickNameTextField.getText());

                statusButton.setText("waiting for start game!");
                System.out.print("Connected!");
                ipAddressGetTextField.setVisible(false);
                joinGameButton.setVisible(false);
                statusButton.setVisible(true);

            } catch (IOException e) {
                statusButton.setVisible(true);
            }

        });


        startGameButton.addActionListener(back -> {


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
